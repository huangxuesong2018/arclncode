package com.gupaoedu.vip.mongo.explorer.mvc.controller;

import com.alibaba.fastjson.JSON;
import com.gupaoedu.vip.mongo.explorer.constants.ExplorerConstants;
import com.gupaoedu.vip.mongo.explorer.service.IUFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;

import javax.core.common.ResultMsg;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author HXS
 * @copyright
 * @since 2019-03-07
 */
@RestController
@RequestMapping("/web")
public class ExplorerController {
    @Autowired
    private IUFileService uFileService;

    @RequestMapping("/upload/progress.json")
    public ResponseEntity progress(@RequestParam("X-Progress-ID") String progressId,
                                   @RequestParam("callback") String callback){
        Progress progress = new Progress();
        progress.setFinish(1);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");

        String json = JSON.toJSONString(progress);

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.parseMediaType("application/json"))
                .body(((callback == null) ? json : (callback +"(" + json + ")")));
    }

    class Progress{
        private long received = 0;
        private long size = 0;
        private long finish = 0;

        public long getReceived() {
            return received;
        }
        public void setReceived(long received) {
            this.received = received;
        }
        public long getSize() {
            return size;
        }
        public void setSize(long size) {
            this.size = size;
        }
        public long getFinish() {
            return finish;
        }
        public void setFinish(long finish) {
            this.finish = finish;
        }
    }

    @RequestMapping(value = "createFolder.json")
    public Mono<Object> createFolder(@RequestParam String path,@RequestParam String name){
        String group = ExplorerConstants.FILE_GROUP_USERS;
        String uName = ExplorerConstants.ANONYMOUS;
        String pathStr = path.replace("my:","") + "/" + name;
        ResultMsg<?> msg = uFileService.createFolder(group,uName,pathStr);
        return Mono.just(msg);
    }

    @RequestMapping(value="/list.json")
    @ResponseBody
    public Mono<Object> list(@RequestParam(name="path") String path){
        String group = ExplorerConstants.FILE_GROUP_USERS;
        String uName = ExplorerConstants.ANONYMOUS;
        String pathStr = (path + "/").replaceAll("/+","/");
        ResultMsg<?> msg = uFileService.list(group,uName,pathStr);
        return Mono.just(msg);
    }


    /**
     * 在个人目录下上传文件
     */
    @PostMapping(value="/upload.json")
    public Mono<Object> upload(@RequestParam("upload") List<MultipartFile> files,  //默认支持多文件同时上传
                               @RequestParam("X-Progress-ID") String progressId,
                               @RequestParam("path") String path) {  //文件上传到哪个路径
        final int bufferSize = 1024 * 100;
        Map<String, File> uploads = new HashMap<String, File>();
        for (MultipartFile file : files) {
            try{
                InputStream stream = file.getInputStream();
                String fileName = file.getOriginalFilename();
                //正式环境，我会改成一个多线程
                String filePath = uFileService.getTempPath().getData() + progressId + ".temp";
                File f = new File(filePath);
                OutputStream bos = new FileOutputStream(f);
                int bytesRead = 0;
                byte[] buffer = new byte[bufferSize];
                while ((bytesRead = stream.read(buffer, 0, bufferSize)) != -1) {
                    bos.write(buffer, 0, bytesRead);
                }
                bos.close();
                stream.close();
                uploads.put(fileName, f);
            }catch(Exception e){
                e.printStackTrace();
            }
        }

        String group = ExplorerConstants.FILE_GROUP_USERS;
        String uName = ExplorerConstants.ANONYMOUS;
        ResultMsg<?> msg = uFileService.upload(group, uName, path, uploads);
        return Mono.just(msg);

    }
}
