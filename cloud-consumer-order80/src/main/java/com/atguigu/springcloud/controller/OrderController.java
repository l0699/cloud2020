package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * 微服务的消费者
 */
@Slf4j
@RestController
@RequestMapping("/consumer/payment")
public class OrderController {
    @Autowired
    private RestTemplate restTemplate;
    //public  static  String PAYMENT_URL="http://localhost:8001";
    private final static String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE";//集群
    @GetMapping("/create")
    public CommonResult<Payment> create(Payment payment){
        CommonResult commonResult =
                restTemplate.postForObject(PAYMENT_URL + "/payment/create", payment, CommonResult.class);
        return commonResult;
    }
    @GetMapping("/getPaymentById/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id){
        CommonResult forObject = restTemplate.getForObject(PAYMENT_URL + "/payment/get/" + id, CommonResult.class);
        return forObject;
    }
    @GetMapping("/getPaymentById2/{id}")
    public CommonResult<Payment> getPaymentById2(@PathVariable("id") Long id){
       ResponseEntity<CommonResult> entity= restTemplate.getForEntity(PAYMENT_URL + "/payment/get/" + id, CommonResult.class);
        if (entity.getStatusCode().is2xxSuccessful()) {
            System.out.println("---------------------------");
            return entity.getBody();
        } else {
            System.out.println("-----------------");
            return new CommonResult<>(444, "操作失败");
        }
    }
}
