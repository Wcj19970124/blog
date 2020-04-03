package com.wcj.service;


import com.wcj.pojo.Comment;
import com.wcj.pojo.CommentGoods;

import java.util.List;

/**
 * @author wcj
 * @Date 2020/3/23 15:05
 * @Version 1.0
 */
public interface CommentService {

    /**
     * 发表评论
     *
     * @param comment
     */
    void addComment(Comment comment);

    /**
     * 根据博客查询评论
     *
     * @param blog
     * @return
     */
    List<Comment> getComment(String blog);

    /**
     * 根据id删除评论
     *
     * @param id
     */
    void deleteComment(String id);

    /**
     * 给评论点赞
     * @param commentGoods
     */
    void commentGood(CommentGoods commentGoods);

    /**
     * 根据用户id和博客id查询博客的评论情况
     * @param blogId
     * @return
     */
    List<Comment> getCommentGoods(String blogId);
}
