package com.wcj.service.impl;

import com.wcj.mapper.LogMapper;
import com.wcj.pojo.Log;
import com.wcj.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wcj
 * @Date 2020/3/23 15:07
 * @Version 1.0
 */
@Service
public class LogServiceImpl implements LogService {

    @Autowired
    private LogMapper logMapper;

    /**
     * 保存日志信息
     * @param logger
     * @return
     */
    @Override
    public void save(Log logger) {
        logMapper.save(logger);
    }
}
