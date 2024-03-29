package com.czp.springcloud.sericefeign;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @EnableFeignClients开启Feign
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableEurekaClient
@EnableFeignClients
public class SericeFeignApplication {

    public static void main(String[] args) {
        SpringApplication.run(SericeFeignApplication.class, args);
    }

}
