package com.scalefocus.beer.etl.exception;

public class StoreToSqlException extends RuntimeException {
    public StoreToSqlException(Exception e){
        super(e);
    }
}
