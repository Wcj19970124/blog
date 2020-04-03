package com.wcj.service;

import com.wcj.pojo.Comment;
import com.wcj.pojo.User;
import com.wcj.utils.Page;
import com.wcj.vo.BlogVo;

import java.util.List;

/**
 * @author wcj
 * @Date 2020/3/23 15:05
 * @Version 1.0
 */
public interface UserService {

    /**
     * 添加用户
     *
     * @param user
     */
    void addUser(User user);

    /**
     * 修改用户
     *
     * @param user
     */
    void updateUser(User user);

    /**
     * 根据id查询用户
     *
     * @param id
     * @return
     */
    User getUser(Integer id);

    /**
     * 分页查询用户列表
     *
     * @param page
     * @return
     */
    Page<User> getUserList(Page<User> page);

    /**
     * 根据id删除用户
     *
     * @param id
     */
    void deleteUser(Integer id);

    /**
     * 批量重置密码
     * @param userIds
     */
    void resetPwd(List<Integer> userIds);

    /**
     * 前台注册用户
     * @param user
     */
    void register(User user);

    /**
     * 根据用户名查询用户信息
     * @param userName
     * @return
     */
    User getUserByUserName(String userName);

    /**
     * 根据用户id分页查询用户的收藏博客列表
     * @param page
     * @return
     */
    Page<BlogVo> getCollection(Page<BlogVo> page);

    /**
     * 根据用户id分页查询用户的所有评论列表
     * @param page
     * @return
     */
    Page<Comment> getComment(Page<Comment> page);
}
