package com.wcj.service;

import com.wcj.pojo.Log;
import com.wcj.utils.Page;
import org.apache.poi.ss.usermodel.Workbook;

import java.util.List;

/**
 * @author wcj
 * @Date 2020/3/23 15:05
 * @Version 1.0
 */
public interface LogService {

    /**
     * 保存日志信息
     * @param logger
     * @return
     */
    void save(Log logger);

    /**
     * 分页查询日志
     *
     * @param page
     * @return
     */
    Page<Log> getLogList(Page<Log> page);

    /**
     * 根据id删除日志
     *
     * @param id
     */
    void deleteById(Integer id);

    /**
     * 根据id集合删除日志
     *
     * @param idList
     */
    void deleteByIdList(List<Integer> idList);

    /**
     * 导出全部的日志到Excel
     * @return
     */
    Workbook exportLog();
}
