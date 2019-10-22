package com.czp.springcloud.sericefeign.hystrix;

import com.czp.springcloud.sericefeign.api.APIClient;
import org.springframework.stereotype.Component;

@Component
public class APIClienthystrix implements APIClient {
    @Override
    public String test(String name) {
        return "sorry "+name;
    }

    @Override
    public String test() {
        return null;
    }


}
