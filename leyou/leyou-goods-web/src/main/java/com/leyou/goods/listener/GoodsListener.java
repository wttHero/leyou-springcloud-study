package com.leyou.goods.listener;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

//监听goods的队列
//如果需要定义多个监听器，则只需要去定义多个有@RabbitListener注解的方法即可
@Component
public class GoodsListener {
    public GoodsListener() {
        System.out.println("GoodsListener");
    }

    //定义监听队列消息的方法(针对查询goods时发送的消息)
    //@QueueBinding中value @Queue中的value 是队列的名称，durable = "true" 表示度列持久化
    //exchange的@Exchange中的value = "LEYOU.ITEM.EXCHANGE"是交换机名称（发送队列消息的时候定义的，不定义则默认使用applicaation.yml中配置的默认exchange）
    //ignoreDeclarationExceptions = "true"是忽略交互机重复声明，如果有则直接使用已创建好的同名交换机
    //key = {"item.select"} key中表示发送消息是给定的routingKey
    //注意：下面的方法如果有异常一定要抛出，这样spring的aop机制会自动进行apk的手动校验，即出异常了，不会去消费队列中的消息
    @RabbitListener(bindings =@QueueBinding(value = @Queue(value="LEYOU.ITEM.SELECT.QUEUE",durable = "true"),
    exchange = @Exchange(value = "LEYOU.ITEM.EXCHANGE",ignoreDeclarationExceptions = "true",type = ExchangeTypes.TOPIC)
    ,key = {"item.select"}))
    public void goodsSelectMessage(Long id){
        if(id==null){
            return;
        }
        //执行队列处理逻辑，如更新数据，发送短信之类的
        //下面为了演示操作，打印一下消息的内容
        System.out.println("消费一个消息："+id);

    }

    //定义监听队列消息的方法(针对添加goods时发送的消息)
    @RabbitListener(bindings = @QueueBinding(value = @Queue(value = "LEYOU.ITEM.DELETE.QUEUE",durable = "true")
    ,exchange = @Exchange(value = "LEYOU.ITEM.EXCHANGE",ignoreDeclarationExceptions = "true"),
    key = {"item.delete"}))
    public void goodsDeleteMessage(Long id){
            if(id==null){
                return;
            }
            //执行队列处理逻辑，如更新数据，发送短信之类的
            //下面为了演示操作，打印一下消息的内容
            System.out.println("消费一个消息："+id);

        }

}
