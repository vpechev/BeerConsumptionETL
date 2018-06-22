package com.scalefocus.beer.etl.domain.mapper;

import com.scalefocus.beer.etl.domain.noSql.NoSqlBeerDTO;
import com.scalefocus.beer.etl.domain.sql.SqlBeerDTO;
import org.springframework.stereotype.Component;

@Component
public class DtoMapper {
    public NoSqlBeerDTO transformSqlToNoSqlDTO(SqlBeerDTO noSqlBeerDTO) {
        NoSqlBeerDTO result = new NoSqlBeerDTO();
        result.setId(noSqlBeerDTO.getId());
        result.setName(noSqlBeerDTO.getFields().getName());
        result.setCategoryName(noSqlBeerDTO.getFields().getCat_name());
        result.setStyleName(noSqlBeerDTO.getFields().getStyle_name());
        result.setBreweriesName(noSqlBeerDTO.getFields().getName_breweries());
        result.setCountry(noSqlBeerDTO.getFields().getCountry());
        result.setStateName(noSqlBeerDTO.getFields().getState());
        result.setCity(noSqlBeerDTO.getFields().getCity());
        result.setStreet(noSqlBeerDTO.getFields().getAddress1());


        return result;
    }
}
