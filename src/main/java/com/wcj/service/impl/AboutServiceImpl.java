package com.wcj.service.impl;

import com.wcj.enums.ResultEnum;
import com.wcj.enums.StateEnum;
import com.wcj.exception.BlogException;
import com.wcj.mapper.AboutMapper;
import com.wcj.pojo.About;
import com.wcj.service.AboutService;
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
public class AboutServiceImpl implements AboutService {

    @Autowired
    private AboutMapper aboutMapper;

    /**
     * 添加关于我的信息
     *
     * @param about
     */
    @Override
    public void addAbout(About about) {
        aboutMapper.addAbout(about);
    }

    /**
     * 修改关于我的信息
     *
     * @param about
     */
    @Override
    public void updateAbout(About about) {
        aboutMapper.updateAbout(about);
    }

    /**
     * 根据id查询关于我的信息
     *
     * @param id
     * @return
     */
    @Override
    public About getAbout(Integer id) {
        return aboutMapper.getAbout(id);
    }

    /**
     * 阅读关于我的
     *
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public About readAbout() {
        //先查再修改阅读数
        About about = aboutMapper.readAbout();
        if (about == null) {
            throw new BlogException(ResultEnum.DATA_NOT_FOUND.getCode(), "暂无关于我的");
        }
        about.setAboutRead(about.getAboutRead() + 1);
        aboutMapper.updateAbout(about);
        return about;
    }

    /**
     * 根据id删除关于我的
     *
     * @param id
     */
    @Override
    public void deleteAbout(Integer id) {
        aboutMapper.deleteAbout(id);
    }

    /**
     * 根据id启用关于我的
     *
     * @param id
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void enableAbout(Integer id) {
        //先查询是否已经有启用的
        About about = aboutMapper.readAbout();
        if (about != null) {
            throw new BlogException(ResultEnum.ERROR.getCode(), "已有启用的关于");
        }
        //没有启用的则查询对应id的关于我的信息
        About about1 = aboutMapper.getAbout(id);
        about1.setEnable(StateEnum.ENABLED.getCode());
        aboutMapper.updateEnable(about1);
    }

    /**
     * 弃用关于我的
     *
     * @param id
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void disableAbout(Integer id) {
        About about = aboutMapper.getAbout(id);
        about.setEnable(StateEnum.NOT_ENABLE.getCode());
        aboutMapper.updateEnable(about);
    }

    /**
     * 分页查询关于我的
     *
     * @param page
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Page<About> getAboutList(Page<About> page) {
        //查询数据
        List<About> aboutList = aboutMapper.getAboutList(page);
        page.setList(aboutList);
        //查询总数
        int totalCount = aboutMapper.getCountByPage(page);
        page.setTotalCount(totalCount);
        return page;
    }
}
