package com.wcj.service;

import com.wcj.pojo.Type;
import com.wcj.utils.Result;

import java.util.List;

/**
 * @author wcj
 * @Date 2020/3/23 15:05
 * @Version 1.0
 */
public interface TypeService {

    /**
     * 添加类型
     *
     * @param type
     */
    void addType(Type type);

    /**
     * 后台查询分类列表
     *
     * @return
     */
    List<Type> getTypeList();

    /**
     * 前台查询分类列表
     *
     * @return
     */
    List<Type> getTypeLists();

    /**
     * 根据id查询分类
     *
     * @param id
     * @return
     */
    Type getType(Integer id);

    /**
     * 根据id修改分类
     *
     * @param type
     */
    void updateType(Type type);

    /**
     * 根据id删除分类
     *
     * @param id
     */
    void deleteType(Integer id);

    /**
     * 启用分类
     *
     * @param id
     */
    void enableType(Integer id);

    /**
     * 弃用分类
     *
     * @param id
     */
    void disableType(Integer id);
}
