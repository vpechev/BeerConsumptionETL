package com.scalefocus.beer.etl.service;

import com.google.common.collect.Lists;
import com.scalefocus.beer.etl.domain.mapper.DtoMapper;
import com.scalefocus.beer.etl.domain.noSql.NoSqlBeerDTO;
import com.scalefocus.beer.etl.domain.sql.SqlBeerDTO;
import com.scalefocus.beer.etl.messaging.publisher.RabbitMqPublisher;
import com.scalefocus.beer.etl.repository.BeerMongoRepository;
import com.scalefocus.beer.etl.repository.BeerSqlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BeerService {
    @Autowired
    private BeerMongoRepository mongoRepository;
    @Autowired
    private BeerSqlRepository sqlRepository;
    @Autowired
    private DtoMapper noSqlToSqlDtoMapper;
    @Autowired
    private RabbitMqPublisher rabbitMqPublisher;

    /**
     * Call persistence API in order to store the passed list in NoSql database
     * @param beerList - list of NoSqlBeerDTO objects
     */
    public void insertToNoSql(List<NoSqlBeerDTO> beerList) {
        List<NoSqlBeerDTO> result = this.mongoRepository.insert(beerList);
    }

    /**
     * Call persistence API in order to store the passed object in NoSql database
     * @param beerDto - SqlBeerDTO object
     */
    public void insertToNoSql(NoSqlBeerDTO beerDto) {
        NoSqlBeerDTO result = this.mongoRepository.insert(beerDto);
    }

    /**
     * Call persistence API in order to store the passed list in SQL database
     * @param beerList - list of SqlBeerDTO objects
     */
    public List<SqlBeerDTO> insertToSql(List<SqlBeerDTO> beerList){
        List<SqlBeerDTO> inserted = Lists.newArrayList(this.sqlRepository.saveAll(beerList));
        emitBeerList(inserted);
        return inserted;
    }

    /**
     * Call RabbitMqPublish in order to send the list to RabbitMq
     * @param beerList - list of SqlBeerDTO object
     */
    public void emitBeerList(List<SqlBeerDTO> beerList) {
        rabbitMqPublisher.publishToRabbitMq(beerList);
    }
}
