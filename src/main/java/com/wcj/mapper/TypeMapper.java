package com.wcj.mapper;

import com.wcj.pojo.Type;
import com.wcj.utils.Page;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * 帖子类型表Mapper
 *
 * @author wcj
 * @date
 * @Version 1.0
 */
@Component
public interface TypeMapper {

    /**
     * 根据类型名查询类型
     *
     * @param typeName
     * @return
     */
    Type getTypeByTypeName(String typeName);

    /**
     * 添加类型
     *
     * @param type
     */
    void addType(Type type);


    /**
     * 后端查询分类列表
     *
     * @return
     * @param page
     */
    List<Type> getTypeList(Page<Type> page);

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
     * 根据分页信息查询分类总条数
     * @param page
     * @return
     */
    int getCountByPage(Page<Type> page);
}
