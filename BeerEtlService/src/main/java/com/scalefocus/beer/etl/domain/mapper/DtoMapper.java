package com.scalefocus.beer.etl.domain.mapper;

import com.scalefocus.beer.etl.domain.noSql.NoSqlBeerDTO;
import com.scalefocus.beer.etl.domain.sql.SqlBeerDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface DtoMapper {
    /**
     * Maps the parameter object to the type of the result object
     * @param noSqlBeerDTO
     * @return
     */
    @Mapping(source = "fields.name", target="name")
    @Mapping(source = "fields.cat_name", target="categoryName")
    @Mapping(source = "fields.style_name", target="styleName")
    @Mapping(source = "fields.name_breweries", target="breweriesName")
    @Mapping(source = "fields.country", target="country")
    @Mapping(source = "fields.state", target="stateName")
    @Mapping(source = "fields.city", target="city")
    @Mapping(source = "fields.address1", target="street")
    NoSqlBeerDTO mapSqlToNoSqlDTO(SqlBeerDTO noSqlBeerDTO);
}
