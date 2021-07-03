package com.tls.xblog.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tls.xblog.entity.Tag;
import com.tls.xblog.service.TagService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class TagController {
    @Autowired
    @Qualifier("TagService")
    TagService tagService;

    @ResponseBody
    @RequestMapping(value = "/tag",method = RequestMethod.GET)
    public String getTag() throws JsonProcessingException {
        List<Tag> tags = tagService.findAllTags();
        ObjectMapper objectMapper = new ObjectMapper();
        String s = objectMapper.writeValueAsString(tags);
        System.out.println(s);
        return s;
    }

    @ResponseBody
    @RequestMapping(value = "/admin/tag",method = RequestMethod.PUT)
    public String insertTag(Tag tag) throws JsonProcessingException {
        return tagService.insertTag(tag);
    }

    @ResponseBody
    @RequestMapping(value = "/admin/tag",method = RequestMethod.POST)
    public String updateTag(Tag tag) throws JsonProcessingException {
        return tagService.updateTag(tag);
    }

    @ResponseBody
    @RequestMapping(value = "/admin/tag",method = RequestMethod.DELETE)
    public String deleteTag(@RequestParam("id")Long id) throws JsonProcessingException {
        return tagService.deleteTag(id);
    }




}
