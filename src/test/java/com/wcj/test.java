package com.wcj;


import com.wcj.pojo.Article;
import com.wcj.utils.CrawlerUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wcj
 * @Date 2020/3/24 10:46
 * @Version 1.0
 */
public class test {
    public static void main(String[] args) {
        Md5Hash md5Hash = new Md5Hash("admin","admin",2);
        System.out.println(md5Hash);
    }

    @Test
    public void testFramework() throws Exception {
        Article article = (Article) CrawlerUtils.execute(Article.class, "", new Article());
        System.out.println(article);
    }

    @Test
    public void testFrameWorkList() throws Exception {
        List<Article> articleList  = (List<Article>) CrawlerUtils.execute(Article.class, "", new ArrayList<Article>());
        for (Article article : articleList) {
            System.out.println(article);
        }
    }
}
