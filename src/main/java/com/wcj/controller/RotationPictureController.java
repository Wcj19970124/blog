package com.wcj.controller;

import com.wcj.pojo.RotationPicture;
import com.wcj.service.RotationPictureService;
import com.wcj.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 轮播图前端控制器
 *
 * @author wcj
 * @Date 2020/4/5 12:41
 * @Version 1.0
 */
@RestController
@RequestMapping("/rotation-picture")
@Api(tags = "轮播图")
public class RotationPictureController {

    @Autowired
    private RotationPictureService rotationPictureService;

    /**
     * 添加轮播图
     *
     * @param rotationPicture
     * @return
     */
    @PostMapping("/add")
    @ApiOperation("添加轮播图")
    public Result<Object> addRotationPicture(@RequestBody RotationPicture rotationPicture) {
        rotationPictureService.addRotationPicture(rotationPicture);
        return new Result<>("添加成功!");
    }

    /**
     * 根据id删除轮播图
     *
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    @ApiOperation("删除轮播图")
    public Result<Object> deleteRotationPicture(@PathVariable Integer id) {
        rotationPictureService.deleteRotationPicture(id);
        return new Result<>("删除成功!");
    }

    /**
     * 查询轮播图列表
     *
     * @return
     */
    @GetMapping("/list")
    @ApiOperation("查询轮播图列表")
    public Result<List<RotationPicture>> getRotationPictureList() {
        List<RotationPicture> rotationPictureList = rotationPictureService.getRotationPictureList();
        return new Result<>(rotationPictureList);
    }

    /**
     * 启用轮播图
     *
     * @param id
     * @return
     */
    @PutMapping("/enable/{id}")
    @ApiOperation("启用轮播图")
    public Result<Object> enableRotationPicture(@PathVariable Integer id) {
        rotationPictureService.enableRotationPicture(id);
        return new Result<>("已启用!");
    }

    /**
     * 弃用轮播图
     *
     * @param id
     * @return
     */
    @PutMapping("/disable/{id}")
    @ApiOperation("弃用轮播图")
    public Result<Object> disableRotationPicture(@PathVariable Integer id) {
        rotationPictureService.disableRotationPicture(id);
        return new Result<>("已弃用!");
    }

    /**
     * 前台查询轮播图列表
     *
     * @return
     */
    @GetMapping("/listFront")
    @ApiOperation("前台查询轮播图列表")
    public Result<List<RotationPicture>> getRotationPictureListFront() {
        List<RotationPicture> rotationPictureList = rotationPictureService.getRotationPictureListFront();
        return new Result<>(rotationPictureList);
    }
}
