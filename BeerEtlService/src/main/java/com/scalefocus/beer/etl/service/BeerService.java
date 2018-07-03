package com.scalefocus.beer.etl.service;

import com.google.common.collect.Lists;
import com.scalefocus.beer.etl.domain.mapper.DtoMapper;
import com.scalefocus.beer.etl.domain.noSql.NoSqlBeerDTO;
import com.scalefocus.beer.etl.domain.sql.SqlBeerDTO;
import com.scalefocus.beer.etl.exception.DuplicateEntityException;
import com.scalefocus.beer.etl.exception.StoreToSqlException;
import com.scalefocus.beer.etl.messaging.publisher.RabbitMqPublisher;
import com.scalefocus.beer.etl.repository.BeerMongoRepository;
import com.scalefocus.beer.etl.repository.BeerSqlRepository;
import com.scalefocus.beer.etl.rest.controller.BeerController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BeerService {
    private final static Logger LOGGER = LoggerFactory.getLogger(BeerService.class);

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
        LOGGER.debug("Entity with id {} inserted to NoSQL", result.getId());
    }

    /**
     * Call persistence API in order to store the passed list in SQL database
     * @param beerList - list of SqlBeerDTO objects
     */
    public List<SqlBeerDTO> insertToSql(List<SqlBeerDTO> beerList){
        List<SqlBeerDTO> insertedList = new ArrayList<>();

        beerList.forEach(x-> {
            try {
                if (!this.sqlRepository.existsById(x.getId())) {
                    SqlBeerDTO inserted = this.sqlRepository.save(x);
                    LOGGER.debug("Entity with id {} inserted to SQL", inserted.getId());
                    insertedList.add(inserted);

                    emitBeerDto(inserted);
                    //We sleep the thread in order to increase the interval and show in the RabbitMq console, that a lot of messages goes through.
                    Thread.sleep(750);
                }
            } catch (Exception e) {
                handleExceptionOnInsert(e);
            }
        });

        return insertedList;
    }

    public void emitBeerDto(SqlBeerDTO beerDTO) {
        rabbitMqPublisher.publishToRabbitMq(beerDTO);
    }

    /**
     * Call RabbitMqPublish in order to send the list to RabbitMq
     * @param beerList - list of SqlBeerDTO object
     */
    public void emitBeerList(List<SqlBeerDTO> beerList) {
        rabbitMqPublisher.publishToRabbitMq(beerList);
    }

    /**
     * Returns the count from the DB with with operates the SqlRepository
     * @return - the number of entities in particular DB
     */
    public long getSqlCount(){
        return this.sqlRepository.count();
    }

    /**
     * Returns the count from the DB with with operates the MongoRepository
     * @return - the number of entities in particular DB
     */
    public long getNoSqlCount(){
        return this.mongoRepository.count();
    }

    /**
     * Handle exception on operating with DB through SpringData
     * @param e
     */
    private void handleExceptionOnInsert(Exception e){
        if (e instanceof DataIntegrityViolationException && e.getCause().getCause().getMessage().contains("duplicate key value")) {
            LOGGER.error("This entity already exists in the DB", e);
            throw new DuplicateEntityException(e);
        } else {
            LOGGER.error("Exception on storing entities in the DB", e);
            throw new StoreToSqlException(e);
        }
    }
}
