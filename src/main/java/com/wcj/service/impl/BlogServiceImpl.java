package com.wcj.service.impl;

import com.wcj.mapper.BlogMapper;
import com.wcj.mapper.TypeMapper;
import com.wcj.pojo.Blog;
import com.wcj.pojo.Type;
import com.wcj.service.BlogService;
import com.wcj.utils.IdWorker;
import com.wcj.utils.Page;
import com.wcj.vo.BlogVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

/**
 * @author wcj
 * @Date 2020/3/23 15:07
 * @Version 1.0
 */
@Service
@Slf4j
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogMapper blogMapper;

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private TypeMapper typeMapper;

    /**
     * 添加博客
     *
     * @param blog
     */
    @Override
    public void addBlog(Blog blog) {
        blog.setBlogId(idWorker.nextId() + "");
        blogMapper.addBlog(blog);
    }

    /**
     * 修改博客
     *
     * @param blog
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateBlog(Blog blog) {
        //先查询修改前博客的类型
        Blog oldBlog = blogMapper.getBlog(blog.getBlogId());
        Type oldType = typeMapper.getType(oldBlog.getBlogType());
        //再查询修改后博客的类型
        Type nowType = typeMapper.getType(blog.getBlogType());
        if (!oldType.getTypeId().equals(nowType.getTypeId())) {
            //将原始类型博客数减1
            oldType.setTypeBlogCount(oldType.getTypeBlogCount() - 1);
            typeMapper.updateType(oldType);
            //再将修改后博客类型的博客数加1
            nowType.setTypeBlogCount(nowType.getTypeBlogCount() + 1);
            typeMapper.updateType(nowType);
        }
        blogMapper.updateBlog(blog);
    }

    /**
     * 根据id查询博客
     *
     * @param id
     * @return
     */
    @Override
    public Blog getBlog(String id) {
        return blogMapper.getBlog(id);
    }

    /**
     * 删除博客
     *
     * @param id
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteBlog(String id) {
        //先查询博客
        Blog blog = blogMapper.getBlog(id);
        blogMapper.deleteBlog(id);
        //将博客对应博客类型的博客数减1
        Type type = typeMapper.getType(blog.getBlogType());
        type.setTypeBlogCount(type.getTypeBlogCount() - 1);
        typeMapper.updateType(type);
    }

    /**
     * 分页查询博客列表
     *
     * @param page
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Page<BlogVo> getBlogList(Page<BlogVo> page) {
        //查询博客列表
        List<BlogVo> blogVoList = blogMapper.getBlogList(page);
        page.setList(blogVoList);
        //查询总条数
        int totalCount = blogMapper.getCountByPage(page);
        page.setTotalCount(totalCount);
        return page;
    }

    /**
     * 按照id阅读博客
     *
     * @param id
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public BlogVo readBlog(String id) {
        //先查再修改阅读数
        Blog blog = blogMapper.getBlog(id);
        //修改阅读数
        blog.setBlogRead(blog.getBlogRead() + 1);
        blogMapper.updateBlog(blog);
        //将blog转换为BlogVo
        BlogVo blogVo = new BlogVo();
        BeanUtils.copyProperties(blog, blogVo);
        //查询分类
        Type type = typeMapper.getType(blog.getBlogType());
        blogVo.setTypeName(type.getTypeName());
        return blogVo;
    }
}
