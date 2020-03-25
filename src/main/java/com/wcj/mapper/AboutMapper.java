package com.wcj.mapper;

import com.wcj.pojo.About;
import com.wcj.utils.Page;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * 管理员表Mapper
 *
 * @author wcj
 * @date 2020-02-07 14:04:12
 * @Version 1.0
 */
@Component
public interface AboutMapper {

    /**
     * 添加关于我的信息
     * @param about
     */
    void addAbout(About about);

    /**
     * 修改关于我的信息
     * @param about
     */
    void updateAbout(About about);

    /**
     * 根据id查询关于我的信息
     * @param id
     * @return
     */
    About getAbout(Integer id);

    /**
     * 阅读关于我的
     * @return
     */
    About readAbout();

    /**
     * 根据id删除关于我的
     * @param id
     */
    void deleteAbout(Integer id);

    /**
     * 启用关于我的
     * @param about1
     */
    void updateEnable(About about1);

    /**
     * 分页查询数据总数
     * @param page
     * @return
     */
    int getCountByPage(Page<About> page);

    /**
     * 分页查询数据列表
     * @param page
     * @return
     */
    List<About> getAboutList(Page<About> page);
}
