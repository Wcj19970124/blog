package com.wcj.mapper;

import com.wcj.pojo.Link;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * 评论表Mapper
 *
 * @author wcj
 * @date
 * @Version 1.0
 */
@Component
public interface LinkMapper {

    /**
     * 添加友情链接
     * @param link
     */
    void addLink(Link link);

    /**
     * 根据id删除友情链接
     * @param id
     */
    void deleteById(Integer id);

    /**
     * 修改友情链接
     * @param link
     */
    void updateLink(Link link);

    /**
     * 根据id查询友情链接
     * @param id
     * @return
     */
    Link getById(Integer id);

    /**
     * 查询友情链接列表
     * @return
     */
    List<Link> getLinkList();
}
