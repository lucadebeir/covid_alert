package com.polytechmtp.covidalert.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity(name="locations")
@Access(AccessType.FIELD)
public class Location {
    @Id
    private String location_id;
    private long latitude;
    private long longitude;
    private Date location_date;

    @ManyToMany(mappedBy = "locations")
    @JsonIgnore
    private List<User> users ;

    public void setLocation_id(String location_id) {
        this.location_id = location_id;
    }

    public String getLocation_id() {
        return location_id;
    }

    public long getLatitude() {
        return latitude;
    }

    public void setLatitude(long latitude) {
        this.latitude = latitude;
    }

    public long getLongitude() {
        return longitude;
    }

    public void setLongitude(long longitude) {
        this.longitude = longitude;
    }

    public Date getLocation_date() {
        return location_date;
    }

    public void setLocation_date(Date location_date) {
        this.location_date = location_date;
    }
}