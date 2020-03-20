package com.atguigu.springcloud.dao;

import com.atguigu.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface PaymentDao {
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
