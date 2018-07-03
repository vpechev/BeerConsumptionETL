package com.scalefocus.beer.etl.rest.controller;


import com.scalefocus.beer.etl.domain.sql.SqlBeerDTO;
import com.scalefocus.beer.etl.service.BeerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/beers")
public class BeerController {
    private final static Logger LOGGER = LoggerFactory.getLogger(BeerController.class);

    @Autowired
    private BeerService service;

    /**
     * Call the service layer in order to process the passed list
     * @param beerList
     */
    @PostMapping("/_bulk")
    public void insertInSql(@RequestBody List<SqlBeerDTO> beerList){
        LOGGER.debug("New list with {} entities received for processing.", beerList.size());
        this.service.insertToSql(beerList);
    }

//    @GetMapping("/sql/count")
//    public long getSqlCount(){
//        LOGGER.debug("New request for getting SQL count accepted.");
//        return this.service.getSqlCount();
//    }
//
//    @GetMapping("/nosql/count")
//    public long getNoSqlCount(){
//        LOGGER.debug("New request for getting NoSQL count accepted.");
//        return this.service.getNoSqlCount();
//    }
}
