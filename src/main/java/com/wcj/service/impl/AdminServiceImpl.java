package com.wcj.service.impl;

import com.wcj.mapper.AdminMapper;
import com.wcj.pojo.Admin;
import com.wcj.service.AdminService;
import com.wcj.utils.Md5;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wcj
 * @Date 2020/3/23 15:07
 * @Version 1.0
 */
@Service
@Slf4j
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    /**
     * 根据用户名查询管理员信息
     *
     * @param userName
     * @return
     */
    @Override
    public Admin getAdminByUserName(String userName) {
        return adminMapper.getAdminByUserName(userName);
    }

    /**
     * 获取用户信息
     *
     * @return
     */
    @Override
    public Admin getInfo() {
        return adminMapper.getInfo();
    }

    /**
     * 修改管理员信息
     *
     * @param admin
     */
    @Override
    public void updateAdmin(Admin admin) {
        adminMapper.updateAdmin(admin);
    }

    /**
     * 修改管理员密码
     *
     * @param admin
     */
    @Override
    public void updatePwd(Admin admin) {
        //先查再改
        Admin oldAdmin = adminMapper.getInfo();
        oldAdmin.setPassword(Md5.toMd5(admin));
        adminMapper.updateAdmin(oldAdmin);
    }

}
