package com.wcj.pojo;

import com.wcj.annotation.Extract;
import com.wcj.annotation.JsoupDocument;
import lombok.Data;

/**
 * @author wcj
 * @Date 2020/9/13 10:57
 * @Version 1.0
 */
@JsoupDocument(domain = "http://www.lssdjt.com")
@Data
public class ArticleContent {

    @Extract(cssQuery = ".post_public.content.mt5.clearfix")
    private String content;
}
