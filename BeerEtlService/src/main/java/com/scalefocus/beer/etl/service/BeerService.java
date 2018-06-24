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

    public void insertToNoSql(List<NoSqlBeerDTO> beerList) {
        List<NoSqlBeerDTO> result = this.mongoRepository.insert(beerList);
    }

    public List<SqlBeerDTO> insertToSql(List<SqlBeerDTO> beerList){
        List<SqlBeerDTO> inserted = Lists.newArrayList(this.sqlRepository.saveAll(beerList));
        emitBeerList(inserted);
        return inserted;
    }

    public void emitBeerList(List<SqlBeerDTO> beerList) {
        rabbitMqPublisher.publishToRabbitMq(beerList);
    }

    public List<NoSqlBeerDTO> transformSqlToNoSqlList(List<SqlBeerDTO> beerList) {
        List<NoSqlBeerDTO> resultList = new ArrayList<>();

        beerList.forEach(x -> resultList.add(noSqlToSqlDtoMapper.transformSqlToNoSqlDTO(x)));

        return resultList;
    }
}
