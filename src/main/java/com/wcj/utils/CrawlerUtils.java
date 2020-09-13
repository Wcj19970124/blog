package com.wcj.utils;

import com.wcj.annotation.Extract;
import com.wcj.annotation.JsoupDocument;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.lang.reflect.Field;
import java.util.List;

/**
 * 爬虫工具类(历史上的今天)
 *
 * @author wcj
 * @Date 2020/9/13 10:42
 * @Version 1.0
 */
public final class CrawlerUtils {

    private CrawlerUtils() {
    }

    /**
     * 获取Jsoup连接Connection
     *
     * @param url
     * @return
     */
    public static Connection getConnection(String url) {
        //获取Connection
        Connection connection = Jsoup.connect(url);
        //伪造请求头
        connection.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;" +
                "q=0.8,application/signed-exchange;v=b3;q=0.9");
        connection.header("Accept-Encoding", "gzip, deflate");
        connection.header("Accept-Language", "zh-CN,zh;q=0.9,en;q=0.8");
        connection.header("Cache-Control", "max-age=0");
        connection.header("Connection", "keep-alive");
        connection.header("Cookie", "bdshare_firstime=1599917555934; UM_distinctid=1748284812e11c-0361d5a6841d52-333769-1fa400-1748284812fce9; " +
                "__gads=ID=099088d6d086e5a9:T=1599917558:S=ALNI_MZJgXW6RjPpSm2m7GutSc1bN8AZBw; " +
                "BAIDU_SSP_lcr=https://www.baidu.com/link?url=AKimjI22GZyEW5mDbHb-GUYbSB8L6_cd898Ff2A47oy&wd=&eqid=92a3773d0067d2de000000035f5d78a6; " +
                "CNZZDATA452871=cnzz_eid%3D1652270868-1599913104-https%253A%252F%252Fwww.baidu.com%252F%26ntime%3D1599957689; " +
                "Hm_lvt_5ca35bf8a591fd43aee73e87a5bc0283=1599917556,1599918691,1599961265; Hm_lpvt_5ca35bf8a591fd43aee73e87a5bc0283=1599961265; " +
                "CNZZDATA836142=cnzz_eid%3D1176908316-1599916251-https%253A%252F%252Fwww.baidu.com%252F%26ntime%3D1599960542;" +
                " __tins__5904987=%7B%22sid%22%3A%201599961265655%2C%20%22vd%22%3A%201%2C%20%22expires%22%3A%201599963065655%7D; __51cke__=; __51laig__=1");
        connection.header("Host", "www.lssdjt.com");
        connection.header("Upgrade-Insecure-Requests", "1");
        connection.header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) " +
                "Chrome/85.0.4183.102 Safari/537.36");
        //返回connection
        return connection;
    }

    /**
     * 爬虫执行的方法
     *
     * @param clazz
     * @param url
     * @param obj
     * @return
     * @throws Exception
     */
    public static Object execute(Class<?> clazz, String url, Object obj) throws Exception {
        //反射获取爬虫实体类上的JsoupDocument注解
        JsoupDocument jsoupDocument = clazz.getAnnotation(JsoupDocument.class);
        if (jsoupDocument == null) {
            throw new RuntimeException("该类不是一个爬虫实体类!");
        }
        //通过注解获取目标Url
        String targetUrl = jsoupDocument.targetUrl();
        if (StringUtils.isNotBlank(targetUrl)) {
            url = targetUrl;
        }
        if (StringUtils.isBlank(url)) {
            throw new RuntimeException("待爬取目标Url不能为空!");
        }
        //获取connection
        Connection connection = getConnection(url);
        //获取爬取页面的body部分
        Connection.Method method = jsoupDocument.method();
        Element body = connection.method(method).execute().parse().body();
        String cssQuery = jsoupDocument.cssQuery();

        //获取爬虫实体类的字段属性
        Field[] declaredFields = clazz.getDeclaredFields();
        //根据obj的类型，区分是爬取单个对象还是集合对象
        if (obj instanceof List) {
            return handlerList(clazz, obj, body, cssQuery, declaredFields);
        } else {
            return handlerObject(obj, body, cssQuery, declaredFields);
        }
    }

    /**
     * 处理单个对象
     *
     * @param obj
     * @param body
     * @param cssQuery
     * @param declaredFields
     * @return
     * @throws Exception
     */
    private static Object handlerObject(Object obj, Element body, String cssQuery, Field[] declaredFields) throws Exception {
        if (StringUtils.isBlank(cssQuery)) {
            setFieldValue(obj, declaredFields, body);
            return obj;
        } else {
            Elements liElements = body.select(cssQuery);
            int size = liElements.size();
            if (size == 0) {
                return obj;
            }
            Element element = liElements.get(0);
            setFieldValue(obj, declaredFields, element);
            return obj;
        }

    }

    /**
     * 处理集合
     *
     * @param clazz
     * @param obj
     * @param body
     * @param cssQuery
     * @param declaredFields
     * @return
     * @throws Exception
     */
    private static Object handlerList(Class<?> clazz, Object obj, Element body, String cssQuery, Field[] declaredFields) throws Exception {
        List list = (List) obj;
        Elements elements = body.select(cssQuery);
        for (Element element : elements) {
            Object object = clazz.newInstance();
            setFieldValue(object, declaredFields, element);
            list.add(object);
        }
        return list;
    }

    /**
     * 将爬取结果注入到爬虫实体类的属性中
     *
     * @param obj
     * @param declaredFields
     * @param element
     * @throws Exception
     */
    private static void setFieldValue(Object obj, Field[] declaredFields, Element element) throws Exception {
        for (Field declaredField : declaredFields) {
            //暴力反射
            declaredField.setAccessible(true);
            //处理字段本身就是一个爬虫实体类对象的情况
            JsoupDocument fieldJsoupDocument = declaredField.getAnnotation(JsoupDocument.class);
            if (fieldJsoupDocument != null) {
                //获取字段的Class，且反射获取其对象
                Class<?> fieldType = declaredField.getType();
                Object object = fieldType.newInstance();
                //获取字段上的hrefAttr，从而获取其目标的Url
                String hrefAttr = fieldJsoupDocument.hrefAttr();
                if (StringUtils.isBlank(hrefAttr)) {
                    throw new RuntimeException("字段" + declaredField.getName() + "上的目标Url字段不能为空!");
                }
                //获取组装对象的hrefAttr字段及其值
                Class<?> parentClazz = obj.getClass();
                Field field = parentClazz.getDeclaredField(hrefAttr);
                field.setAccessible(true);
                String hrefValue = (String) field.get(obj);
                //处理域名
                if (!hrefValue.toLowerCase().startsWith("http")) {
                    String domain = fieldJsoupDocument.domain();
                    hrefValue = domain + hrefValue;
                }
                //递归获取其字段的值（因为如果字段上有JsoupDocument注解，代表其本身也是一个爬虫实体对象，需要执行爬虫方法）
                Object childObject = execute(fieldType, hrefValue, object);
                //将递归的爬虫实体注入到最终大的爬虫实体中
                declaredField.set(obj, childObject);
                continue;
            }
            //处理字段本身就是一个属性的情况
            Extract extract = declaredField.getAnnotation(Extract.class);
            if (extract != null) {
                String filedCssQuery = extract.cssQuery();
                Elements entityElement = element.select(filedCssQuery);
                String attr = extract.attr();
                if (StringUtils.isNotBlank(attr)) {
                    String attrValue = entityElement.attr(attr);
                    declaredField.set(obj, attrValue);
                } else {
                    int contentType = extract.contentType();
                    int length = extract.length();
                    if (contentType == 1) {
                        String text = entityElement.text();
                        //针对获取的字符串内容，只获取前30个字符
                        if (text.length() > length) {
                            text = text.substring(0, length);
                        }
                        declaredField.set(obj, text);
                    } else if (contentType == 2) {
                        String html = entityElement.html();
                        if (html.length() > length) {
                            html = html.substring(0, length);
                        }
                        declaredField.set(obj, html);
                    } else {
                        throw new RuntimeException("字段" + declaredField.getName() + "的contentType只能为1或者2!");
                    }
                }
            }
        }
    }
}
