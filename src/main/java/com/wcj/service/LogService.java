package com.wcj.service;

import com.wcj.pojo.Log;

/**
 * @author wcj
 * @Date 2020/3/23 15:05
 * @Version 1.0
 */
public interface LogService {

    /**
     * 保存日志信息
     * @param logger
     */
    void save(Log logger);
}
