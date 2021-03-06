package com.scalefocus.beer.etl.configuration;

import com.scalefocus.beer.etl.messaging.consumer.RabbitMqConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.support.GenericMessage;

@Configuration
@EnableBinding(Sink.class)
public class ConsumerConfiguration {
    @Autowired
    private RabbitMqConsumer rabbitMqConsumer;

    /**
     * Setup input channel from RabbitMq which consumes messages from the configured queue
     * (this which correspond to the Sink configuration in applications.yml)
     * @param message - the consumed message from RabbitMq with payload type - String
     */
    @StreamListener(Sink.INPUT)
    public void consumeMessage(GenericMessage<String> message){
        rabbitMqConsumer.processMessage(message);
    }
}
