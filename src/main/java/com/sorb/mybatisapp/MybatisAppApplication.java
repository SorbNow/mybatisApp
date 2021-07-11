package com.sorb.mybatisapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class MybatisAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybatisAppApplication.class, args);
    }

}
