package com.czp.spingcloud.serviceclient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 通过注解@EnableEurekaClient 表明自己是一个eurekaclient（服务提供者）.
 * 仅仅@EnableEurekaClient是不够的，还需要在配置文件中注明自己的服务注册中心的地址，
 *
 * Spring4.3中引进了｛@GetMapping、@PostMapping、@PutMapping、@DeleteMapping、
 * @PatchMapping｝，来帮助简化常用的HTTP方法的映射，并更好地表达被注解方法的语义。
 *
 * @GetMapping是一个组合注解，是@RequestMapping(method = RequestMethod.GET)的缩
 * 写。该注解将HTTP Get 映射到 特定的处理方法上。
 */

@SpringBootApplication
@EnableDiscoveryClient
@RestController
public class ServiceClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceClientApplication.class, args);
    }

    @GetMapping("/service2")
    public String service(){
        return "service";
    }

    @Value("${server.port}")
    String port;

    @GetMapping("/hi")
    public String home(@RequestParam(value = "name", defaultValue = "czp") String name) {
        return "hellow world!! " + name + " ,i am from port:" + port;
    }

    @GetMapping("/service")
    public String service2(){
        System.out.println("白页");
        return "service";
    }

    @GetMapping("404")
    public String e404() {
        System.out.println("404............");
        return "这真的是一个404页面，你看看";
    }

}
