package com.tls.xblog.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import java.util.HashMap;
import java.util.Map;


/**
*文件上传的大小异常在进入controller前就产生，
因此无法在controller中捕获
*/
@ControllerAdvice
public class MyException {



    @ResponseBody
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public Map handleMaxUploadSizeExceededException(MaxUploadSizeExceededException e) throws JsonProcessingException {
        HashMap map = new HashMap();
        map.put("success",0);
        map.put("message","单文件最大2MB, 单请求最大10MB！");
        map.put("url","null");
        return map;
    }


}
