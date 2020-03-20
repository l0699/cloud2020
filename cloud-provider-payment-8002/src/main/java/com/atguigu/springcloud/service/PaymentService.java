package com.atguigu.springcloud.service;

import com.atguigu.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Param;

public interface PaymentService {
    /****
     * 新增
     * @param payment
     * @return
     */
    int create(Payment payment);

    /**
     *
     * @param id
     * @return
     */
    Payment getPaymentById(@Param("id") Long id);
}
