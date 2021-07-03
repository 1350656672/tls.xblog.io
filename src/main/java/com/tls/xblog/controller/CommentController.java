package com.tls.xblog.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tls.xblog.entity.Blog;
import com.tls.xblog.entity.Comment;
import com.tls.xblog.service.BlogService;
import com.tls.xblog.service.CommentService;
import com.tls.xblog.service.TestService;
import com.tls.xblog.until.MarkdownUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller
//@CrossOrigin(origins = "http://localhost:8001", maxAge = 3600)
public class CommentController {
    @Autowired
    @Qualifier("CommentService")
    CommentService commentService;

    @ResponseBody
    @RequestMapping(value = "/comment",method = RequestMethod.POST)
    public String getComment(@RequestParam("blogId") long blogId) throws JsonProcessingException {
        List<Comment> comments= commentService.findCommentsById(blogId);
        System.out.println(comments);
        ObjectMapper objectMapper = new ObjectMapper();
        String s = objectMapper.writeValueAsString(comments);
        return s;
    }

    @ResponseBody
    @RequestMapping(value = "/admin/comment",method = RequestMethod.DELETE)
    public String deleteComment(@RequestParam("id") long id,@RequestParam("blogId") long blogId) throws JsonProcessingException {
        commentService.deleteCommentsById(id);
        List<Comment> comments= commentService.findCommentsById(blogId);
        System.out.println(comments);
        ObjectMapper objectMapper = new ObjectMapper();
        String s = objectMapper.writeValueAsString(comments);
        return s;
    }

    @ResponseBody
    @RequestMapping(value = "/comment/add",method = RequestMethod.POST)
    //注意：使用封装时，参数需要与前端对应
    public String addComment(Comment comment) throws JsonProcessingException {
        System.out.println(comment.getParentCommentId());
        //提交评论
        int i = commentService.addCommentsById(comment);
        //查询评论
        List<Comment> comments= commentService.findCommentsById(comment.getBlogId());
        System.out.println(comments);
        ObjectMapper objectMapper = new ObjectMapper();
        String s = objectMapper.writeValueAsString(comments);
        return s;
    }


}
