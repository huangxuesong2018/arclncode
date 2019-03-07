package com.gupaoedu.vip.mongo.explorer.common.config;

import com.gupaoedu.vip.mongo.explorer.common.annotation.DepotDB;
import com.gupaoedu.vip.mongo.explorer.common.annotation.ExplorerDB;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

/**
 * @author HXS
 * @copyright
 * @since 2019-03-07
 */
@Configuration
@ConfigurationProperties(prefix="spring.data.mongodb.depot")
public class DepotMongoConfig {
    private String uri;

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    @DepotDB
    @Bean
    public MongoTemplate depotMongoTemplate() {
        //SimpleMongoDbFactory factory = new SimpleMongoDbFactory(new MongoClient("192.168.1.129:27017"), "gp-explorer");
        SimpleMongoDbFactory factory = new SimpleMongoDbFactory(new MongoClientURI(uri));
        return new MongoTemplate(factory);
    }
}
