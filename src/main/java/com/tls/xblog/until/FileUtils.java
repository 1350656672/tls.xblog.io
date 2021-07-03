package com.tls.xblog.until;

import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class FileUtils {

    public static String randomFileName() {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String fileName = simpleDateFormat.format(date);
        return fileName;
    }


    public static String upload (MultipartFile mf, String dir) throws IOException {
        //File fileDir = new File("T:\\Java\\upload\\"+dir);
        File fileDir = new File(ResourceUtils.getURL("classpath:").getPath()+"/static/"+dir);
        if (!fileDir.exists()){
            fileDir.mkdir();
        }
        String fileName = randomFileName()+mf.getOriginalFilename();
        File file = new File(fileDir + File.separator + fileName);
        mf.transferTo(file);
        //return file.getPath();
        return "http://localhost:8080/"+dir+"/"+fileName;
    }

    public static String uploads (MultipartFile mfs[],String dir) throws IOException {
        /*File fileDir = new File("T:\\Java\\upload\\"+dir);*/
        File fileDir = new File(ResourceUtils.getURL("classpath:").getPath()+"/static/"+dir);
        if (!fileDir.exists()){
            fileDir.mkdir();
        }
        for (MultipartFile mf :mfs){
            if (mf.getSize()!=0){
                File file = new File(fileDir + File.separator + mf.getOriginalFilename());
                mf.transferTo(file);
            }
        }
        return fileDir.getPath();
    }
}
