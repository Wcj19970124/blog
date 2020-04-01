package com.wcj.mapper;

import com.wcj.pojo.Blog;
import com.wcj.utils.Page;
import com.wcj.vo.BlogVo;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * 博客表Mapper
 *
 * @author wcj
 * @date 2020-02-07 14:04:12
 * @Version 1.0
 */
@Component
public interface BlogMapper {

    /**
     * 添加博客
     * @param blog
     */
    void addBlog(Blog blog);

    /**
     * 修改博客
     * @param blog
     */
    void updateBlog(Blog blog);

    /**
     * 根据id查询博客
     * @param id
     * @return
     */
    Blog getBlog(String id);

    /**
     * 删除博客
     * @param id
     */
    void deleteBlog(String id);

    /**
     * 分页查询博客列表
     * @param page
     * @return
     */
    List<BlogVo> getBlogList(Page<BlogVo> page);

    /***
     * 分页查询博客总条数
     * @param page
     * @return
     */
    int getCountByPage(Page<BlogVo> page);

    /**
     * 前台推荐阅读
     * @return
     */
    List<BlogVo> recommendRead();

    /**
     * 获取时间轴数据
     * @return
     */
    List<BlogVo> getBlogVoList();
}
