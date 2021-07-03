package com.tls.xblog.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tls.xblog.entity.Message;
import com.tls.xblog.service.MessageService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
public class MessageController {
    @Autowired
    @Qualifier("MessageService")
    MessageService messageService;

    @RequestMapping(value = "/message",method = RequestMethod.GET)
    public String getMessage() throws JsonProcessingException {
        List<Message> messages = messageService.findMessages();
        ObjectMapper objectMapper = new ObjectMapper();
        String s = objectMapper.writeValueAsString(messages);
        return s;
    }

    @RequestMapping(value = "/message",method = RequestMethod.PUT)
    public String addMessage(Message message) throws JsonProcessingException {
        messageService.addMessage(message);
        List<Message> messages = messageService.findMessages();
        ObjectMapper objectMapper = new ObjectMapper();
        String s = objectMapper.writeValueAsString(messages);
        return s;
    }

    @RequestMapping(value = "/admin/message",method = RequestMethod.DELETE)
    public String deleteMessage(@RequestParam("id")Long id) throws JsonProcessingException {
        messageService.deleteMessage(id);
        List<Message> messages = messageService.findMessages();
        ObjectMapper objectMapper = new ObjectMapper();
        String s = objectMapper.writeValueAsString(messages);
        return s;
    }

}
