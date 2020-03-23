package com.wcj.service.impl;

import com.wcj.mapper.AboutMapper;
import com.wcj.service.AboutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wcj
 * @Date 2020/3/23 15:07
 * @Version 1.0
 */
@Service
public class AboutServiceImpl implements AboutService {

    @Autowired
    private AboutMapper aboutMapper;
}
