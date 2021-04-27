package com.hon.windy.quickapi;

import com.hon.windy.quickapi.message.MessageEvent;
import com.lmax.disruptor.ExceptionHandler;

public class EventExceptionHandler implements ExceptionHandler<MessageEvent> {

    @Override
    public void handleEventException(Throwable ex, long sequence, MessageEvent event) {

    }

    @Override
    public void handleOnStartException(Throwable ex) {

    }

    @Override
    public void handleOnShutdownException(Throwable ex) {

    }
}
