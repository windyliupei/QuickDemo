package com.hon.windy.quickapi.service;


import com.hon.windy.quickapi.EventExceptionHandler;
import com.hon.windy.quickapi.message.MessageEvent;
import com.hon.windy.quickapi.message.MessageEventFactory;
import com.hon.windy.quickapi.message.MessageEventHandler;
import com.lmax.disruptor.*;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import com.windowsazure.messaging.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Service
public class HelloService {



    @Qualifier("taskExecutor")
    @Autowired
    public Executor executor;

    Disruptor<MessageEvent> disruptor;

    @PostConstruct
    public void init() throws NotificationHubsException {
        EventFactory<MessageEvent> eventFactory = new MessageEventFactory();
        int ringBufferSize = 1024 * 1024; // RingBuffer 大小，必须是 2 的 N 次方；

        disruptor = new Disruptor<MessageEvent>(eventFactory,
                ringBufferSize, executor, ProducerType.MULTI,
                new BlockingWaitStrategy());

        WorkHandler<MessageEvent> eventHandler1 = new MessageEventHandler("Handler1");
        WorkHandler<MessageEvent> eventHandler2 = new MessageEventHandler("Handler2");
        WorkHandler<MessageEvent> eventHandler3 = new MessageEventHandler("Handler3");
        WorkHandler<MessageEvent> eventHandler4 = new MessageEventHandler("Handler4");
        //同时处理
        //disruptor.handleEventsWith(eventHandler1,eventHandler2,eventHandler3,eventHandler4);
        disruptor.handleEventsWithWorkerPool(eventHandler1,eventHandler2,eventHandler3,eventHandler4);
        disruptor.start();
    }

    @PreDestroy
    public void shutDown(){
        disruptor.shutdown();//关闭 disruptor，方法会堵塞，直至所有的事件都得到处理；
    }

    public void push(String message){
        // 发布事件；
        RingBuffer<MessageEvent> ringBuffer = disruptor.getRingBuffer();
        long sequence = ringBuffer.next();//请求下一个事件序号；

        try {
            MessageEvent event = ringBuffer.get(sequence);//获取该序号对应的事件对象；
            event.setValue(message);
        } finally{
            ringBuffer.publish(sequence);//发布事件；
        }
    }


}
