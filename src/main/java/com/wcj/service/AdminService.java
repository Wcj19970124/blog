package com.wcj.service;

import com.wcj.pojo.Admin;

/**
 * @author wcj
 * @Date 2020/3/23 15:05
 * @Version 1.0
 */
public interface AdminService {

    /**
     * 根据用户名查询管理员信息
     * @param userName
     * @return
     */
    Admin getAdminByUserName(String userName);

    /**
     * 获取用户信息
     * @return
     */
    Admin getInfo();

    /**
     * 修改管理员信息
     * @param admin
     */
    void updateAdmin(Admin admin);

    /**
     * 修改管理员密码
     * @param admin
     */
    void updatePwd(Admin admin);
}
