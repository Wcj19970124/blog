package com.wcj.service;

import com.wcj.pojo.User;
import com.wcj.utils.Page;

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
}
