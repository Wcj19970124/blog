package com.wcj.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @author wcj
 * @Date 2020/3/23 14:26
 * @Version 1.0
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "upload")
public class UploadConfig {

    private String baseUrl;
    private List<String> allowTypes;
}
