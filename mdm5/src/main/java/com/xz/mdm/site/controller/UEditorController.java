package com.xz.mdm.site.controller;


import com.alibaba.druid.util.StringUtils;
import com.xz.mdm.commons.PublicMsg;
import com.xz.mdm.commons.Ueditor;
import com.xz.mdm.site.config.AliyunOssApi;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by Administrator on 2017/6/10.
 */
@Controller
@RequestMapping("ueditor")
public class UEditorController {

    /*@RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public Object upload(MultipartFile upfile) {
        Map<String, Object> result = new LinkedHashMap<>();
        // UEDITOR的规则:不为SUCCESS则显示state的内容
        result.put("state", "SUCCESS");
        result.put("url", "");
        result.put("title", upfile.getOriginalFilename());
        result.put("original", upfile.getOriginalFilename());
        try {
            String key = AliyunOssApi.putObject(upfile.getInputStream());
            String url = "https://fogo.oss-cn-shenzhen.aliyuncs.com" + key;
            result.put("url", url);
        } catch (IOException e) {
            result.put("state", "文件上传失败:【" + upfile.getOriginalFilename() + "】");
            e.printStackTrace();
        }
        return result;
    }*/

    private static String location = "F:/image/userphoto/";


    @RequestMapping(value="/config")
    @ResponseBody
    public String ueditor(HttpServletRequest request) {

        return PublicMsg.UEDITOR_CONFIG;
    }

    @RequestMapping(value="/imgUpload")
    @ResponseBody
    public Ueditor imgUpload(MultipartFile upfile) throws IOException{
        Ueditor ueditor = new Ueditor();

        if (upfile.isEmpty() || StringUtils.isEmpty(upfile.getOriginalFilename())){
            return null;
        }
        String contentType = upfile.getContentType();
        if (!contentType.contains("")){
            return null;
        }
        String root_fileName = upfile.getOriginalFilename();
        String fileName = UUID.randomUUID() + ".png";
        String rqUrl = "";
        try {
            File fi = new File(location);
            if (!fi.exists()) {
                fi.mkdirs();
            }
            FileInputStream fileInputStream = (FileInputStream)upfile.getInputStream();
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(location +"/"+ fileName));
            byte[] bs = new byte[1024];
            int len;
            while ((len = fileInputStream.read(bs)) != -1){
                bufferedOutputStream.write(bs, 0, len);
            }
            bufferedOutputStream.flush();
            bufferedOutputStream.close();

            rqUrl = "/site/common/upload/findimg?name=" + fileName;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
        ueditor.setState("SUCCESS");
        ueditor.setUrl(rqUrl);
        return ueditor;
    }


}
