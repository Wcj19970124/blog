package com.wcj.config;

import com.wcj.realm.AdminRealm;
import lombok.Data;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.DelegatingFilterProxy;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author wcj
 * @Date 2020/3/24 9:05
 * @Version 1.0
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "shiro")
public class ShiroConfig {

    private static final String SHIRO_FILTER = "shiroFilter";
    private String[] anonUrls;
    private String[] authcUrls;
    private String logoutUrl;
    private String loginUrl;
    private String hashAlgorithmName;
    private int hashIterations;

    /**
     * 注入适配器
     *
     * @return
     */
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        //创建适配器
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        //注入散列方式
        credentialsMatcher.setHashAlgorithmName(hashAlgorithmName);
        //注入散列次数
        credentialsMatcher.setHashIterations(hashIterations);
        return credentialsMatcher;
    }

    /**
     * 注入自定义Realm
     *
     * @param matcher
     * @return
     */
    @Bean
    public AdminRealm adminRealm(HashedCredentialsMatcher matcher) {
        //创建自定义realm
        AdminRealm realm = new AdminRealm();
        //注入凭证适配器
        realm.setCredentialsMatcher(matcher);
        return realm;
    }

    /**
     * 注入安全管理器
     *
     * @param realm
     * @return
     */
    @Bean
    public SecurityManager securityManager(AdminRealm realm) {
        //创建默认安全管理器
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //注入自定义realm
        securityManager.setRealm(realm);
        return securityManager;
    }

    /**
     * shiro过滤器
     *
     * @param securityManager
     * @return
     */
    @Bean(SHIRO_FILTER)
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        //创建shiro过滤器工厂
        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
        //注入安全管理器
        factoryBean.setSecurityManager(securityManager);
        //注入登录路径
        factoryBean.setLoginUrl(loginUrl);
        //创建过滤链
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        //设置放行路径
        if (anonUrls != null && anonUrls.length > 0) {
            for (String anon : anonUrls) {
                filterChainDefinitionMap.put(anon, "anon");
            }
        }
        //设置登出路径
        if (logoutUrl != null) {
            filterChainDefinitionMap.put(logoutUrl, "logout");
        }
        //设置拦截路径
        if (authcUrls != null && authcUrls.length > 0) {
            for (String authc : authcUrls) {
                filterChainDefinitionMap.put(authc, "authc");
            }
        }
        //注入过滤链
        factoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return factoryBean;
    }

    /**
     * 注入委托过滤器
     *
     * @return
     */
    @Bean
    public FilterRegistrationBean<DelegatingFilterProxy> delegatingFilterProxy() {
        //创建注册器
        FilterRegistrationBean<DelegatingFilterProxy> filterRegistrationBean = new FilterRegistrationBean<>();
        //创建代理器
        DelegatingFilterProxy proxy = new DelegatingFilterProxy();
        //注入插件
        proxy.setTargetFilterLifecycle(true);
        //注入委托的过滤器
        proxy.setTargetBeanName(SHIRO_FILTER);
        //注册过滤器
        filterRegistrationBean.setFilter(proxy);
        return filterRegistrationBean;
    }
}
