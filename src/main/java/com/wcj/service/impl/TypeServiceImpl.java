package com.wcj.service.impl;

import com.wcj.enums.StateEnum;
import com.wcj.exception.BlogException;
import com.wcj.mapper.TypeMapper;
import com.wcj.pojo.Type;
import com.wcj.service.TypeService;
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
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeMapper typeMapper;

    /**
     * 添加类型
     *
     * @param type
     */
    @Override
    public void addType(Type type) {
        //先查再添加
        Type t = typeMapper.getTypeByTypeName(type.getTypeName());
        if (t != null) {
            throw new BlogException("该分类已经存在!");
        }
        typeMapper.addType(type);
    }

    /**
     * 后台查询分类列表
     *
     * @return
     * @param page
     */
    @Override
    public Page<Type> getTypeList(Page<Type> page) {

        //查询分类列表
        List<Type> typeList = typeMapper.getTypeList(page);
        page.setList(typeList);
        //查询总条数
        int totalCount = typeMapper.getCountByPage(page);
        page.setTotalCount(totalCount);
        return page;
    }

    /**
     * 前台查询分类列表
     *
     * @return
     */
    @Override
    public List<Type> getTypeLists() {
        return typeMapper.getTypeLists();
    }

    /**
     * 根据id查询分类
     *
     * @param id
     * @return
     */
    @Override
    public Type getType(Integer id) {
        return typeMapper.getType(id);
    }

    /**
     * 根据id修改分类
     *
     * @param type
     */
    @Override
    public void updateType(Type type) {
        typeMapper.updateType(type);
    }

    /**
     * 根据id删除分类
     *
     * @param id
     */
    @Override
    public void deleteType(Integer id) {
        typeMapper.deleteType(id);
    }

    /**
     * 启用分类
     *
     * @param id
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void enableType(Integer id) {
        //先查再启用
        Type type = typeMapper.getType(id);
        type.setEnable(StateEnum.ENABLED.getCode());
        typeMapper.updateType(type);
    }

    /**
     * 弃用分类
     *
     * @param id
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void disableType(Integer id) {
        //先查再弃用
        Type type = typeMapper.getType(id);
        type.setEnable(StateEnum.NOT_ENABLE.getCode());
        typeMapper.updateType(type);
    }

}
