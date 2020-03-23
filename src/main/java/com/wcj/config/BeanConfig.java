package com.wcj.config;

import com.wcj.utils.IdWorker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 用于将一些对象放入IOC容器
 * @author wcj
 * @Date 2020/3/23 13:52
 * @Version 1.0
 */
@Configuration
public class BeanConfig {

    @Bean
    public IdWorker idWorker(){
        return new IdWorker();
    }
}
