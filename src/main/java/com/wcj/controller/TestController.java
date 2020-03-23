package com.wcj.controller;

import com.wcj.exception.BlogException;
import com.wcj.utils.Result;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wcj
 * @Date 2020/3/23 15:39
 * @Version 1.0
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @RequestMapping("/testException/{id}")
    public Result<Object> test(@PathVariable Integer id){
        if (id == 1) {
            return new Result<>();
        }else{
            throw new BlogException("发生了异常!");
        }
    }
}
