package com.mqtest.rss;

import com.mqtest.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

/**
 * @author: hr222
 * @create: 2019-10-27 19:23
 * @description: 订阅模式中,不需要创建一个队列,而是通过交换机发送到消息队列中去.这里需要注意交换机发送到消息队列中去若是存在多个消费者这里只有一个
 * 消费者能消费该信息.还有交换机本身不会对信息进行持久化
 **/
public class Send {

    private final static String EXCHANGE_NAME = "test_exchange_fanout";

    public static void main(String[] argv) throws Exception {
        // 获取到连接以及mq通道
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();

        // 声明exchange
        channel.exchangeDeclare(EXCHANGE_NAME, "fanout");

        // 消息内容
        String message = "Hello World!";
        channel.basicPublish(EXCHANGE_NAME, "", null, message.getBytes());
        System.out.println(" [x] Sent '" + message + "'");

        channel.close();
        connection.close();
    }

}
