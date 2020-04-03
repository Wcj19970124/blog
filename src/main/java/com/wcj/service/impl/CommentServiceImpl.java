package com.wcj.service.impl;

import com.wcj.dao.CommentDao;
import com.wcj.dao.CommentGoodDao;
import com.wcj.enums.ResultEnum;
import com.wcj.exception.BlogException;
import com.wcj.mapper.BlogMapper;
import com.wcj.mapper.UserMapper;
import com.wcj.pojo.Blog;
import com.wcj.pojo.Comment;
import com.wcj.pojo.CommentGoods;
import com.wcj.pojo.User;
import com.wcj.service.CommentService;
import com.wcj.utils.IdWorker;
import com.wcj.utils.ShiroUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author wcj
 * @Date 2020/3/23 15:07
 * @Version 1.0
 */
@Service
@Slf4j
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentDao commentDao;

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private BlogMapper blogMapper;

    @Autowired
    private CommentGoodDao commentGoodDao;

    /**
     * 发表评论
     *
     * @param comment
     */
    @Override
    public void addComment(Comment comment) {
        comment.setId(idWorker.nextId() + "");
        comment.setCommentGood(0);
        comment.setCreatedTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        //查询用户
        User user = userMapper.getUser(comment.getCommentUser());
        comment.setUser(user);
        //查询博客,博客评论数加1
        Blog blog = blogMapper.getBlog(comment.getCommentBlog());
        blog.setBlogComment(blog.getBlogComment()+1);
        blogMapper.updateBlog(blog);
        //保存评论信息
        comment.setBlog(blog);
        commentDao.save(comment);
    }

    /**
     * 根据博客查询评论
     *
     * @param blogId
     * @return
     */
    @Override
    public List<Comment> getComment(String blogId) {
        return commentDao.findAllByCommentBlogOrderByCreatedTimeDescCommentGoodDesc(blogId);
    }

    /**
     * 根据id删除评论
     * @param id
     */
    @Override
    public void deleteComment(String id) {
        commentDao.deleteById(id);
    }

    /**
     * 给评论点赞
     * @param commentGoods
     */
    @Override
    public void commentGood(CommentGoods commentGoods) {
        //先查询当前用户是否已经对该条评论点赞
        User user = (User) ShiroUtils.getLoginUser();
        int count = commentGoodDao.countByUserIdEqualsAndCommentIdEquals(user.getUserId(),commentGoods.getCommentId());
        if(count>0){
            throw new BlogException(ResultEnum.ERROR.getCode(),"已点赞,不能重复点赞!");
        }

        //评论点赞数加1
        Comment comment = commentDao.findById(commentGoods.getCommentId()).get();
        comment.setCommentGood(comment.getCommentGood()+1);
        commentDao.save(comment);

        //保存评论的点赞信息
        commentGoods.setUserId(user.getUserId());
        commentGoods.setId(idWorker.nextId()+"");
        commentGoodDao.save(commentGoods);
    }

    /**
     * 根据用户id和博客id查询博客评论的点赞情况
     * @param blogId
     * @return
     */
    @Override
    public List<Comment> getCommentGoods(String blogId) {
        //先根据博客id查询博客对应的评论集合
        List<Comment> commentList = commentDao.findAllByCommentBlogOrderByCreatedTimeDescCommentGoodDesc(blogId);
        //筛选出评论的id集合
        List<String> commentIds = commentList.stream().map(Comment::getId).collect(Collectors.toList());
        //根据评论的集合id查询出评论的点赞集合
        List<CommentGoods> commentGoodsList = commentGoodDao.findByCommentIdIn(commentIds);
        //遍历博客对应的评论集合，修改其点赞情况
        commentList.forEach(e->{
            commentGoodsList.forEach(commentGoods->{
                if(commentGoods.getCommentId().equals(e.getId())){
                    e.setCommentFlag(true);
                }
            });
        });
        return commentList;
    }
}
