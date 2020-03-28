package com.wcj.mapper;

import com.wcj.pojo.Log;
import com.wcj.utils.Page;
import org.apache.ibatis.annotations.Insert;
import org.springframework.stereotype.Component;

import java.util.List;


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

    /**
     * 分页查询日志数据
     * @param page
     * @return
     */
    List<Log> getLogList(Page<Log> page);

    /**
     * 分页查询日志总条数
     * @param page
     * @return
     */
    int getCountByPage(Page<Log> page);

    /**
     * 根据id删除日志
     * @param id
     */
    void deleteById(Integer id);

    /**
     * 根据id集合删除日志
     * @param idList
     */
    void deleteByIdList(List<Integer> idList);

    /**
     * 导出到Excel，查询所有的日志信息
     * @return
     */
    List<Log> getAll();
}
