package com.wcj.dao;

import com.wcj.pojo.BlogCollection;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 博客收藏dao层
 * @author wcj
 * @Date 2020/4/3 14:33
 * @Version 1.0
 */
@Repository
public interface BlogCollectionDao extends MongoRepository<BlogCollection,Integer> {

    /**
     * 根据用户id和博客id查询收藏数量
     * @param userId
     * @param blogId
     * @return
     */
    int countByUserIdEqualsAndBlogIdEquals(Integer userId,String blogId);

    /**
     * 根据用户id查询用户收藏博客
     * @param userId
     * @return
     */
    List<BlogCollection> findAllByUserIdEquals(Integer userId);

    /**
     * 根据用户id查询用户的博客收藏数
     * @param userId
     * @return
     */
    int countByUserIdEquals(Integer userId);
}
