package com.wcj.mapper;

import com.wcj.pojo.Log;
import org.apache.ibatis.annotations.Insert;
import org.springframework.stereotype.Component;


/**
 * 接口访问日志表Mapper
 *
 * @author wcj
 * @date
 * @Version 1.0
 *
 */
@Component
public interface LogMapper {

    /**
     * 保存日志信息
     * @param logger
     */
    @Insert("insert into bl_log(log_url,log_params,log_status,log_message,log_method,log_time,log_result,log_ip) values(#{logUrl},#{logParams},#{logStatus},#{logMessage},#{logMethod},#{logTime},#{logResult},#{logIp})")
    void save(Log logger);
}
