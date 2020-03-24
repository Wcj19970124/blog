package com.wcj.controller;

import com.wcj.pojo.Link;
import com.wcj.service.LinkService;
import com.wcj.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author wcj
 * @Date 2020/3/24 22:41
 * @Version 1.0
 */
@Api(tags = "友情链接")
@RestController
@RequestMapping("link")
public class LinkController {

    @Autowired
    private LinkService linkService;

    /**
     * 添加友情链接
     * @param link
     * @return
     */
    @PostMapping("/add")
    @ApiOperation("添加友情链接")
    public Result<Object> addLink(@RequestBody Link link){
        linkService.addLink(link);
        return new Result<>("添加成功!");
    }

    /**
     * 根据id删除友情链接
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    @ApiOperation("根据id删除友情链接")
    public Result<Object> deleteLink(@PathVariable Integer id){
        linkService.deleteById(id);
        return new Result<>("删除成功!");
    }

    /**
     * 修改友情链接
     * @param link
     * @return
     */
    @PutMapping("/update")
    @ApiOperation("修改友情链接")
    public Result<Object> updateLink(@RequestBody Link link){
        linkService.updateLink(link);
        return new Result<>("修改成功!");
    }

    /**
     * 根据id查询友情链接
     * @param id
     * @return
     */
    @GetMapping("/getLink/{id}")
    @ApiOperation("根据id查询友情链接")
    public Result<Link> getById(@PathVariable Integer id){
        Link link = linkService.getById(id);
        return new Result<>(link);
    }

    /**
     * 查询友情链接列表
     * @return
     */
    @GetMapping("/list")
    @ApiOperation("查询友情链接列表")
    public Result<List<Link>> getLinkList(){
        List<Link> linkList = linkService.getLinkList();
        return new Result<>(linkList);
    }
}
