package com.leyou.item;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.leyou.item.mapper")
@EnableDiscoveryClient
public class LeyouItemServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(LeyouItemServiceApplication.class,args);
    }
}
