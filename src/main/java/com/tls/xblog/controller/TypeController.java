package com.tls.xblog.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tls.xblog.entity.Comment;
import com.tls.xblog.entity.Type;
import com.tls.xblog.service.CommentService;
import com.tls.xblog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class TypeController {
    @Autowired
    @Qualifier("TypeService")
    TypeService typeService;

    /*@ResponseBody
    @RequestMapping(value = "/type",method = RequestMethod.POST)
    public String test2() throws JsonProcessingException {
        List<Type> types = typeService.findAllTypesHasBlog();
        ObjectMapper objectMapper = new ObjectMapper();
        String s = objectMapper.writeValueAsString(types);
        return s;
    }*/

    @ResponseBody
    @RequestMapping(value = "/typeAll",method = RequestMethod.POST)
    public String test3() throws JsonProcessingException {
        List<Type> types = typeService.findAllTypes();
        ObjectMapper objectMapper = new ObjectMapper();
        String s = objectMapper.writeValueAsString(types);
        //System.out.println(s);
        return s;
    }

    @ResponseBody
    @RequestMapping(value = "/type",method = RequestMethod.GET)
    public String getType() throws JsonProcessingException {
        List<Type> types = typeService.findAllTypes();
        ObjectMapper objectMapper = new ObjectMapper();
        String s = objectMapper.writeValueAsString(types);
        //System.out.println(s);
        return s;
    }

    @ResponseBody
    @RequestMapping(value = "/type",method = RequestMethod.PUT)
    public String insertType( Type type ) throws JsonProcessingException {
        return typeService.insertType(type);
    }
    @ResponseBody
    @RequestMapping(value = "/admin/type",method = RequestMethod.POST)
    public String updateType( Type type ) throws JsonProcessingException {
        return typeService.updateType(type);
    }
    @ResponseBody
    @RequestMapping(value = "/admin/type",method = RequestMethod.DELETE)
    public String deleteType(@RequestParam("id") Long id) throws JsonProcessingException {
        return typeService.deleteType(id);
    }



}
