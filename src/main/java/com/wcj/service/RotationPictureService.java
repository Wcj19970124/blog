package com.wcj.service;

import com.wcj.pojo.RotationPicture;

import java.util.List;

/**
 * @author wcj
 * @Date 2020/4/5 12:43
 * @Version 1.0
 */
public interface RotationPictureService {

    /**
     * 添加轮播图
     *
     * @param rotationPicture
     */
    void addRotationPicture(RotationPicture rotationPicture);

    /**
     * 根据id删除轮播图
     *
     * @param id
     */
    void deleteRotationPicture(Integer id);

    /**
     * 查询轮播图列表
     *
     * @return
     */
    List<RotationPicture> getRotationPictureList();

    /**
     * 启用轮播图
     *
     * @param id
     */
    void enableRotationPicture(Integer id);

    /**
     * 弃用轮播图
     *
     * @param id
     */
    void disableRotationPicture(Integer id);

    /**
     * 前台查询轮播图列表
     * @return
     */
    List<RotationPicture> getRotationPictureListFront();
}
