package com.scalefocus.beer.etl.domain.noSql;

import org.springframework.data.annotation.Id;

public class NoSqlBeerDTO {
    @Id
    private int id;
    private String name;
    private String categoryName;
    private String styleName;
    private String breweriesName;
    private String country;
    private String stateName;
    private String city;
    private String street;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getStyleName() {
        return styleName;
    }

    public void setStyleName(String styleName) {
        this.styleName = styleName;
    }

    public String getBreweriesName() {
        return breweriesName;
    }

    public void setBreweriesName(String breweriesName) {
        this.breweriesName = breweriesName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }
}
