package com.hon.windy.quickapi.message;
import com.lmax.disruptor.EventFactory;

public class MessageEventFactory implements EventFactory<MessageEvent>{


    public MessageEvent newInstance()
    {
        return new MessageEvent();
    }

}
