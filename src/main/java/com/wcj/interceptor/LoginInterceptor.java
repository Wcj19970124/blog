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
     * 白名单
     */
    private static String[] whiteList = {
            "/admin/login", "/user/login","/link/list", "/music/listFront", "/blog/read", "/admin/getInfo", "/type/listFront"
            ,"/about/read","/blog/recommendRead","/blog/list","/blog/getTimeLine","/user/register","/comment/getComment"
            ,"/rotation-picture/listFront"
    };

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
        if (containsWhiteList(request.getRequestURI())) {
            return true;
        }
        String token = request.getHeader("Authorization");
        if(StringUtils.isNotBlank(token)){
            Object loginUser = ShiroUtils.getLoginUser();
            if(loginUser!=null){
                return true;
            }
        }
        throw new BlogException(ResultEnum.NOT_LOGIN);
    }

    /**
     * 判断访问路径是否在白名单中
     *
     * @param url
     * @return
     */
    public boolean containsWhiteList(String url) {
        for (String str : whiteList) {
            if (url.contains(str)) {
                return true;
            }
        }
        return false;
    }
}
