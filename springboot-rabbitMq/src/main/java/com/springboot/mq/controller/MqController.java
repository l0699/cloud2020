package com.springboot.mq.controller;

import com.rabbitmq.client.*;
import com.springboot.mq.config.RabbitMqUtil;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;
@RestController
public class MqController {
    @Autowired
    private RabbitMqUtil rabbitMqUtil;
    @Value("${rabbitmq.port}")
    private String post;
    /**
     * 队列名字
     */
    private static final String QUEUE = "TEST-QUEUE";
    @RequestMapping(value = "/getPost", method = RequestMethod.GET)
    public String getPost() {
        return post;
    }

    @SneakyThrows
    @RequestMapping(value = "/sent", method = RequestMethod.GET)
    public String sent() {
        //获取连接
        Connection connection = rabbitMqUtil.getConnection();
        //创建一个通道
        Channel channel = connection.createChannel();
        //声明队列
        channel.queueDeclare(QUEUE, false, false, false, null);
        for (int i=0;i<=50;i++){
            String MEG = "HELLO-WORLE"+i;
            channel.basicPublish("", QUEUE, null, MEG.getBytes());
        }
        channel.close();
        connection.close();
        return "成功";
    }

    @RequestMapping(value = "/getMeg", method = RequestMethod.GET)
    public String getMeg() {
        //获取连接
        Connection connection = rabbitMqUtil.getConnection();
        try {
            Channel channel = connection.createChannel();
            channel.queueDeclare(QUEUE, false, false, false, null);
            DefaultConsumer defaultConsumer = new DefaultConsumer(channel) {
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    String s = new String(body, "utf-8");
                    System.out.println(s);
                }
            };
            //监听消息
            channel.basicConsume(QUEUE, true, defaultConsumer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "获取成功";
    }
}
