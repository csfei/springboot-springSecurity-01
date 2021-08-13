package com.cuisf;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.cuisf.mapper")
public class SpringbootSecurity01Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootSecurity01Application.class, args);
    }

}
