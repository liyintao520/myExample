package com.lyt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

    @Bean
    public RouteLocator myRoutes(RouteLocatorBuilder builder) {
        return builder.routes().route(p -> p
                // 断言，路由转发的判断条件，path 为使用过滤器修改 /hello
                .path("/hello")
                // 过滤器，路由请求时所经过的过滤逻辑，可用于修改请求、响应内容；
                .filters(f -> f.addRequestHeader("Hello", "World"))
                .uri("http://localhost:8100"))
                .build();
    }
}
