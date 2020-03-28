package com.wcj.service.impl;

import com.wcj.excel.entity.ExportParams;
import com.wcj.excel.handler.ExcelExportHandler;
import com.wcj.mapper.LogMapper;
import com.wcj.pojo.Log;
import com.wcj.service.LogService;
import com.wcj.utils.Page;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author wcj
 * @Date 2020/3/23 15:07
 * @Version 1.0
 */
@Service
@Slf4j
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

    /**
     * 分页查询日志
     *
     * @param page
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Page<Log> getLogList(Page<Log> page) {
        //查询日志数据
        List<Log> logList = logMapper.getLogList(page);
        page.setList(logList);
        //查询日志总条数
        int totalCount = logMapper.getCountByPage(page);
        page.setTotalCount(totalCount);
        return page;
    }

    /**
     * 根据id删除日志
     *
     * @param id
     */
    @Override
    public void deleteById(Integer id) {
        logMapper.deleteById(id);
    }

    /**
     * 根据id集合删除日志
     *
     * @param idList
     */
    @Override
    public void deleteByIdList(List<Integer> idList) {
        logMapper.deleteByIdList(idList);
    }

    /**
     * 导出全部的日志到Excel
     * @return
     */
    @Override
    public Workbook exportLog() {
        //获取所有的日志信息
        List<Log> logList = logMapper.getAll();
        return new ExcelExportHandler().createSheet(new ExportParams("测试导出", "最新日志"), Log.class, logList);
    }
}
