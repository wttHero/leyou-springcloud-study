package com.leyou.goods;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

//取消数据库的自动配置
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages="com.leyou.goods.client")//开启feign支持
@EnableCircuitBreaker //开启熔断器
public class LeyouGoodsWebApplication {
    public static void main(String[] args) {
        SpringApplication.run(LeyouGoodsWebApplication.class,args);
    }
}
