package com.wcj.service;

import com.wcj.pojo.Link;

import java.util.List;

/**
 * @author wcj
 * @Date 2020/3/23 15:05
 * @Version 1.0
 */
public interface LinkService {

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
