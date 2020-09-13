package com.wcj.controller;

import com.wcj.pojo.BlogPicture;
import com.wcj.service.BlogPictureService;
import com.wcj.utils.Page;
import com.wcj.utils.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author wcj
 * @Date 2020/5/6 23:33
 * @Version 1.0
 */
@RestController
@RequestMapping("blog-picture")
public class BlogPictureController {

    @Autowired
    private BlogPictureService blogPictureService;

    /**
     * 添加博客图片
     * @param blogPicture
     * @return
     */
    @PostMapping("/add")
    @ApiOperation("添加博客图片")
    public Result<Object> addBlogPicture(@RequestBody BlogPicture blogPicture){
        blogPictureService.addBlogPicture(blogPicture);
        return new Result<>("添加成功!");
    }

    /**
     * 删除博客图片
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    @ApiOperation("删除博客图片")
    public Result<Object> deleteBlogPicture(@PathVariable Integer id){
        blogPictureService.deleteBlogPicture(id);
        return new Result<>("删除成功!");
    }

    /**
     * 分页查询博客图片
     * @param page
     * @return
     */
    @PostMapping("/list")
    @ApiOperation("分页查询博客图片列表")
    public Result<Page<BlogPicture>> getBlogPictureList(@RequestBody Page<BlogPicture> page){
        page = blogPictureService.getBlogPictureList(page);
        return new Result<>(page);
    }
}
