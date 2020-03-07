package com.leyou.goods.client;

import com.leyou.item.api.GoodsApi;
import org.springframework.cloud.openfeign.FeignClient;


//利用fegin进行远程调用item-service微服务的接口获取数据
//注意：要使得下面的@FeignClient注解生效，需要在启动类上添加@EnableFeignClients来开启feign支持
//fallback = GoodsClientFallBack.class 指定GoodsClientFallBack.class里的熔断方法
//方法熔断触发条件（远程调用服务停止时即item-service停了）
// 注意：需要在springboot启动类上添加@EnableCircuitBreaker 熔断才能生效
@FeignClient(name = "item-service",fallback=GoodsClientFallBack.class)
public interface GoodsClient extends GoodsApi {

}
