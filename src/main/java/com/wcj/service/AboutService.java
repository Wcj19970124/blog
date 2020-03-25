package com.wcj.service;

import com.wcj.pojo.About;
import com.wcj.utils.Page;

/**
 * @author wcj
 * @Date 2020/3/23 15:04
 * @Version 1.0
 */
public interface AboutService {

    /**
     * 添加关于我的信息
     *
     * @param about
     */
    void addAbout(About about);

    /**
     * 修改关于我的信息
     *
     * @param about
     */
    void updateAbout(About about);

    /**
     * 根据id查询关于我的信息
     *
     * @param id
     * @return
     */
    About getAbout(Integer id);

    /**
     * 阅读关于我的
     *
     * @return
     */
    About readAbout();

    /**
     * 根据id删除关于我的
     *
     * @param id
     */
    void deleteAbout(Integer id);

    /**
     * 根据id启用关于我的
     *
     * @param id
     */
    void enableAbout(Integer id);

    /**
     * 弃用关于我的
     *
     * @param id
     */
    void disableAbout(Integer id);

    /**
     * 分页查询关于我的
     *
     * @param page
     * @return
     */
    Page<About> getAboutList(Page<About> page);
}
