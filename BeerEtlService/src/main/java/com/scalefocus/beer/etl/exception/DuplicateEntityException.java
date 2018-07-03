package com.scalefocus.beer.etl.exception;

public class DuplicateEntityException extends RuntimeException {
    public DuplicateEntityException(Exception e){
        super(e);
    }
}

