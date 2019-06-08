open module spring.petclinic {
    exports com.scalefocus.beer.etl.rest.controller;

    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;

    requires org.mongodb.driver.core;

    requires slf4j.api;

    requires java.persistence;

    requires spring.beans;
    requires spring.boot;
    requires spring.boot.autoconfigure;
    requires spring.cloud.stream;
    requires spring.context;
    requires spring.data.commons;
    requires spring.data.jpa;
    requires spring.data.mongodb;
    requires spring.messaging;
    requires spring.tx;
    requires spring.web;
    requires spring.webmvc;
//    requires springfox.core;
//    requires springfox.spi;
    requires springfox.swagger2;
    requires springfox.spring.web;

    requires jdk.unsupported;
}