package com.scalefocus.beer.etl.messaging.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.scalefocus.beer.etl.domain.mapper.DtoMapper;
import com.scalefocus.beer.etl.domain.noSql.NoSqlBeerDTO;
import com.scalefocus.beer.etl.domain.sql.SqlBeerDTO;
import com.scalefocus.beer.etl.service.BeerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Service;
import java.io.IOException;

@Service
public class RabbitMqConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMqConsumer.class);

    @Autowired
    private BeerService beerService;

    @Autowired
    private DtoMapper noSqlToSqlDtoMapper;

    /**
     * Process consumes messages from RabbitMq
     * @param message - the message which is consumed from RabbitMq
     */
    public void processMessage(GenericMessage<String> message){
        String messagePayload = message.getPayload();
        try {
            SqlBeerDTO sqlBeerDTO = new ObjectMapper().readValue(messagePayload, SqlBeerDTO.class);
            NoSqlBeerDTO noSqlBeerDTO = noSqlToSqlDtoMapper.transformSqlToNoSqlDTO(sqlBeerDTO);

            beerService.insertToNoSql(noSqlBeerDTO);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
