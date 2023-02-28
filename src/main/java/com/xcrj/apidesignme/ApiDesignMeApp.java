package com.xcrj.apidesignme;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class ApiDesignMeApp {
    public static void main(String[] args) {
        SpringApplication.run(ApiDesignMeApp.class, args);
        System.out.println("ApiDesignMeApp 启动");
    }
}