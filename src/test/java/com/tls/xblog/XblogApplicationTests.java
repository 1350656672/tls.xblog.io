package com.tls.xblog;

import com.github.pagehelper.PageInfo;
import com.tls.xblog.entity.*;
import com.tls.xblog.service.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@SpringBootTest
class XblogApplicationTests {

    @Autowired
    DataSource dataSource;
    @Test
    void contextLoads() {
        //System.out.println(dataSource.getClass());

    }

    @Autowired
    @Qualifier("BlogService")
    BlogService blogService;
    @Test
    void contextLoads2() {
        /*Blog blog = new Blog();
        blog.setContent("2222");
        blog.setTypeId(2);
        blogService.insertBlog(blog);*/

       /*Blog blog1 = blogService.selectBlogById(2);
        System.out.println(blog1);*/

        //List<Blog> blogs = blogService.getBlogsByType(Long.valueOf(1));
        /*ArrayList<Long> arrayList = new ArrayList();
        arrayList.add(Long.valueOf(1));
        arrayList.add(Long.valueOf(2));
        arrayList.add(Long.valueOf(3));*/
        //List<Blog> blogs = blogService.getBlogsByTags(arrayList);
        Map map = blogService.searchBlogs(1, 3,"搜索");
        List<Blog> blogs = (List<Blog>) map.get("blogs");
        PageInfo<Blog> pageInfo = (PageInfo<Blog>) map.get("pageInfo");
        System.out.println("输出内容：");
        System.out.println(blogs.get(0));
        System.out.println(blogs);
        System.out.println(pageInfo);
    }

    @Autowired
    @Qualifier("CommentService")
    CommentService commentService;
    @Test
    void contextLoads3() {

        /*List<Comment> comments = commentService.findCommentsById(1);
        System.out.println(comments.get(0).toString());*/
        Comment comment = new Comment();
        comment.setBlogId((long)1);

    }

    @Autowired
    @Qualifier("TypeService")
    TypeService typeService;
    @Test
    void contextLoads4() {
        /*Type type = typeService.findTypeById(Long.valueOf(1));
        System.out.println(type);*/
//        List<Type> types = typeService.findAllTypesHasBlog();
//        System.out.println(types);
        List<Type> types = typeService.findAllTypes();
        System.out.println(types);

    }

    @Autowired
    @Qualifier("TagService")
    TagService tagService;
    @Test
    void contextLoads5() {

        List<Tag> tags = tagService.findAllTags();
        System.out.println(tags);

    }

    @Autowired
    @Qualifier("MessageService")
    MessageService messageService;
    @Test
    void contextLoads6() {
        List<Message> messages = messageService.findMessages();
        System.out.println(messages);
        Message message = new Message();
        message.setNickname("ww");
        message.setContent("aaa");
        messageService.addMessage(message);
    }

    @Autowired
    @Qualifier("UserService")
    UserService userService;
    @Test
    void userTest(){
        User user = userService.login("admin", "admin");
        System.out.println(user);
    }
}
