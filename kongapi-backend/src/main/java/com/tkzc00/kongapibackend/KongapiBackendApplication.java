package com.tkzc00.kongapibackend;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.tkzc00.kongapibackend.mapper")
public class KongapiBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(KongapiBackendApplication.class, args);
    }

}
