package com.tls.xblog.controller;

import org.springframework.http.HttpRequest;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.RequestContext;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/file")
public class FileConrtoller {
    @RequestMapping(value = "/upload" , method = RequestMethod.POST)
    public String upload(@RequestPart("file") MultipartFile multipartFile) throws IOException {


        return "单文件上传成功";

    }

    @RequestMapping(value = "/uploads" , method = RequestMethod.POST)
    public String uploads(@RequestPart("files") MultipartFile[] multipartFiles) throws IOException {
        String path =ResourceUtils.getURL("classpath:").getPath()+"/static/";
        System.out.println(path);
        for (MultipartFile mf : multipartFiles){
            if (mf.getSize()!=0){
                mf.transferTo(new File(path+mf.getOriginalFilename()));
            }
        }
        return "多文件上传成功";

    }
}
