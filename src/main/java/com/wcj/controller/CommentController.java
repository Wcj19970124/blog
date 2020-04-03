package com.wcj.controller;

import com.wcj.pojo.Blog;
import com.wcj.pojo.Comment;
import com.wcj.pojo.CommentGoods;
import com.wcj.pojo.User;
import com.wcj.service.CommentService;
import com.wcj.utils.Result;
import com.wcj.utils.ShiroUtils;
import com.wcj.utils.StringUtils;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 评论前端控制器
 *
 * @author wcj
 * @Date 2020/4/3 8:41
 * @Version 1.0
 */
@RestController
@RequestMapping("comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    /**
     * 发表评论
     *
     * @param comment
     * @return
     */
    @PostMapping("/add")
    @ApiOperation("发表评论")
    public Result<Object> addComment(@RequestBody Comment comment) {
        User user = (User) ShiroUtils.getLoginUser();
        comment.setCommentUser(user.getUserId());
        if (StringUtils.isBlank(comment.getCommentBlog())) {
            return new Result<>("博客id不能为空!");
        }
        commentService.addComment(comment);
        return new Result<>("发表成功!");
    }

    /**
     * 根据博客查询评论
     * @param blogId
     * @return
     */
    @GetMapping("/getComment/{blogId}")
    @ApiOperation("根据博客查询评论")
    public Result<List<Comment>> getComment(@PathVariable String blogId){
        List<Comment> commentList = commentService.getComment(blogId);
        return new Result<>(commentList);
    }

    /**
     * 根据id删除评论
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    @ApiOperation("根据id删除评论")
    public Result<Object> deleteComment(@PathVariable String id){
        commentService.deleteComment(id);
        return new Result<>("删除成功!");
    }

    /**
     * 给评论点赞
     * @param commentGoods
     * @return
     */
    @PostMapping("/commentGood")
    @ApiOperation("给评论点赞")
    public Result<Object> commentGood(@RequestBody CommentGoods commentGoods){
        if(StringUtils.isBlank(commentGoods.getCommentId())){
            return new Result<>("评论id不能为空!");
        }
        commentService.commentGood(commentGoods);
        return new Result<>("已赞!");
    }

    /**
     * 根据用户id和博客id查询博客评论的点赞情况
     * @param blogId
     * @return
     */
    @GetMapping("/getCommentGoods/{blogId}")
    @ApiOperation("查询评论点赞情况")
    public Result<List<Comment>> getCommentGoods(@PathVariable String blogId){
        List<Comment> commentList = commentService.getCommentGoods(blogId);
        return new Result<>(commentList);
    }
}