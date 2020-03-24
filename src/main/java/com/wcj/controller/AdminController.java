package com.wcj.controller;

import com.wcj.enums.ResultEnum;
import com.wcj.exception.BlogException;
import com.wcj.pojo.Admin;
import com.wcj.service.AdminService;
import com.wcj.utils.Result;
import com.wcj.utils.ShiroUtils;
import com.wcj.utils.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * admin前端控制器
 * @author wcj
 * @Date 2020/3/24 9:35
 * @Version 1.0
 */
@Api(tags = "管理员")
@RestController
@RequestMapping("admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    /**
     * admin登录
     *
     * @param admin
     * @return
     */
    @PostMapping("/login")
    @ApiOperation(value = "登录")
    public Result<Object> login(@RequestBody Admin admin) {
        //参数检验
        if (admin == null || StringUtils.isBlank(admin.getUsername()) || StringUtils.isBlank(admin.getPassword())) {
            throw new BlogException(ResultEnum.PARAMS_NULL.getCode(), "用户名或密码错误!");
        }
        //登录
        Subject subject = SecurityUtils.getSubject();
        AuthenticationToken token = new UsernamePasswordToken(admin.getUsername(), admin.getPassword());
        try {
            subject.login(token);
        } catch (Exception e) {
            throw new BlogException(ResultEnum.ERROR.getCode(), "用户名或密码错误!");
        }
        //登录成功，记录用户信息
        Serializable sessionId = subject.getSession().getId();
        Map<String, Object> returnMap = new HashMap<>(2);
        returnMap.put("token", sessionId);
        return new Result<>(returnMap);
    }

    /**
     * 获取登录用户信息
     *
     * @return
     */
    @GetMapping("/info")
    @ApiOperation(value = "获取登录用户信息")
    public Result<Admin> getAdminInfo() {
        Admin loginAdmin = ShiroUtils.getLoginAdmin();
        loginAdmin.setPassword("");
        return new Result<>(loginAdmin);
    }

    /**
     * 获取管理员信息
     * @return
     */
    @GetMapping("/getInfo")
    @ApiOperation("获取管理员信息")
    public Result<Admin> getInfo(){
        Admin admin = adminService.getInfo();
        return new Result<>(admin);
    }

    /**
     * 修改管理员信息
     * @param admin
     * @return
     */
    @PutMapping("/update")
    @ApiOperation("修改管理员信息")
    public Result<Object> updateAdmin(@RequestBody Admin admin){
        adminService.updateAdmin(admin);
        return new Result<>("修改成功!");
    }

    /**
     * 修改管理员密码
     * @param admin
     * @return
     */
    @PutMapping("/updatePwd")
    @ApiOperation("修改管理员密码")
    public Result<Object> updatePwd(@RequestBody Admin admin){
        adminService.updatePwd(admin);
        return new Result<>("修改成功!");
    }
}
