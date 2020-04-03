package com.wcj.dao;

import com.wcj.pojo.Goods;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * 博客点赞dao层接口
 *
 * @author wcj
 * @Date 2020/4/3 12:54
 * @Version 1.0
 */
@Repository
public interface GoodsDao extends MongoRepository<Goods, String> {

    /**
     * 根据用户id和博客id查询点赞数
     * @param userId
     * @param blogId
     * @return
     */
    int countByUserIdEqualsAndBlogIdEquals(Integer userId,String blogId);
}
