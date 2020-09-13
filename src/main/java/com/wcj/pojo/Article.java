package com.wcj.pojo;

import com.wcj.annotation.Extract;
import com.wcj.annotation.JsoupDocument;
import lombok.Data;

/**
 * 爬虫爬取的实体类（历史上的今天）
 *
 * @author wcj
 * @Date 2020/9/13 10:51
 * @Version 1.0
 */
@JsoupDocument(domain = "http://www.lssdjt.com", targetUrl = "http://www.lssdjt.com", cssQuery = ".main .list .gong")
@Data
public class Article {

    @Extract(cssQuery = "a em")
    private String time;

    @Extract(cssQuery = "a i")
    private String title;

    @Extract(cssQuery = "a", attr = "href")
    private String link;

    @JsoupDocument(domain = "http://www.lssdjt.com",hrefAttr = "link")
    private ArticleContent articleContent;
}
