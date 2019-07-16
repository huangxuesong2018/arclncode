package com.xz.mdm.site.controller;


import com.alibaba.druid.util.StringUtils;
import com.xz.mdm.site.config.AliyunOssApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.UUID;

/**
 * Created by Administrator on 2017/4/22.
 */
@Controller
@RequestMapping("site/common/upload")
public class UploadController {

    private static Logger logger = LoggerFactory.getLogger(UploadController.class);

    private static String location = "F:/image/userphoto/";

    @RequestMapping(value = "/batchFileUpload", method = RequestMethod.POST)
    @ResponseBody
    public String uploadImages(MultipartFile file, HttpServletRequest request) throws IOException {
        //这里写的不是太好，应该放到初始化里面，再用单例
        if (file.isEmpty() || StringUtils.isEmpty(file.getOriginalFilename())){
            return null;
        }
        String contentType = file.getContentType();
        if (!contentType.contains("")){
            return null;
        }
        String root_fileName = file.getOriginalFilename();
        logger.info("上传图片:name={},type={}",root_fileName, contentType);
        String fileName = UUID.randomUUID() + ".png";
        try {
            File fi = new File(location);
            if (!fi.exists()) {
                fi.mkdirs();
            }
            FileInputStream fileInputStream = (FileInputStream)file.getInputStream();
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(location +"/"+ fileName));
            byte[] bs = new byte[1024];
            int len;
            while ((len = fileInputStream.read(bs)) != -1){
                bufferedOutputStream.write(bs, 0, len);
            }
            bufferedOutputStream.flush();
            bufferedOutputStream.close();

            String rqUrl = "/site/common/upload/findimg?name=" + fileName;
            return rqUrl;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


    /**
     *  获取图片流
     * @param name
     * @param response
     */
    @RequestMapping(value = "/findimg", method = RequestMethod.GET)
    public static void show(String name, HttpServletResponse response){
        try {
            response.setContentType("image/png");
            File file = new File(location + name);
            FileInputStream inputStream = new FileInputStream(file);
            if (inputStream != null){
                //得到文件大小
                int i = inputStream.available();
                byte[] data = new byte[i];
                //读数据
                inputStream.read(data);
                inputStream.close();
                OutputStream outputStream = response.getOutputStream();
                outputStream.write(data);
                outputStream.close();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }



}
