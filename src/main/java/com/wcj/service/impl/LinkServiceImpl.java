package com.wcj.service.impl;

import com.wcj.mapper.LinkMapper;
import com.wcj.pojo.Link;
import com.wcj.service.LinkService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wcj
 * @Date 2020/3/23 15:07
 * @Version 1.0
 */
@Service
@Slf4j
public class LinkServiceImpl implements LinkService {

    @Autowired
    private LinkMapper linkMapper;

    /**
     * 添加友情链接
     * @param link
     */
    @Override
    public void addLink(Link link) {
        linkMapper.addLink(link);
    }

    /**
     * 根据id删除友情链接
     * @param id
     */
    @Override
    public void deleteById(Integer id) {
        linkMapper.deleteById(id);
    }

    /**
     * 修改友情链接
     * @param link
     */
    @Override
    public void updateLink(Link link) {
        linkMapper.updateLink(link);
    }

    /**
     * 根据id查询友情链接
     * @param id
     * @return
     */
    @Override
    public Link getById(Integer id) {
        return linkMapper.getById(id);
    }

    /**
     * 查询友情链接列表
     * @return
     */
    @Override
    public List<Link> getLinkList() {
        return linkMapper.getLinkList();
    }
}
