package com.wcj.realm;

import com.wcj.enums.ResultEnum;
import com.wcj.exception.BlogException;
import com.wcj.pojo.Admin;
import com.wcj.service.AdminService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 管理员登录认证
 *
 * @author wcj
 * @Date 2020/3/24 8:49
 * @Version 1.0
 */
public class AdminRealm extends AuthorizingRealm {

    @Autowired
    private AdminService adminService;

    /**
     * 认证方法
     *
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
        String userName = usernamePasswordToken.getUsername();
        Admin admin = adminService.getAdminByUserName(userName);
        if (admin == null) {
            throw new BlogException(ResultEnum.ERROR.getCode(), "用户不存在！");
        }
        //构建盐值
        ByteSource credentialSalt = ByteSource.Util.bytes(admin.getUsername());
        return new SimpleAuthenticationInfo(admin, admin.getPassword(), credentialSalt, this.getName());
    }

    /**
     * 授权方法
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return new SimpleAuthorizationInfo();
    }
}
