package com.wcj.controller;

import com.wcj.enums.ResultEnum;
import com.wcj.pojo.Log;
import com.wcj.service.LogService;
import com.wcj.utils.Page;
import com.wcj.utils.Result;
import com.wcj.utils.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * @author wcj
 * @Date 2020/3/25 18:04
 * @Version 1.0
 */
@Api(tags = "日志")
@RestController
@RequestMapping("log")
public class LogController {

    @Autowired
    private LogService logService;

    /**
     * 分页查询日志
     *
     * @param page
     * @return
     */
    @PostMapping("/list")
    @ApiOperation("分页查询日志")
    public Result<Page<Log>> getLogList(@RequestBody Page<Log> page) {
        String sortColumn = page.getSortColumn();
        if (StringUtils.isNotBlank(sortColumn)) {
            String[] sortColumns = {"log_url", "log_status", "log_method", "log_ip", "created_time"};
            List<String> sortColumnList = Arrays.asList(sortColumns);
            if (!sortColumnList.contains(sortColumn)) {
                return new Result<>(ResultEnum.PARAMS_ERROR.getCode(), "查询参数不合法!");
            }
        }
        page = logService.getLogList(page);
        return new Result<>(page);
    }

    /**
     * 根据id删除日志
     *
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    @ApiOperation("根据id删除日志")
    public Result<Object> deleteById(@PathVariable Integer id) {
        logService.deleteById(id);
        return new Result<>("删除成功!");
    }

    /**
     * 根据id集合删除日志
     *
     * @param idList
     * @return
     */
    @DeleteMapping("/delete")
    @ApiOperation("根据id集合删除日志")
    public Result<Object> deleteByIdList(@RequestBody List<Integer> idList) {
        logService.deleteByIdList(idList);
        return new Result<>("删除成功!");
    }
}
