package com.atguigu.springcloud.service;

import org.springframework.stereotype.Component;

@Component
public class PaymentFallbackService implements PaymentHystrixService {
    @Override
    public String paymentInfo_OK(Integer id) {
        return "发生异常或者请求超时，请稍后再试";
    }

    @Override
    public String paymentInfo_TimeOut(Integer id) {
        return "发生异常或者请求超时，请稍后再试";
    }
}
