package com.scalefocus.beer.etl.domain.sql;

import com.scalefocus.beer.etl.domain.AbstractDomainObject;
import com.sun.org.apache.xpath.internal.operations.String;

import java.util.Calendar;

public class SqlBeerDTO extends AbstractDomainObject {
    private String datasetid;
    private String recordid;
    private FieldsDTO fields;
    private GeometryDTO geometry;
    private Calendar record_timestamp;

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

    public GeometryDTO getGeometry() {
        return geometry;
    }

    public void setGeometry(GeometryDTO geometry) {
        this.geometry = geometry;
    }

    public Calendar getRecord_timestamp() {
        return record_timestamp;
    }

    public void setRecord_timestamp(Calendar record_timestamp) {
        this.record_timestamp = record_timestamp;
    }
}