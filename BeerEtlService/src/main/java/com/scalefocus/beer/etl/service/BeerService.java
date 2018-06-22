package com.scalefocus.beer.etl.service;

import com.scalefocus.beer.etl.domain.mapper.DtoMapper;
import com.scalefocus.beer.etl.domain.noSql.NoSqlBeerDTO;
import com.scalefocus.beer.etl.domain.sql.SqlBeerDTO;
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

    public void insertToNoSql(List<NoSqlBeerDTO> beerList) {
        List<NoSqlBeerDTO> result = this.mongoRepository.insert(beerList);
        // publish to RabbitMQ
    }

//    public void insertToNoSql(NoSqlBeerDTO beerDTO) {
//        NoSqlBeerDTO result = this.mongoRepository.insert(beerDTO);
//        // publish to RabbitMQ
//    }

    public void insertToSql(List<SqlBeerDTO> beerList) {
        this.sqlRepository.saveAll(beerList);
    }



    public List<NoSqlBeerDTO> transformSqlToNoSqlList(List<SqlBeerDTO> beerList) {
        List<NoSqlBeerDTO> resultList = new ArrayList<>();

        beerList.forEach(x -> resultList.add(noSqlToSqlDtoMapper.transformSqlToNoSqlDTO(x)));

        return resultList;
    }
}
