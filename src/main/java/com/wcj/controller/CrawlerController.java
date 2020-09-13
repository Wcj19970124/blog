package com.wcj.controller;

import com.wcj.pojo.Article;
import com.wcj.utils.CrawlerUtils;
import com.wcj.utils.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * 前台爬虫（历史上的今天信息）
 *
 * @author wcj
 * @Date 2020/9/13 16:03
 * @Version 1.0
 */
@RestController
@RequestMapping("crawler")
public class CrawlerController {

    @GetMapping("/getArticleList")
    @ApiOperation("获取历史上的今天爬虫爬取数据")
    public Result<List<Article>> getCrawlerArticle() throws Exception {
        List<Article> articleList = (List<Article>) CrawlerUtils.execute(Article.class, "", new ArrayList<Article>());
        //处理时间格式，只保留年份
        for (Article article : articleList) {
            String time = new SimpleDateFormat("yyyy年").format(new SimpleDateFormat("yyyy年MM月dd日").parse(article.getTime()));
            //将年份前面多余的0去除
            int size = 0;
            for (int i = 0; i < time.length(); i++) {
                if (time.charAt(i) != '0') {
                    size = i;
                    break;
                }
            }
            time = time.substring(size);
            article.setTime(time);
        }
        return new Result<>(articleList);
    }
}
