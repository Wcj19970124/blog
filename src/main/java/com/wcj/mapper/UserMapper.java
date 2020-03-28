package com.wcj.mapper;

import com.wcj.pojo.User;
import com.wcj.utils.Page;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * 用户表Mapper
 *
 * @author wcj
 * @date
 * @Version 1.0
 *
 */
@Component
public interface UserMapper {

    /**
     * 添加用户
     * @param user
     */
    void addUser(User user);

    /**
     * 修改用户
     * @param user
     */
    void updateUser(User user);

    /**
     * 根据id查询用户
     * @param id
     * @return
     */
    User getUser(Integer id);

    /**
     * 分页查询用户列表
     * @param page
     * @return
     */
    List<User> getUserList(Page<User> page);

    /**
     * 分页查询用户总数
     * @param page
     * @return
     */
    int getCountByPage(Page<User> page);

    /**
     * 根据id删除用户
     * @param id
     */
    void deleteUser(Integer id);

    /**
     * 获取用户列表
     * @param userIds
     * @return
     */
    List<User> getUsers(List<Integer> userIds);
}
