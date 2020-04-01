package com.wcj.service;

import com.wcj.pojo.Music;
import com.wcj.utils.Page;

import java.util.List;

/**
 * @author wcj
 * @Date 2020/3/23 15:05
 * @Version 1.0
 */
public interface MusicService {

    /**
     * 添加音乐
     *
     * @param music
     */
    void addMusic(Music music);

    /**
     * 修改音乐
     *
     * @param music
     */
    void updateMusic(Music music);

    /**
     * 根据id查询音乐
     *
     * @param id
     * @return
     */
    Music getMusic(Integer id);

    /**
     * 分页查询音乐列表
     *
     * @param page
     * @return
     */
    Page<Music> getMusicList(Page<Music> page);

    /**
     * 根据id删除音乐
     *
     * @param id
     */
    void deleteMusic(Integer id);

    /**
     * 根据id启用音乐
     *
     * @param id
     */
    void enableMusic(Integer id);

    /**
     * 根据id弃用音乐
     *
     * @param id
     */
    void disableMusic(Integer id);

    /**
     * 前台查询音乐列表
     * @return
     */
    List<Music> getMusicList();

}
