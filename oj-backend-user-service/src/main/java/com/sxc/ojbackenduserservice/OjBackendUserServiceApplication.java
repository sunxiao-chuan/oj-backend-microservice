package com.sxc.ojbackenduserservice;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@MapperScan("com.sxc.ojbackenduserservice.mapper")
@EnableScheduling
@EnableAspectJAutoProxy(proxyTargetClass = true, exposeProxy = true)
@ComponentScan("com.sxc")
//开启服务发现、
@EnableDiscoveryClient
//找到对应的客户端 Bean 的位置.指定 Feign 客户端接口所在的包路径
@EnableFeignClients(basePackages = {"com.sxc.ojbackendserviceclient.service"})

public class OjBackendUserServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(OjBackendUserServiceApplication.class, args);
    }
}
