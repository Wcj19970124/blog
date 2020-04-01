package com.wcj.service.impl;

import com.wcj.enums.StateEnum;
import com.wcj.mapper.MusicMapper;
import com.wcj.pojo.Music;
import com.wcj.service.MusicService;
import com.wcj.utils.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author wcj
 * @Date 2020/3/23 15:07
 * @Version 1.0
 */
@Service
@Slf4j
public class MusicServiceImpl implements MusicService {

    @Autowired
    private MusicMapper musicMapper;

    /**
     * 添加音乐
     *
     * @param music
     */
    @Override
    public void addMusic(Music music) {
        musicMapper.addMusic(music);
    }

    /**
     * 修改音乐
     *
     * @param music
     */
    @Override
    public void updateMusic(Music music) {
        musicMapper.updateMusic(music);
    }

    /**
     * 根据id查询音乐
     *
     * @param id
     * @return
     */
    @Override
    public Music getMusic(Integer id) {
        return musicMapper.getMusic(id);
    }

    /**
     * 分页查询音乐列表
     *
     * @param page
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Page<Music> getMusicList(Page<Music> page) {
        //先查数据
        List<Music> musicList = musicMapper.getMusicList(page);
        page.setList(musicList);
        //再查数据总数
        int totalCount = musicMapper.getCountByPage(page);
        page.setTotalCount(totalCount);
        return page;
    }

    /**
     * 根据id删除音乐
     *
     * @param id
     */
    @Override
    public void deleteMusic(Integer id) {
        musicMapper.deleteMusic(id);
    }

    /**
     * 根据id启用音乐
     *
     * @param id
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void enableMusic(Integer id) {
        Music music = musicMapper.getMusic(id);
        music.setEnabled(StateEnum.ENABLED.getCode());
        musicMapper.updateMusic(music);
    }

    /**
     * 根据id弃用音乐
     *
     * @param id
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void disableMusic(Integer id) {
        Music music = musicMapper.getMusic(id);
        music.setEnabled(StateEnum.NOT_ENABLE.getCode());
        musicMapper.updateMusic(music);
    }

    /**
     * 前台查询音乐列表
     * @return
     */
    @Override
    public List<Music> getMusicList() {
        List<Music> musicList = musicMapper.getList();
        return musicList;
    }
}
