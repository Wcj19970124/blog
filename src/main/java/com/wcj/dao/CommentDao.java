package com.wcj.dao;

import com.wcj.pojo.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author wcj
 * @Date 2020/4/3 8:46
 * @Version 1.0
 */
@Repository
public interface CommentDao extends MongoRepository<Comment,String> {

    /**
     * 查询博客评论
     * 再根据创建时间和评论点赞数进行排序
     * @param commentBlog
     * @return
     */
    List<Comment> findAllByCommentBlogOrderByCreatedTimeDescCommentGoodDesc(String commentBlog);
}
