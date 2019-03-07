package com.gupaoedu.vip.mongo.explorer.repository;

import com.gupaoedu.vip.mongo.explorer.entity.ShortUrl;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShortUrlRepository extends MongoRepository<ShortUrl,String> {

}
