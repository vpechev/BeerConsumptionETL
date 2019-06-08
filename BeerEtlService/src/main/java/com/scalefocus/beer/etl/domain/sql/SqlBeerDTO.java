package com.scalefocus.beer.etl.domain.sql;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.Calendar;

@Entity
@Table(name="Beer")
@ApiModel(description = "Contains all meta and actual data for Beer entity")
public class SqlBeerDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ApiModelProperty(example = "open-beer-data-public-usage")
    private String datasetid;
    @ApiModelProperty(example = "test-record-id-value")
    private String recordid;
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch=FetchType.EAGER)
    @JoinColumn(name="id")
    private FieldsDTO fields;
    @ApiModelProperty(example = "2016-09-26T07:21:38+03:00")
    private Calendar record_timestamp;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDatasetid() {
        return datasetid;
    }

    public void setDatasetid(String datasetid) {
        this.datasetid = datasetid;
    }

    public String getRecordid() {
        return recordid;
    }

    public void setRecordid(String recordid) {
        this.recordid = recordid;
    }

    public FieldsDTO getFields() {
        return fields;
    }

    public void setFields(FieldsDTO fields) {
        this.fields = fields;
    }

    public Calendar getRecord_timestamp() {
        return record_timestamp;
    }

    public void setRecord_timestamp(Calendar record_timestamp) {
        this.record_timestamp = record_timestamp;
    }
}