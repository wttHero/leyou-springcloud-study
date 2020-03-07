package com.leyou.goods.client;

import com.leyou.item.api.SpecificationApi;
import org.springframework.cloud.openfeign.FeignClient;

//利用fegin进行远程调用item-service微服务的接口获取数据
//注意：要使得下面的@FeignClient注解生效，需要在启动类上添加@EnableFeignClients来开启feign支持
@FeignClient(name = "item-service")
public interface SpecificationClient extends SpecificationApi {
}
