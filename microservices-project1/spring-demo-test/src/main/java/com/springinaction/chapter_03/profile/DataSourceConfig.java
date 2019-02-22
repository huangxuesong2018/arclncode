package com.springinaction.chapter_03.profile;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jndi.JndiObjectFactoryBean;

import javax.sql.DataSource;

/**
 * @see Profile 是Spring 3 出现的
 * 只有当规定的 profile 激活时，相应的 bean 才会被创建，但
 * 是可能会有其他的 bean 并没有声明在一个给定的 profile 范围内。没有指定 profile 的 bean 始终都会被创建，
 * 与激活哪个 profile 没有关系
 * @author HXS
 * @copyright
 * @since 2019-02-20
 */
@Configuration
public class DataSourceConfig {

    @Bean(destroyMethod = "shutdown")
    @Profile("dev")
    public DataSource dataSource(){
        System.out.println("dev");
        return new EmbeddedDatabaseBuilder()
                .addScript("classpath:chapter_03/schema.sql")
                .addScript("classpath:chapter_03/test-data.sql")
                .build();
    }

    @Bean
    @Profile("prod")
    public DataSource jndiDataSource() {
        System.out.println("prod");
        JndiObjectFactoryBean jndiObjectFactoryBean = new JndiObjectFactoryBean();
        jndiObjectFactoryBean.setJndiName("jdbc/myDS");
        jndiObjectFactoryBean.setResourceRef(true);
        jndiObjectFactoryBean.setProxyInterface(DataSource.class);
        return (DataSource) jndiObjectFactoryBean.getObject();
    }

}
