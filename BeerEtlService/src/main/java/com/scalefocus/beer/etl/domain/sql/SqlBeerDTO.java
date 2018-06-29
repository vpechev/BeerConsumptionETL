package com.scalefocus.beer.etl.domain.sql;

import javax.persistence.*;
import java.util.Calendar;

@Entity
@Table(name="Beer")
public class SqlBeerDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String datasetid;
    private String recordid;
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch=FetchType.EAGER)
    @JoinColumn(name="id")
    private FieldsDTO fields;
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