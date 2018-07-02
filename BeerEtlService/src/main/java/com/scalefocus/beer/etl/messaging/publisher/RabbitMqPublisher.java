package com.scalefocus.beer.etl.messaging.publisher;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.scalefocus.beer.etl.domain.sql.SqlBeerDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@EnableBinding(Source.class)
public class RabbitMqPublisher {
    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMqPublisher.class);
    private final String TYPE_HEADERS_LABEL = "type";
    private final String TYPE_HEADERS_VALUE = "NoSqlBeerEntity";

    @Autowired
    private Source sourceChannel;

    //TODO it is better to make this method async
    /**
     * Itterate the passed list of objects and publish everyone of them to RabbitMq
     * @param beersList - the list which
     */
    public void publishToRabbitMq(List<SqlBeerDTO> beersList){
        ObjectMapper objectMapper = new ObjectMapper();

        beersList.forEach(x->{
            try {
                String jsonMessage = objectMapper.writeValueAsString(x);
                MessageBuilder<String> messageBuilder = MessageBuilder.withPayload(jsonMessage);
                messageBuilder.setHeader(TYPE_HEADERS_LABEL, TYPE_HEADERS_VALUE);

                sourceChannel.output().send(messageBuilder.build());
            } catch(JsonProcessingException e) {
                LOGGER.error("Error on publishing a beer entity <<<id={}>>> to RabbitMQ. \nStacktrace: ", x.getId(), e);
            }
        });
    }
}