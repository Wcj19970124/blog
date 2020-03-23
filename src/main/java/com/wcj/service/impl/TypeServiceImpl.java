package com.wcj.service.impl;

import com.wcj.mapper.TypeMapper;
import com.wcj.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wcj
 * @Date 2020/3/23 15:07
 * @Version 1.0
 */
@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeMapper typeMapper;
}
