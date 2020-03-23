package com.wcj.service.impl;

import com.wcj.mapper.CollectionMapper;
import com.wcj.service.CollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wcj
 * @Date 2020/3/23 15:07
 * @Version 1.0
 */
@Service
public class CollectionServiceImpl implements CollectionService {

    @Autowired
    private CollectionMapper collectionMapper;
}
