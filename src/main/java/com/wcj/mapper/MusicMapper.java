package com.wcj.mapper;

import com.wcj.pojo.Music;
import com.wcj.utils.Page;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * 音乐表Mapper
 *
 * @author wcj
 * @date 2020-02-07 14:04:12
 * @Version 1.0
 *
 */
@Component
public interface MusicMapper {

    /**
     * 添加音乐
     * @param music
     */
    void addMusic(Music music);

    /**
     * 修改音乐
     * @param music
     */
    void updateMusic(Music music);

    /**
     * 根据id查询音乐
     * @param id
     * @return
     */
    Music getMusic(Integer id);

    /**
     * 分页查询音乐数据列表
     * @param page
     * @return
     */
    List<Music> getMusicList(Page<Music> page);

    /**
     * 分页查询音乐总数
     * @param page
     * @return
     */
    int getCountByPage(Page<Music> page);

    /**
     * 跟据id删除音乐
     * @param id
     */
    void deleteMusic(Integer id);
}
