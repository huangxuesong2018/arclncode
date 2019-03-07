package com.gupaoedu.vip.mongo.explorer.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.gupaoedu.vip.mongo.explorer.common.config.ExplorerConfig;
import com.gupaoedu.vip.mongo.explorer.constants.ExplorerConstants;
import com.gupaoedu.vip.mongo.explorer.constants.SystemConstant;
import com.gupaoedu.vip.mongo.explorer.entity.OFile;
import com.gupaoedu.vip.mongo.explorer.entity.ShortUrl;
import com.gupaoedu.vip.mongo.explorer.entity.UFile;
import com.gupaoedu.vip.mongo.explorer.repository.DepotRepository;
import com.gupaoedu.vip.mongo.explorer.repository.ShortUrlRepository;
import com.gupaoedu.vip.mongo.explorer.repository.UFileRepository;
import com.gupaoedu.vip.mongo.explorer.service.IUFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.core.common.ResultMsg;
import javax.core.common.encrypt.DESStaticKey;
import javax.core.common.utils.FileUtils;
import javax.core.common.utils.ShortUrlUtils;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author HXS
 * @copyright
 * @since 2019-03-07
 */
@Service
public class UFileService implements IUFileService {
    private final static String KEY = "TFmrluhJJC/=AiisvHkxutuyKGwzn3JhZfxX";

    @Autowired
    private UFileRepository uFileRepository;

    @Autowired
    private DepotRepository depotRepository;

    @Autowired
    private ShortUrlRepository shortUrlRepository;

    @Autowired
    private ExplorerConfig explorerConfig;
    @Override
    public ResultMsg<?> createFolder(String group, String uname, String path) {
        UFile uFile = new UFile(path);
        pushRequired(group,uname,uFile,null);
        UFile result = uFileRepository.insert(uFile);
        return new ResultMsg(SystemConstant.RESULT_STATUS_SUCCESS, result);
    }

    @Override
    public ResultMsg<?> list(String group, String uName, String path) {
        JSONArray result = new JSONArray();
        if(StringUtils.isEmpty(uName)){
            uName = ExplorerConstants.ANONYMOUS;
        }

        List<UFile> list = uFileRepository.findByXpath(path);
        for (UFile uf : list) {
            if(uf.getFtype().equals(ExplorerConstants.FILE_TYPE_DIR)){
                JSONObject fo = packageFile(uf);
                fo.put("type", "dir");
                result.add(fo);
            }else{
                String url = uf.getId();
                JSONObject fo = packageFile(uf);
                fo.put("url", url);
                fo.put("ext", uf.getExt());
                fo.put("size", uf.getFsize());
                fo.put("last", uf.getLastModified());
                fo.put("type", "file");
                result.add(fo);
            }
        }
        return new ResultMsg<Object>(SystemConstant.RESULT_STATUS_SUCCESS, "",result);
    }

    @Override
    public ResultMsg<?> getTempPath() {
        ResultMsg<String> path = new ResultMsg<String>(SystemConstant.RESULT_STATUS_SUCCESS);
        File tempDir = new File(explorerConfig.getTempRootDir());
        if(!tempDir.exists()){ tempDir.mkdirs(); }

        path.setData(tempDir.getAbsolutePath());
        return path;
    }

    @Override
    public ResultMsg<?> upload(String group, String uName, String path, Map<String, File> files) {
       try {
            JSONArray result = new JSONArray();
            List<ShortUrl> urls = new ArrayList<ShortUrl>();
            path = path.replaceAll("//", "/");
            for (String fileName : files.keySet()) {
                File src = files.get(fileName);
                UFile f = new UFile((path + "/" + fileName).replaceAll("/+","/"));
                f.setCreateTime(System.currentTimeMillis());
                f.setLastModified(System.currentTimeMillis());
                f.setFsize(src.length());
                f.setFgroup(group);
                f.setOwner(uName);

                //保存到文件仓库，保存到MongoDB
                OFile oFile = new OFile(src);
                oFile.setName(fileName);
                String objectId = depotRepository.putFile(oFile);

                //用户的数据库，objectId  就是一个 MD5的值
                f.setObjectId(objectId);
                uFileRepository.insert(f);

                //保存到用户FTP目录，相当于本地磁盘
                String realPath = explorerConfig.getUsersRootDir() +
                        uName.replaceAll("@|\\.", "/") + "/" +
                        objectId.replaceAll("(.{3})", "$1/") + path + "/";



                File udir = new File(realPath.replaceAll("//", "/"));
                if(!udir.exists()){ udir.mkdirs(); }
                FileUtils.copyFile(src.getAbsolutePath(), realPath + fileName);

                //生成短地址
                String url = DESStaticKey.encrypt(uName + "|" + objectId +"|" + path + "/" + fileName, KEY);
                String surl = ShortUrlUtils.getShortUrl(url);
                urls.add(new ShortUrl(surl, url));

                //组装返回值
                String ext = fileName.substring(fileName.lastIndexOf("."));
                JSONObject o = new JSONObject();
                o.put("originalName", path + fileName);
                o.put("name", fileName);
                o.put("url", surl);
                o.put("size", src.length());
                o.put("type", ext);
                o.put("last", src.lastModified());

                result.add(o);

                src.delete();
            }
            //生成短链接
            shortUrlRepository.saveAll(urls);

            return new ResultMsg<Object>(SystemConstant.RESULT_STATUS_SUCCESS,"",result);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultMsg<Object>(SystemConstant.RESULT_STATUS_FAILED,"上传失败");
        }
    }

    private JSONObject packageFile(UFile uf){
        JSONObject fo = new JSONObject();
        fo.put("id", uf.getId());
        fo.put("pid", uf.getPid());
        fo.put("name", uf.getFname());
        fo.put("xpath", uf.getXpath());
        fo.put("fgroup", uf.getFgroup());
        return fo;
    }

    private void pushRequired(String group, String name, UFile ufile, File file){
        ufile.setOwner(name);
        long curr = System.currentTimeMillis();
        ufile.setCreateTime(curr);
        ufile.setLastModified(curr);
        ufile.setFgroup(group);
        if(null != file && file.isFile()){
            ufile.setFsize(file.length());
            ufile.setLastModified(file.lastModified());
        }

    }
}
