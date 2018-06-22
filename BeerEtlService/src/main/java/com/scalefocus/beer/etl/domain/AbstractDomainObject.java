package com.scalefocus.beer.etl.domain;

import org.springframework.data.annotation.Id;

public abstract class AbstractDomainObject {
    @Id
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
