package com.wcj.service.impl;

import com.wcj.mapper.RotationPictureMapper;
import com.wcj.pojo.RotationPicture;
import com.wcj.service.RotationPictureService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wcj
 * @Date 2020/4/5 12:44
 * @Version 1.0
 */
@Service
@Slf4j
public class RotationPictureImpl implements RotationPictureService {

    @Autowired
    private RotationPictureMapper rotationPictureMapper;
    /**
     * 添加轮播图
     * @param rotationPicture
     */
    @Override
    public void addRotationPicture(RotationPicture rotationPicture) {
        rotationPictureMapper.addRotationPicture(rotationPicture);
    }

    /**
     * 根据id删除轮播图
     * @param id
     */
    @Override
    public void deleteRotationPicture(Integer id) {
        rotationPictureMapper.deleteRotationPicture(id);
    }

    /**
     * 查询轮播图列表
     * @return
     */
    @Override
    public List<RotationPicture> getRotationPictureList() {
        return rotationPictureMapper.getRotationPictureList();
    }

    /**
     * 启用轮播图
     * @param id
     */
    @Override
    public void enableRotationPicture(Integer id) {
        rotationPictureMapper.enableRotationPicture(id);
    }

    /**
     * 弃用轮播图
     * @param id
     */
    @Override
    public void disableRotationPicture(Integer id) {
        rotationPictureMapper.disableRotationPicture(id);
    }

    /**
     * 前台查询轮播图列表
     * @return
     */
    @Override
    public List<RotationPicture> getRotationPictureListFront() {
        return rotationPictureMapper.getRotationPictureListFront();
    }
}
