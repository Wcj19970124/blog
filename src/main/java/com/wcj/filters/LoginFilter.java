package com.wcj.filters;

import com.alibaba.fastjson.JSON;
import com.wcj.enums.ResultEnum;
import com.wcj.utils.Result;
import org.apache.shiro.web.filter.authc.UserFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

/**
 * 用于处理登陆失效
 * @author wcj
 * @Date 2020/4/4 10:56
 * @Version 1.0
 */
public class LoginFilter extends UserFilter {

    /**
     * 因为此项目为前后端分离项目，但是shiro是用于非前后端分离项目的
     * 所以在登录失效的时候，shiro默认的处理是跳转到登录页，但是该操作
     * 并不会影响到前端，所以我们需要重写此方法，返回一个状态码给前端
     * 让前端做跳转
     * @param request
     * @param response
     * @throws IOException
     */
    @Override
    protected void redirectToLogin(ServletRequest request, ServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(JSON.toJSONString(new Result<>(ResultEnum.NOT_LOGIN)));
    }
}
