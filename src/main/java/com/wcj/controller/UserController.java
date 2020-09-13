package com.wcj.controller;

import com.wcj.enums.ResultEnum;
import com.wcj.enums.StateEnum;
import com.wcj.pojo.Blog;
import com.wcj.pojo.Comment;
import com.wcj.pojo.User;
import com.wcj.service.UserService;
import com.wcj.token.UsernamePasswordToken;
import com.wcj.utils.Page;
import com.wcj.utils.Result;
import com.wcj.utils.ShiroUtils;
import com.wcj.utils.StringUtils;
import com.wcj.vo.BlogVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.*;

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
     * 后台修改用户
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
     * 前台修改个人信息,无乐观锁
     *
     * @param user
     * @return
     */
    @PutMapping("/updateFront")
    @ApiOperation("前台修改个人信息")
    public Result<Object> updateFront(@RequestBody User user) {
        userService.updateFront(user);
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

    /**
     * 前台用户注册
     *
     * @param user
     * @return
     */
    @PostMapping("/register")
    @ApiOperation("前台用户注册")
    public Result<Object> register(@RequestBody User user) {
        userService.register(user);
        return new Result<>("注册成功!");
    }

    /**
     * 前台用户登录
     *
     * @param user
     * @return
     */
    @PostMapping("/login")
    @ApiOperation("前台用户登录")
    public Result<Object> login(@RequestBody User user) {
        //验证参数是否为空
        if(user==null || StringUtils.isBlank(user.getUsername()) || StringUtils.isBlank(user.getPassword())){
            return new Result<>("用户名或密码错误!");
        }
        //登录
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword(), StateEnum.USER.getCode());
        try{
            subject.login(token);
        }catch (Exception e){
            return new Result<>("用户名或密码错误!");
        }
        //登录成功
        Map<String,Object> userMap = new HashMap<>(4);
        Serializable sessionId = subject.getSession().getId();
        User u = (User)ShiroUtils.getLoginUser();
        u.setPassword("");
        u.setDeleted(null);
        userMap.put("token",sessionId);
        userMap.put("user",u);
        return new Result<>(userMap);
    }

    /**
     * 根据用户id分页查询用户的收藏博客列表
     * @return
     */
    @PostMapping("/getCollection")
    @ApiOperation("获取用户收藏文章列表")
    public Result<Page<BlogVo>> getCollection(@RequestBody Page<BlogVo> page){
         page = userService.getCollection(page);
         return new Result<>(page);
    }

    /**
     * 根据用户id分页查询用户的评论列表
     * @param page
     * @return
     */
    @PostMapping("/getComment")
    @ApiOperation("获取用户的评论列表")
    public Result<Page<Comment>> getComment(@RequestBody Page<Comment> page){
        page = userService.getComment(page);
        return new Result<>(page);
    }

    /**
     * 根据用户id查询用户的收藏数和评论数
     *
     * @return
     */
    @GetMapping("/getCommentAndCollection")
    @ApiOperation("获取用户的收藏数和评论数")
    public Result<Map<String, Integer>> getCommentAndCollection() {
        Map<String, Integer> map = userService.getCommentAndCollection();
        return new Result<>(map);
    }
}
