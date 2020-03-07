package com.leyou.upload.config;

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

//    public static void main(String[] args) throws Exception {
////        byte[] bytes = "String".getBytes();
////        for (byte aByte : bytes) {
////            System.out.println(aByte);
////        }
////        List<String> list = new ArrayList<>();
////        list. add("x");
////        Collection<String> clist = Collections. unmodifiableCollection(list);
////        clist. add("y"); // 运行时此行报错
////        System. out. println(list. size());
//
//        Callable<String> callable = new Callable() {
//
//            @Override
//            public Object call() throws Exception {
//                return "aaa";
//            }
//        };
//        String call = callable.call();
//        System.out.println(call);
    }

