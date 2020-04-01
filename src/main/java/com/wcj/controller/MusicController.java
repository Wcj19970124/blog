package com.wcj.controller;

import com.wcj.enums.ResultEnum;
import com.wcj.pojo.Music;
import com.wcj.service.MusicService;
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
 * 音乐前端控制器
 *
 * @author wcj
 * @Date 2020/3/25 19:37
 * @Version 1.0
 */
@Api(tags = "音乐")
@RestController
@RequestMapping("music")
public class MusicController {

    @Autowired
    private MusicService musicService;

    /**
     * 添加音乐
     *
     * @param music
     * @return
     */
    @PostMapping("/add")
    @ApiOperation("添加音乐")
    public Result<Object> addMusic(@RequestBody Music music) {
        musicService.addMusic(music);
        return new Result<>("添加成功!");
    }

    /**
     * 修改音乐
     *
     * @param music
     * @return
     */
    @PutMapping("/update")
    @ApiOperation("修改音乐")
    public Result<Object> updateMusic(@RequestBody Music music) {
        musicService.updateMusic(music);
        return new Result<>("修改成功!");
    }

    /**
     * 根据id查询音乐
     *
     * @param id
     * @return
     */
    @GetMapping("/getMusic/{id}")
    @ApiOperation("根据id查询音乐")
    public Result<Music> getMusic(@PathVariable Integer id) {
        Music music = musicService.getMusic(id);
        return new Result<>(music);
    }

    /**
     * 分页查询音乐列表
     *
     * @param page
     * @return
     */
    @PostMapping("/list")
    @ApiOperation("分页查询音乐列表")
    public Result<Page<Music>> getMusicList(@RequestBody Page<Music> page) {
        String sortColumn = page.getSortColumn();
        if (StringUtils.isNotBlank(sortColumn)) {
            String[] sortColumns = {"name", "artist", "created_time", "enable"};
            List<String> sortColumnList = Arrays.asList(sortColumns);
            if (!sortColumnList.contains(sortColumn)) {
                return new Result<>(ResultEnum.PARAMS_ERROR.getCode(), "查询参数不合法!");
            }
        }
        page = musicService.getMusicList(page);
        return new Result<>(page);
    }

    /**
     * 前台查询音乐列表
     * @return
     */
    @GetMapping("/listFront")
    @ApiOperation("前台查询音乐列表")
    public Result<List<Music>> getMusicList() {
        List<Music> musicList = musicService.getMusicList();
        return new Result<>(musicList);
    }

    /**
     * 根据id删除音乐
     *
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    @ApiOperation("根据id删除音乐")
    public Result<Object> deleteMusic(@PathVariable Integer id) {
        musicService.deleteMusic(id);
        return new Result<>("删除成功!");
    }

    /**
     * 根据id启用音乐
     *
     * @param id
     * @return
     */
    @PutMapping("/enable/{id}")
    @ApiOperation("根据id启用音乐")
    public Result<Object> enableMusic(@PathVariable Integer id) {
        musicService.enableMusic(id);
        return new Result<>("已启用!");
    }

    /**
     * 根据id弃用音乐
     *
     * @param id
     * @return
     */
    @PutMapping("/disable/{id}")
    @ApiOperation("根据id弃用音乐")
    public Result<Object> disableMusic(@PathVariable Integer id) {
        musicService.disableMusic(id);
        return new Result<>("已弃用!");
    }
}
