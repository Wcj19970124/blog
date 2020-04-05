package com.wcj.dao;

import com.wcj.pojo.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
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

    /**
     * 根据用户id查询用户的评论数
     * @param userId
     * @return
     */
    int countByCommentUserEquals(Integer userId);

    /**
     * 根据博客标题和用户昵称查询评论
     * @param comment
     * @param pageable
     * @return
     */
    @Query("{'blog.blogTitle':{$regex:?#{[0].blog.blogTitle}},'user.nickname':{$regex:?#{[0].user.nickname}}}")
    Page<Comment> getCommentByBlogTitleAndNickname(Comment comment, Pageable pageable);
}
