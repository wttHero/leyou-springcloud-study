package com.leyou.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class LeyouCorsConfiguration {

    @Bean
    public CorsFilter createFilter() {
        //初始化cors配置对象
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        //添加允许跨域的域名（可多个），如果要携带cookie则不能写*, *:代表所有域名
        corsConfiguration.addAllowedOrigin("http://manage.leyou.com");
        //添加是否允许携带cookie true:是 false:否
        corsConfiguration.setAllowCredentials(true);
        //添加跨域的请求方式，*: 代表允许所有请求方式
        corsConfiguration.addAllowedMethod("*");
        //添加头信息，*: 代表允许所有头信息
        corsConfiguration.addAllowedHeader("*");

        //初始化cors配置源对象
        UrlBasedCorsConfigurationSource corsConfigurationSource = new UrlBasedCorsConfigurationSource();
        corsConfigurationSource.registerCorsConfiguration("/**",corsConfiguration);
        //返回corsFilter实例，参数：cors配置源对象
        return new CorsFilter(corsConfigurationSource);
    }
}
