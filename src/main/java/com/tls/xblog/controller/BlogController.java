package com.tls.xblog.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tls.xblog.entity.Blog;
import com.tls.xblog.entity.Type;
import com.tls.xblog.service.BlogService;
import com.tls.xblog.service.Impl.TestServiceImpl;
import com.tls.xblog.service.TestService;
import com.tls.xblog.until.FileUtils;
import com.tls.xblog.until.MarkdownUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sun.plugin.javascript.navig.Array;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;

@Controller
public class BlogController {
    @Autowired
    @Qualifier("BlogService")
    BlogService blogService;
    @Autowired
    @Qualifier("TestService")
    TestService testService;

    @ResponseBody
    @RequestMapping(value = "/blog/all",method = RequestMethod.GET)
    public String getAllBlogs() throws JsonProcessingException {
        List<Blog> blogs = blogService.selectAllBlog();
        ObjectMapper objectMapper = new ObjectMapper();
        String s = objectMapper.writeValueAsString(blogs);
        System.out.println(s);
        return s;
    }

    @ResponseBody
    @RequestMapping(value = "/blog",method = RequestMethod.GET)
    public String getBlogById(@RequestParam("id")long id,@RequestParam("convert")Boolean convert) throws JsonProcessingException {
        Blog blog = blogService.selectBlogById(id);
        String content = blog.getContent();
        System.out.println(content);
        if (convert){
            content= MarkdownUtils.markdownToHtmlExtensions(content);
            blog.setContent(content);
        }

        System.out.println(content);
        ObjectMapper objectMapper = new ObjectMapper();
        String s = objectMapper.writeValueAsString(blog);
        return s;
    }

    @ResponseBody
    @RequestMapping(value = "/admin/blog",method = RequestMethod.PUT)
    public String insertBlog(Blog blog) throws IOException {
        System.out.println(blog);
        return blogService.insertBlog(blog);
    }

    /*编辑内容（包括编辑后发草稿和直接发布）*/
    @ResponseBody
    @RequestMapping(value = "/admin/blog",method = RequestMethod.POST)
    public String updateBlog(Blog blog) throws IOException {
        System.out.println(blog);
        return blogService.updateBlog(blog);
    }

    @ResponseBody
    @RequestMapping(value = "/admin/blog",method = RequestMethod.DELETE)
    public String deleteBlog(long id) throws IOException {
        System.out.println(id);
        blogService.deleteBlog(id);
        return "彻底删除";
    }

    /*放入回收站与编辑（后发草稿或重新发布）一样都是更新行为，但由于只操作一列数据，故分开*/
    @ResponseBody
    @RequestMapping(value = "/admin/trashBlog",method = RequestMethod.POST)
    public String trashBlog(long id) throws IOException {
        System.out.println(id);
        blogService.trashBlog(id);
        return "已移入回收站";
    }
    @ResponseBody
    @RequestMapping(value = "/admin/restoreBlog",method = RequestMethod.POST)
    public String restoreBlog(long id) throws IOException {
        System.out.println(id);
        blogService.restoreBlog(id);
        return "已从回收站找回";
    }

    @ResponseBody
    @GetMapping("/admin/getTrashBlogs")
    public String getTrashBlogs() throws JsonProcessingException {
        List<Blog> blogs = blogService.getTrashBlogs();
        ObjectMapper objectMapper = new ObjectMapper();
        String s = objectMapper.writeValueAsString(blogs);
        System.out.println(s);
        return s;
    }

    @ResponseBody
    @PostMapping("/getBlogsByType")
    public String getBlogsByType(@RequestParam("id")Long id) throws JsonProcessingException {
        List<Blog> blogs = blogService.getBlogsByType(id);
        ObjectMapper objectMapper = new ObjectMapper();
        String s = objectMapper.writeValueAsString(blogs);
        System.out.println(s);
        return s;
    }

    @ResponseBody
    @PostMapping("/getBlogsByTags")
    public String getBlogsByTags(@RequestParam("tags")Long[] ids) throws JsonProcessingException {
        List<Blog> blogs = blogService.getBlogsByTags(Arrays.asList(ids));
        ObjectMapper objectMapper = new ObjectMapper();
        String s = objectMapper.writeValueAsString(blogs);
        System.out.println(s);
        return s;
    }

    @ResponseBody
    @PostMapping("/searchBlogs")
    public String searchBlogs(@RequestParam("pageNum")int pageNum,@RequestParam("pageSize") int pageSize,@RequestParam("searchKey")String searchKey) throws JsonProcessingException {
        Map map = blogService.searchBlogs(pageNum,pageSize,searchKey);
        ObjectMapper objectMapper = new ObjectMapper();
        String s = objectMapper.writeValueAsString(map);
        System.out.println(s);
        return s;
    }

    @ResponseBody
    @PostMapping("/getRecommandBlogs")
    public String getRecommandBlogs(@RequestParam("pageNum")int pageNum, @RequestParam("pageSize") int pageSize, HttpServletRequest request) throws JsonProcessingException {
        Map map = blogService.getRecommendBlogs(pageNum,pageSize);
        ObjectMapper objectMapper = new ObjectMapper();
        String s = objectMapper.writeValueAsString(map);
        System.out.println(s);
        System.out.println(request.getSession().getAttribute("uid"));
        return s;
    }
    @ResponseBody
    @GetMapping("/getCompletedBlogs")
    public String getCompleteBlogs() throws JsonProcessingException {
        List<Blog> blogs = blogService.getCompletedBlogs();
        ObjectMapper objectMapper = new ObjectMapper();
        String s = objectMapper.writeValueAsString(blogs);
        System.out.println(s);
        return s;
    }

    @ResponseBody
    @GetMapping("/admin/getUncompletedBlogs")
    public String getUncompletedBlogs() throws JsonProcessingException {
        List<Blog> blogs = blogService.getUncompletedBlogs();
        ObjectMapper objectMapper = new ObjectMapper();
        String s = objectMapper.writeValueAsString(blogs);
        System.out.println(s);
        return s;
    }



    @ResponseBody
    @RequestMapping(value = "/contentPicture",method = RequestMethod.POST)
    public String test7(@RequestParam("editormd-image-file") MultipartFile mf/*Map map*/) throws IOException {
        System.out.println("进入contentPicture请求");
        String path = FileUtils.upload(mf, "contentPicture");
        HashMap map = new HashMap();
        map.put("success",1);
        map.put("message","上传成功");
        map.put("url",path);
        ObjectMapper objectMapper = new ObjectMapper();
        String s = objectMapper.writeValueAsString(map);
        return s;
    }


    @ResponseBody
    @RequestMapping(value = "/firstPicture",method = RequestMethod.POST)
    public String test8(@RequestPart("file") MultipartFile mf) throws IOException {
        System.out.println("进入firstPicture请求");
        String path = FileUtils.upload(mf, "contentPicture");
        HashMap map = new HashMap();
        map.put("success",1);
        map.put("message","上传成功");
        map.put("url",path);
        ObjectMapper objectMapper = new ObjectMapper();
        String s = objectMapper.writeValueAsString(map);
        return s;
    }
}
