package com.wasai;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @Author: zhangyu
 * @Date: ${YEAR} ${MOUNTH} ${DATE} ${TIME}
 * @Description:
 **/
@SpringBootApplication
@MapperScan("com.wasai.mapper")
public class Main8001 {
    public static void main(String[] args) {
        SpringApplication.run(Main8001.class, args);
        System.out.println("Hello world!");
    }
}