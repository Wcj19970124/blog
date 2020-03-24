package com.wcj.interceptor;

import com.wcj.enums.ResultEnum;
import com.wcj.exception.BlogException;
import com.wcj.utils.ShiroUtils;
import com.wcj.utils.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author wcj
 * @Date 2020/3/24 10:06
 * @Version 1.0
 */
public class LoginInterceptor implements HandlerInterceptor {

    /**
     * controller执行之前拦截
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("Authorization");
        if(StringUtils.isNotBlank(token)){
            Object loginAdmin = ShiroUtils.getLoginAdmin();
            if(loginAdmin!=null){
                return true;
            }
        }
        throw new BlogException(ResultEnum.NOT_LOGIN);
    }
}
