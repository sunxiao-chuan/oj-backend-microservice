package com.sxc.ojbackendgateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
// 开启服务发现
@EnableDiscoveryClient
public class OjBackendGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(OjBackendGatewayApplication.class, args);
    }

}
