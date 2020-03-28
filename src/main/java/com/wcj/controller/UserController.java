package com.wcj.controller;

import com.wcj.enums.ResultEnum;
import com.wcj.pojo.User;
import com.wcj.service.UserService;
import com.wcj.utils.Page;
import com.wcj.utils.Result;
import com.wcj.utils.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * @author wcj
 * @Date 2020/3/25 21:12
 * @Version 1.0
 */
@Api(tags = "用户")
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 添加用户
     *
     * @param user
     * @return
     */
    @PostMapping("/add")
    @ApiOperation("添加用户")
    public Result<Object> addUser(@RequestBody User user) {
        userService.addUser(user);
        return new Result<>("添加成功!");
    }

    /**
     * 修改用户
     *
     * @param user
     * @return
     */
    @PutMapping("/update")
    @ApiOperation("修改用户")
    public Result<Object> updateUser(@RequestBody User user) {
        userService.updateUser(user);
        return new Result<>("修改成功!");
    }

    /**
     * 根据id查询用户
     *
     * @param id
     * @return
     */
    @GetMapping("/getUser/{id}")
    @ApiOperation("根据id查询用户")
    public Result<User> getUser(@PathVariable Integer id) {
        User user = userService.getUser(id);
        return new Result<>(user);
    }

    /**
     * 分页查询用户列表
     *
     * @param page
     * @return
     */
    @PostMapping("/list")
    @ApiOperation("分页查询用户列表")
    public Result<Page<User>> getUserList(@RequestBody Page<User> page) {
        String sortColumn = page.getSortColumn();
        if (StringUtils.isNotBlank(sortColumn)) {
            String[] sortColumns = {  "sex", "created_time", "update_time"};
            List<String> sortColumnList = Arrays.asList(sortColumns);
            if (!sortColumnList.contains(sortColumn)) {
                return new Result<>(ResultEnum.PARAMS_ERROR.getCode(), "查询参数不合法!");
            }
        }
        page = userService.getUserList(page);
        return new Result<>(page);
    }

    /**
     * 根据id删除用户
     *
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    @ApiOperation("根据id删除用户")
    public Result<Object> deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
        return new Result<>("删除成功!");
    }

    /**
     * 批量重置用户密码
     * @param userIds
     * @return
     */
    @PutMapping("/resetPwd")
    @ApiOperation("批量重置密码")
    public Result<Object> resetPwd(@RequestBody List<Integer> userIds){
        userService.resetPwd(userIds);
        return new Result<>("重置完毕!");
    }
}
