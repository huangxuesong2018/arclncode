package com.gupaoedu.vip.mongo.explorer.service;

import javax.core.common.ResultMsg;
import java.io.File;
import java.util.Map;

public interface IUFileService {
    public ResultMsg<?> createFolder(String group, String uName, String path);

    /**
     * 获取文件列表
     * @param uName
     * @param path
     * @return
     */
    ResultMsg<?> list(String group, String uName, String path);

    /**
     * 获取临时存储目录
     * @return
     */
    ResultMsg<?> getTempPath();

    /**
     * 上传文件
     * @param uName
     * @param path
     * @return
     */
    ResultMsg<?> upload(String group, String uName, String path, Map<String, File> files);
}
