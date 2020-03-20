package com.atguigu.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@Slf4j
public class OrderZkController {
    @Autowired
    private RestTemplate restTemplate;
    public static final String INVOK_URL="http://cloud-provider-zk-payment";
    @RequestMapping("get")
    public String getZK(){
        return restTemplate.getForObject(INVOK_URL+"/payment/zk",String.class);
    }
}
