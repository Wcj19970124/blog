package com.wcj.service.impl;

import com.wcj.mapper.AdminMapper;
import com.wcj.pojo.Admin;
import com.wcj.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wcj
 * @Date 2020/3/23 15:07
 * @Version 1.0
 */
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    /**
     * 根据用户名查询管理员信息
     * @param userName
     * @return
     */
    @Override
    public Admin getAdminByUserName(String userName) {
         return adminMapper.getAdminByUserName(userName);
    }

}
