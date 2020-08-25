package com.sy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication(scanBasePackages = "com.sy")
//扫描mapper接口(使用TkMapper组件)
@MapperScan(value = "com.sy.bmq.mapper")
//容器启动优先级(可以不加)
@Order(Ordered.HIGHEST_PRECEDENCE)
//开启注解事务(可以不加)
@EnableTransactionManagement
//引入子配置项
@Import(value = {SpringShiroConfig.class,SpringEsConfig.class,CasConfig.class,ShiroConfig.class})
//扫描ES包
@EnableElasticsearchRepositories(basePackages= "com.sy.bmq.es")
public class SlshopApplication {

    public static void main(String[] args) {
        SpringApplication.run(SlshopApplication.class, args);
    }

}
