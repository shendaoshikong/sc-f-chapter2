package com.czp.springcloud.sericefeign.controller;

import com.czp.springcloud.sericefeign.api.APIClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class Controller {

    @Autowired
    APIClient apiclient;

//    @RequestMapping(value = "/hi",method = RequestMethod.GET)
//    public String test(@RequestParam(value = "name") String name){
//        return "hi";
//    }

    @GetMapping("/hi")
    public String test2(@RequestParam String name) {
        return apiclient.test(name);
    }

}
