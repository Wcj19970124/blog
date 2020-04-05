package com.wcj.service.impl;

import com.wcj.dao.BlogCollectionDao;
import com.wcj.dao.CommentDao;
import com.wcj.enums.ResultEnum;
import com.wcj.exception.BlogException;
import com.wcj.mapper.TypeMapper;
import com.wcj.mapper.UserMapper;
import com.wcj.pojo.*;
import com.wcj.service.UserService;
import com.wcj.utils.Md5;
import com.wcj.utils.Page;
import com.wcj.utils.ShiroUtils;
import com.wcj.vo.BlogVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wcj
 * @Date 2020/3/23 15:07
 * @Version 1.0
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private BlogCollectionDao collectionDao;

    @Autowired
    private TypeMapper typeMapper;

    @Autowired
    private CommentDao commentDao;

    /**
     * 添加用户
     *
     * @param user
     */
    @Override
    public void addUser(User user) {
        user.setPassword(Md5.toMd5(user));
        userMapper.addUser(user);
    }

    /**
     * 修改用户
     *
     * @param user
     */
    @Override
    public void updateUser(User user) {
        userMapper.updateUser(user);
    }

    /**
     * 根据id查询用户
     *
     * @param id
     * @return
     */
    @Override
    public User getUser(Integer id) {
        return userMapper.getUser(id);
    }

    /**
     * 分页查询用户列表
     *
     * @param page
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Page<User> getUserList(Page<User> page) {
        //先查用户数据
        List<User> userList = userMapper.getUserList(page);
        page.setList(userList);
        //再查数据总条数
        int totalCount = userMapper.getCountByPage(page);
        page.setTotalCount(totalCount);
        return page;
    }

    /**
     * 根据id删除用户
     *
     * @param id
     */
    @Override
    public void deleteUser(Integer id) {
        userMapper.deleteUser(id);
    }

    /**
     * 批量重置密码
     * @param userIds
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void resetPwd(List<Integer> userIds) {
        //重置密码为123456
        //先查再重置，这样可以使用乐观锁，
        //保证数据的完整性和不会出现脏数据
        List<User> userList = userMapper.getUsers(userIds);
        //然后遍历修改
        userList.forEach(e->{
            e.setPassword(Md5.toMd5(e,"123456"));
            userMapper.updateUser(e);
        });
    }

    /**
     * 前台注册用户
     * @param user
     */
    @Override
    public void register(User user) {
        //先根据用户名查询用户
        User u = userMapper.getUserByUserName(user.getUsername());
        //如果存在,抛出异常
        if(u!=null){
            throw new BlogException(ResultEnum.PARAMS_ERROR.getCode(),"用户已存在!");
        }
        //如果不存在，则insert
        //明文密码加密
        user.setPassword(Md5.toMd5(user));
        user.setName("");
        userMapper.addUser(user);
    }

    /**
     * 根据用户名查询用户信息
     * @param userName
     * @return
     */
    @Override
    public User getUserByUserName(String userName) {
        return userMapper.getUserByUserName(userName);
    }

    /**
     * 根据用户id分页查询用户的收藏博客列表
     * @param page
     * @return
     */
    @Override
    public Page<BlogVo> getCollection(Page<BlogVo> page) {
        //先构造构条件构造器
        User user = (User) ShiroUtils.getLoginUser();
        BlogCollection blogCollection = new BlogCollection();
        blogCollection.setUserId(user.getUserId());
        Example<BlogCollection> example = Example.of(blogCollection);
        //再构造分页构造器
        Pageable pageable = PageRequest.of(page.getCurrentPage()-1,page.getPageSize());
        //再查询博客收藏记录
        org.springframework.data.domain.Page<BlogCollection> blogCollections = collectionDao.findAll(example, pageable);
        //再将博客收藏记录封装成Blog列表
        List<Blog> blogList = new ArrayList<>();
        blogCollections.forEach(e->{
            blogList.add(e.getBlog());
        });
        //遍历blogList,获取博客类型，封装成BlogVo
        List<BlogVo> blogVoList = new ArrayList<>();
        blogList.forEach(e->{
            BlogVo blogVo = new BlogVo();
            BeanUtils.copyProperties(e,blogVo);
            Type type = typeMapper.getType(e.getBlogType());
            blogVo.setTypeName(type.getTypeName());
            blogVoList.add(blogVo);
        });
        //封装page
        page.setList(blogVoList);
        //待优化，将long强转成int不好，可能会有数据精度丢失
        page.setTotalCount((int) blogCollections.getTotalElements());
        page.setTotalPage(blogCollections.getTotalPages());
        return page;
    }

    /**
     * 根据用户id分页查询用户的所有评论列表
     * @param page
     * @return
     */
    @Override
    public Page<Comment> getComment(Page<Comment> page) {
        //先创建条件构造器
        User user = (User) ShiroUtils.getLoginUser();
        Comment comment = new Comment();
        comment.setCommentUser(user.getUserId());
        Example<Comment>example = Example.of(comment);
        //在创建分页构造器
        Pageable pageable = PageRequest.of(page.getCurrentPage()-1,page.getPageSize());
        //查询评论信息
        org.springframework.data.domain.Page<Comment> comments = commentDao.findAll(example, pageable);
        //封装page
        page.setTotalPage(comments.getTotalPages());
        page.setTotalCount((int) comments.getTotalElements());
        page.setList(comments.getContent());
        return page;
    }

    /**
     * 前台修改个人信息
     * @param user
     */
    @Override
    public void updateFront(User user) {
        if(user.getPassword()!=null){
            //加密密码
            user.setPassword(Md5.toMd5(user));
        }
        userMapper.updateFront(user);
    }

    /**
     * 根据用户id查询用户的收藏数和评论数
     * @return
     */
    @Override
    public Map<String, Integer> getCommentAndCollection() {
        User user = (User) ShiroUtils.getLoginUser();
        int commentCount = commentDao.countByCommentUserEquals(user.getUserId());
        int collectionCount = collectionDao.countByUserIdEquals(user.getUserId());
        Map<String,Integer> map = new HashMap<>(4);
        map.put("comment",commentCount);
        map.put("collection",collectionCount);
        return map;
    }

}
