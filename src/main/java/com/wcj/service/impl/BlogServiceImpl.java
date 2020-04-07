package com.wcj.service.impl;

import com.wcj.dao.BlogCollectionDao;
import com.wcj.dao.GoodsDao;
import com.wcj.enums.ResultEnum;
import com.wcj.exception.BlogException;
import com.wcj.mapper.BlogMapper;
import com.wcj.mapper.TypeMapper;
import com.wcj.mapper.UserMapper;
import com.wcj.pojo.*;
import com.wcj.service.BlogService;
import com.wcj.utils.*;
import com.wcj.vo.BlogPopularStatistic;
import com.wcj.vo.BlogStatistic;
import com.wcj.vo.BlogVo;
import com.wcj.vo.TimeLineVo;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

    @Autowired
    private GoodsDao goodsDao;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private BlogCollectionDao collectionDao;

    /**
     * 添加博客
     *
     * @param blog
     */
    @Override
    public void addBlog(Blog blog) {
        //先根据博客类别查询出类别信息
        //将博客对应类别博客数加1
        Type type = typeMapper.getType(blog.getBlogType());
        type.setTypeBlogCount(type.getTypeBlogCount()+1);
        typeMapper.updateType(type);
        //保存博客
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

    /**
     * 前台推荐阅读
     * @return
     */
    @Override
    public List<BlogVo> recommendRead() {
        return blogMapper.recommendRead();
    }

    /**
     * 获取时间轴
     * @return
     */
    @Override
    public List<TimeLineVo> getTimeLine() {
        //获取时间轴数据
        List<BlogVo> blogVoList = blogMapper.getBlogVoList();
        //创建返回时间轴集合
        List<TimeLineVo> timeLineVoList = new ArrayList<>(16);
        blogVoList.forEach(e->{
            //获取月份
            String month = e.getBlogMonth();
            //创建备用时间轴对象
            TimeLineVo timeLineVo = new TimeLineVo();
            //添加月份
            timeLineVo.setMonth(month);
            if(timeLineVoList.contains(timeLineVo)){
                TimeLineVo timeLine = TimeLineUtils.getTimeLineVo(timeLineVoList,timeLineVo);
                List<BlogVo> blogVoList1 = timeLine.getBlogVoList();
                if(blogVoList1==null){
                    blogVoList1 = new ArrayList<>(8);
                }
                blogVoList1.add(e);
                timeLine.setBlogVoList(blogVoList1);
            }else{
                List<BlogVo> blogVoList1 = timeLineVo.getBlogVoList();
                if(blogVoList1==null){
                    blogVoList1 = new ArrayList<>(8);
                }
                blogVoList1.add(e);
                timeLineVo.setBlogVoList(blogVoList1);
                timeLineVoList.add(timeLineVo);
            }
        });
        return timeLineVoList;
    }

    /**
     * 博客点赞
     * @param goods
     */
    @Override
    public void goodBlog(Goods goods) {
        //先查询是否已经点赞
        int count = goodsDao.countByUserIdEqualsAndBlogIdEquals(goods.getUserId(),goods.getBlogId());
        if(count>0){
            throw new BlogException(ResultEnum.ERROR.getCode(),"已点赞，不能重复点赞!");
        }
        //先查询博客，再将博客点赞数加1
        Blog blog = blogMapper.getBlog(goods.getBlogId());
        blog.setBlogGoods(blog.getBlogGoods()+1);
        blogMapper.updateBlog(blog);
        //再存储点赞信息
        goods.setId(idWorker.nextId()+"");
        goodsDao.save(goods);
    }

    /**
     * 根据用户id和博客id查询点赞数
     * @param blogId
     * @return
     */
    @Override
    public int getGoods(String blogId) {
        User user = (User) ShiroUtils.getLoginUser();
        return goodsDao.countByUserIdEqualsAndBlogIdEquals(user.getUserId(),blogId);
    }

    /**
     * 收藏博客
     * @param blogCollection
     */
    @Override
    public void blogCollection(BlogCollection blogCollection) {
        //先查询用户是否已经收藏
        int count = collectionDao.countByUserIdEqualsAndBlogIdEquals(blogCollection.getUserId(),blogCollection.getBlogId());
        if(count>0){
            throw new BlogException("已收藏，不能重复收藏!");
        }
        blogCollection.setCollectionId(idWorker.nextId()+"");
        blogCollection.setCollectionTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        //查询用户
        User user = userMapper.getUser(blogCollection.getUserId());
        blogCollection.setUser(user);
        //查询博客,将博客收藏数加1
        Blog blog = blogMapper.getBlog(blogCollection.getBlogId());
        blog.setBlogCollection(blog.getBlogCollection()+1);
        blogMapper.updateBlog(blog);
        //保存收藏记录
        blogCollection.setBlog(blog);
        collectionDao.save(blogCollection);
    }

    /**
     * 根据用户id和博客id查询收藏
     * @param blogId
     * @return
     */
    @Override
    public int getCollection(String blogId) {
        User user = (User) ShiroUtils.getLoginUser();
        return collectionDao.countByUserIdEqualsAndBlogIdEquals(user.getUserId(),blogId);
    }

    /**
     * 统计每月份发表的博客数
     * @return
     */
    @Override
    public List<BlogStatistic> getBlogStatistic() {
        return blogMapper.getBlogStatistic();
    }

    /**
     * 统计最受欢迎前五博客
     * @return
     */
    @Override
    public List<BlogPopularStatistic> getBlogPopularStatistic() {
        return blogMapper.getBlogPopularStatistic();
    }

}
