package com.hhjin015.commerce.ecommercev2.product.event;

import org.springframework.context.ApplicationEventPublisher;

import static java.util.Objects.nonNull;

public class Events {

    private static ApplicationEventPublisher publisher;

    static void setPublisher(ApplicationEventPublisher publisher) {
        Events.publisher = publisher;
    }

    public static void raise(Object event) {
        if(nonNull(publisher)) {
            publisher.publishEvent(event);
        }
    }
}
