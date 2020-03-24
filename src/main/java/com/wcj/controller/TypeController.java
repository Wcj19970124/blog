package com.wcj.controller;

import com.wcj.pojo.Type;
import com.wcj.service.TypeService;
import com.wcj.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 分类前端控制器
 * @author wcj
 * @Date 2020/3/24 12:53
 * @Version 1.0
 */
@Api(tags = "博客分类")
@RestController
@RequestMapping("type")
public class TypeController {

    @Autowired
    private TypeService typeService;

    /**
     * 添加分类
     *
     * @param type
     * @return
     */
    @PostMapping("/add")
    @ApiOperation(value = "添加分类")
    public Result<Object> addType(@RequestBody Type type) {
        typeService.addType(type);
        return new Result<>("添加分类成功!");
    }

    /**
     * 后台查询分类列表
     *
     * @return
     */
    @GetMapping("/listBack")
    @ApiOperation(value = "后台查询分类列表")
    public Result<List<Type>> getTypeList() {
        List<Type> typeList = typeService.getTypeList();
        return new Result<>(typeList);
    }

    /**
     * 前台查询分类列表
     *
     * @return
     */
    @GetMapping("/listFront")
    @ApiOperation(value = "前台查询分类列表")
    public Result<List<Type>> getTypeLists() {
        List<Type> typeList = typeService.getTypeLists();
        return new Result<>(typeList);
    }

    /**
     * 根据id查询分类
     *
     * @param id
     * @return
     */
    @GetMapping("/getType/{id}")
    @ApiOperation(value = "根据id查询分类")
    public Result<Type> getTypeById(@PathVariable Integer id) {
        Type type = typeService.getType(id);
        return new Result<>(type);
    }

    /**
     * 根据id修改分类
     *
     * @param type
     * @return
     */
    @PutMapping("/update")
    @ApiOperation(value = "根据id修改分类")
    public Result<Object> updateType(@RequestBody Type type) {
        typeService.updateType(type);
        return new Result<>("更新成功!");
    }

    /**
     * 根据id删除分类(逻辑删除)
     *
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "根据id删除分类")
    public Result<Object> deleteType(@PathVariable Integer id) {
        typeService.deleteType(id);
        return new Result<>("删除成功!");
    }

    /**
     * 启用分类
     *
     * @return
     */
    @PutMapping("/enable/{id}")
    @ApiOperation(value = "启用分类")
    public Result<Object> enableType(@PathVariable Integer id) {
        typeService.enableType(id);
        return new Result<>("已启用!");
    }

    /**
     * 弃用分类
     *
     * @param id
     * @return
     */
    @PutMapping("/disable/{id}")
    @ApiOperation(value = "弃用分类")
    public Result<Object> disableType(@PathVariable Integer id) {
        typeService.disableType(id);
        return new Result<>("已弃用");
    }
}
