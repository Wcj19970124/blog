package com.wcj.controller;

import com.wcj.enums.ResultEnum;
import com.wcj.pojo.About;
import com.wcj.service.AboutService;
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
 * 关于我的前端控制器
 *
 * @author wcj
 * @Date 2020/3/25 15:01
 * @Version 1.0
 */
@Api(tags = "关于我的")
@RestController
@RequestMapping("about")
public class AboutController {

    @Autowired
    private AboutService aboutService;

    /**
     * 添加关于我的信息
     *
     * @param about
     * @return
     */
    @PostMapping("/add")
    @ApiOperation("添加关于我的信息")
    public Result<Object> addAbout(@RequestBody About about) {
        aboutService.addAbout(about);
        return new Result<>("添加成功!");
    }

    /**
     * 修改关于我的信息
     *
     * @return
     */
    @PutMapping("/update")
    @ApiOperation("修改关于我的信息")
    public Result<Object> updateAbout(@RequestBody About about) {
        aboutService.updateAbout(about);
        return new Result<>("修改成功!");
    }

    /**
     * 根据id查询关于我的
     *
     * @return
     */
    @GetMapping("/getAbout/{id}")
    @ApiOperation("根据id查询关于我的信息")
    public Result<About> getAbout(@PathVariable Integer id) {
        About about = aboutService.getAbout(id);
        return new Result<>(about);
    }

    /**
     * 阅读关于我的
     *
     * @return
     */
    @GetMapping("/read")
    @ApiOperation("阅读关于我的")
    public Result<About> readAbout() {
        About about = aboutService.readAbout();
        return new Result<>(about);
    }

    /**
     * 根据id删除关于我的
     *
     * @return
     */
    @DeleteMapping("/delete/{id}")
    @ApiOperation("根据id删除关于我的")
    public Result<Object> deleteAbout(@PathVariable Integer id) {
        aboutService.deleteAbout(id);
        return new Result<>("删除成功!");
    }

    /**
     * 根据id启用关于我的
     *
     * @param id
     * @return
     */
    @PutMapping("/enable/{id}")
    @ApiOperation("根据id启用关于我的")
    public Result<Object> enableAbout(@PathVariable Integer id) {
        aboutService.enableAbout(id);
        return new Result<>("已启用!");
    }

    /**
     * 根据id弃用关于我的
     *
     * @return
     */
    @PutMapping("/disable/{id}")
    @ApiOperation("弃用关于我的")
    public Result<Object> disableAbout(@PathVariable Integer id) {
        aboutService.disableAbout(id);
        return new Result<>("已弃用!");
    }

    /**
     * 分页查询关于我的
     *
     * @param page
     * @return
     */
    @PostMapping("/list")
    @ApiOperation("分页查询关于我的")
    public Result<Page<About>> getAboutList(@RequestBody Page<About> page) {
        String sortColumn = page.getSortColumn();
        if (StringUtils.isNotBlank(sortColumn)) {
            String[] sortColumns = {"about_read", "created_time", "update_time"};
            List<String> sortColumnList = Arrays.asList(sortColumns);
            if (!sortColumnList.contains(sortColumn)) {
                return new Result<>(ResultEnum.PARAMS_ERROR.getCode(), "查询参数不合法!");
            }
        }
        page = aboutService.getAboutList(page);
        return new Result<>(page);
    }

}
