package com.wcj.service.impl;

import com.wcj.enums.ResultEnum;
import com.wcj.exception.BlogException;
import com.wcj.mapper.UserMapper;
import com.wcj.pojo.User;
import com.wcj.service.UserService;
import com.wcj.utils.Md5;
import com.wcj.utils.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
        user.setPassword(Md5.toMd5(user,user.getPassword()));
        userMapper.insertUser(user);
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
}
