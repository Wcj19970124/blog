package com.wcj.service.impl;

import com.wcj.mapper.LinkMapper;
import com.wcj.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wcj
 * @Date 2020/3/23 15:07
 * @Version 1.0
 */
@Service
public class LinkServiceImpl implements LinkService {

    @Autowired
    private LinkMapper linkMapper;
}
