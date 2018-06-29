package com.scalefocus.beer.etl.repository;


import com.scalefocus.beer.etl.domain.noSql.NoSqlBeerDTO;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

public interface BeerMongoRepository extends MongoRepository<NoSqlBeerDTO, String> {
}
