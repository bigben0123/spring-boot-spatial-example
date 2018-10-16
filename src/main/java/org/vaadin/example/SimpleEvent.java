package org.vaadin.example;

import java.util.Date;

import javax.persistence.Entity;

import com.vividsolutions.jts.geom.LineString;
import com.vividsolutions.jts.geom.Point;
import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class SimpleEvent extends AbstractEntity {

    private String title;

    public SimpleEvent() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


}
