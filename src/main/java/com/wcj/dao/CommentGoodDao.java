package com.wcj.dao;

import com.wcj.pojo.Comment;
import com.wcj.pojo.CommentGoods;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 评论点赞dao层
 * @author wcj
 * @Date 2020/4/3 16:06
 * @Version 1.0
 */
@Repository
public interface CommentGoodDao extends MongoRepository<CommentGoods,String> {

    /**
     * 根据评论id集合查询出来评论的点赞情况
     * @param commentIds
     * @return
     */
    List<CommentGoods> findByCommentIdIn(List<String> commentIds);

    /**
     * 根据用户id和评论id查询用户是否已经对该条评论点赞
     * @param userId
     * @param commentId
     * @return
     */
    int countByUserIdEqualsAndCommentIdEquals(Integer userId,String commentId);
}
