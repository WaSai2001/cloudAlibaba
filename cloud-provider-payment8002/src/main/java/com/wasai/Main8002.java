package com.wasai;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @Author: zhangyu
 * @Date: ${YEAR} ${MOUNTH} ${DATE} ${TIME}
 * @Description:
 **/
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.wasai.mapper")
public class Main8002{
    public static void main(String[] args) {
        SpringApplication.run(Main8002.class, args);
        System.out.println("Hello world!");
    }
}