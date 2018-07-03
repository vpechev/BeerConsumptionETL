package com.scalefocus.beer.etl.repository;

import com.scalefocus.beer.etl.domain.sql.SqlBeerDTO;
import org.springframework.data.repository.CrudRepository;

public interface BeerSqlRepository extends CrudRepository<SqlBeerDTO, Integer> {
}
