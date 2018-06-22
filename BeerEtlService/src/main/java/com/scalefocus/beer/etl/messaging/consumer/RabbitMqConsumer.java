package com.scalefocus.beer.etl.messaging.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.scalefocus.beer.etl.domain.noSql.NoSqlBeerDTO;
import com.scalefocus.beer.etl.domain.sql.SqlBeerDTO;
import com.scalefocus.beer.etl.service.BeerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class RabbitMqConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMqConsumer.class);

    @Autowired
    private BeerService beerService;

    public void processMessaage(GenericMessage<String> message){
        String messagePaylod = message.getPayload();
        try {
            List<SqlBeerDTO> sqlBeerDTOList = new ObjectMapper().readerFor(SqlBeerDTO.class).readValue(messagePaylod);
            List<NoSqlBeerDTO> noSqlBeerDTOList = beerService.transformSqlToNoSqlList(sqlBeerDTOList);

            beerService.insertToNoSql(noSqlBeerDTOList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
