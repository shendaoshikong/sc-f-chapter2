package com.czp.springcloud.sericefeign.api;

import com.czp.springcloud.sericefeign.hystrix.APIClienthystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Wenyi Feng
 * @since 2018-09-15
 */
@FeignClient(value = "service",fallback = APIClienthystrix.class)
public interface APIClient extends API {

    @GetMapping("/hi")
    String test(@RequestParam(value = "name") String name);
}
