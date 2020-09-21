package com.stwen.shardingjdbc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.stwen.shardingjdbc.dao*")
public class SpringbootShardingJdbcApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootShardingJdbcApplication.class, args);
    }

}
