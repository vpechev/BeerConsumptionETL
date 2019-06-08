package com.scalefocus.beer.etl.domain.sql;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Calendar;

@Entity
@Table(name="Fields")
public class FieldsDTO {
    @Id
    private int id;
    @ApiModelProperty(example = "69")
    private String brewery_id;
    @ApiModelProperty(example = "Lovech")
    private String city;
    @ApiModelProperty(example = "Mr. X")
    private String name;
    @ApiModelProperty(example = "Other")
    private String cat_name;
    @ApiModelProperty(example = "69")
    private String style_id;
    @ApiModelProperty(example = "10")
    private String cat_id;
    @ApiModelProperty(example = "0")
    private String upc;
    @ApiModelProperty(example = "0")
    private String srm;
    @ApiModelProperty(example = "2019-06-08T23:00:00+03:00")
    private Calendar last_mod;
    @ApiModelProperty(example = "Lovech")
    private String state;
    @ApiModelProperty(example = "Lovech Pilsen-Hefeweizen")
    private String style_name;
    @ApiModelProperty(example = "0.0")
    private double abv;
    @ApiModelProperty(example = "Bulgaria")
    private String country;
    @ApiModelProperty(example = "Meltum")
    private String name_breweries;
    @ApiModelProperty(example = "Presyaka village")
    private String address1;
    @ApiModelProperty(example = "0")
    private String ibu;
    @ApiModelProperty(example = "Pavlio")
    private String add_user;

    public String getBrewery_id() {
        return brewery_id;
    }

    public void setBrewery_id(String brewery_id) {
        this.brewery_id = brewery_id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCat_name() {
        return cat_name;
    }

    public void setCat_name(String cat_name) {
        this.cat_name = cat_name;
    }

    public String getStyle_id() {
        return style_id;
    }

    public void setStyle_id(String style_id) {
        this.style_id = style_id;
    }

    public String getCat_id() {
        return cat_id;
    }

    public void setCat_id(String cat_id) {
        this.cat_id = cat_id;
    }

    public String getUpc() {
        return upc;
    }

    public void setUpc(String upc) {
        this.upc = upc;
    }

    public String getSrm() {
        return srm;
    }

    public void setSrm(String srm) {
        this.srm = srm;
    }

    public Calendar getLast_mod() {
        return last_mod;
    }

    public void setLast_mod(Calendar last_mod) {
        this.last_mod = last_mod;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getStyle_name() {
        return style_name;
    }

    public void setStyle_name(String style_name) {
        this.style_name = style_name;
    }

    public double getAbv() {
        return abv;
    }

    public void setAbv(double abv) {
        this.abv = abv;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getName_breweries() {
        return name_breweries;
    }

    public void setName_breweries(String name_breweries) {
        this.name_breweries = name_breweries;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIbu() {
        return ibu;
    }

    public void setIbu(String ibu) {
        this.ibu = ibu;
    }

    public String getAdd_user() {
        return add_user;
    }

    public void setAdd_user(String add_user) {
        this.add_user = add_user;
    }
}