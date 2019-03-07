package com.gupaoedu.vip.mongo.explorer.common.config;

import com.gupaoedu.vip.mongo.explorer.common.annotation.ExplorerDB;
import com.mongodb.MongoClientURI;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

@Configuration
@ConfigurationProperties(prefix="spring.data.mongodb.explorer")
public class ExplorerMongoConfig {
    private String uri;

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    /**
     * 默认的 MongoTemplate
     * @return
     */
    @ExplorerDB
    @Bean
    public MongoTemplate mongoTemplate() {
        //SimpleMongoDbFactory factory = new SimpleMongoDbFactory(new MongoClient("192.168.1.129:27017"), "gp-explorer");
        SimpleMongoDbFactory factory = new SimpleMongoDbFactory(new MongoClientURI(uri));
        return new MongoTemplate(factory);
    }
}
