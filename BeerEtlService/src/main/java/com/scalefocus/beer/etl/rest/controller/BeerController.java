package com.scalefocus.beer.etl.rest.controller;


import com.scalefocus.beer.etl.domain.sql.SqlBeerDTO;
import com.scalefocus.beer.etl.service.BeerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/beers")
public class BeerController {
    private final static Logger LOGGER = LoggerFactory.getLogger(BeerController.class);

    @Autowired
    private BeerService service;

//    @PostMapping
//    public void insertInNoSQLDb(NoSqlBeerDTO beerDTO){
//        this.service.insertToNoSql(beerDTO);
//    }

    @PostMapping("/_bulk")
    public void insertInNoSQLDb(List<SqlBeerDTO> beerList){
        this.service.insertToSql(beerList);
    }
}
