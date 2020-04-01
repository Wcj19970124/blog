package com.wcj.controller;

import com.wcj.enums.ResultEnum;
import com.wcj.pojo.Blog;
import com.wcj.service.BlogService;
import com.wcj.utils.Page;
import com.wcj.utils.Result;
import com.wcj.utils.StringUtils;
import com.wcj.vo.BlogVo;
import com.wcj.vo.TimeLineVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * 博客前端控制器
 *
 * @author wcj
 * @Date 2020/3/25 8:36
 * @Version 1.0
 */
@Api(tags = "博客")
@RestController
@RequestMapping("blog")
public class BlogController {

    @Autowired
    private BlogService blogService;

    /**
     * 添加博客
     *
     * @param blog
     * @return
     */
    @PostMapping("/add")
    @ApiOperation("添加博客")
    public Result<Object> addBlog(@RequestBody Blog blog) {
        blogService.addBlog(blog);
        return new Result<>("添加成功!");
    }

    /**
     * 修改博客
     *
     * @param blog
     * @return
     */
    @PutMapping("/update")
    @ApiOperation("修改博客")
    public Result<Object> updateBlog(@RequestBody Blog blog) {
        blogService.updateBlog(blog);
        return new Result<>("修改成功!");
    }

    /**
     * 根据id查询博客
     *
     * @param id
     * @return
     */
    @GetMapping("/getBlog/{id}")
    @ApiOperation("根据id查询博客")
    public Result<Blog> getBlog(@PathVariable String id) {
        Blog blog = blogService.getBlog(id);
        return new Result<>(blog);
    }

    /**
     * 分页查询博客列表
     *
     * @return
     */
    @PostMapping("/list")
    @ApiOperation("分页查询博客列表")
    public Result<Page<BlogVo>> getBlogList(@RequestBody Page<BlogVo> page) {
        //排序参数校验
        String sortColumn = page.getSortColumn();
        if (StringUtils.isNotBlank(sortColumn)) {
            String[] sortColumns = {"blog_goods", "blog_read", "blog_collection",
                    "type_name", "blog_comment", "created_time", "update_time"};
            List<String> sortColumnList = Arrays.asList(sortColumns);
            if (!sortColumnList.contains(sortColumn.toLowerCase())) {
                return new Result<>(ResultEnum.PARAMS_ERROR.getCode(), "排序参数不合法!");
            }
        }
        page = blogService.getBlogList(page);
        return new Result<>(page);
    }

    /**
     * 前台推荐阅读
     *
     * @return
     */
    @GetMapping("/recommendRead")
    @ApiOperation("前台推荐阅读")
    public Result<List<BlogVo>> recommendRead() {
        List<BlogVo> blogVoList = blogService.recommendRead();
        return new Result<>(blogVoList);
    }

    /**
     * 删除博客
     *
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    @ApiOperation("删除博客")
    public Result<Object> deleteBlog(@PathVariable String id) {
        blogService.deleteBlog(id);
        return new Result<>("删除成功!");
    }

    /**
     * 按照id阅读
     *
     * @param id
     * @return
     */
    @GetMapping("/read/{id}")
    @ApiOperation("按照id阅读")
    public Result<BlogVo> readBlog(@PathVariable String id) {
        BlogVo blogVo = blogService.readBlog(id);
        return new Result<>(blogVo);
    }

    /**
     * 获取时间轴
     *
     * @return
     */
    @GetMapping("/getTimeLine")
    @ApiOperation("获取时间轴")
    public Result<List<TimeLineVo>> getTimeLine() {
        List<TimeLineVo> timeLineVoList = blogService.getTimeLine();
        return new Result<>(timeLineVoList);
    }
}
