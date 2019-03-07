package com.gupaoedu.vip.mongo.explorer.repository;

import com.gupaoedu.vip.mongo.explorer.entity.UFile;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UFileRepository extends MongoRepository<UFile,String>{

    //用SpringBoot的好处就是解放双手，你完全不用写实现类
    //约定优于配置
    //编码规范   find开头就是 查   By条件  And 或者 Or ，For返回值
    List<UFile> findByXpath(String xpath);
}
