package com.wcj.controller;

import com.wcj.utils.Result;
import com.wcj.utils.UploadService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author wcj
 * @Date 2020/3/27 11:04
 * @Version 1.0
 */
@Api("上传文件")
@RestController
@RequestMapping("upload")
public class UploadController {

    @Autowired
    private UploadService uploadService;

    /**
     * 上传图片
     *
     * @return
     */
    @RequestMapping("/uploadImage")
    @ApiOperation("上传图片")
    public Result<String> uploadImage(MultipartFile file) {
        String url = uploadService.uploadImage(file);
        return new Result<>("上传成功!",url);
    }
}
