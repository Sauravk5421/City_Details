package com.t.citydetails;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "REPO")
public class RepoActivity {
    private String city;
    private String state;
    private String lat;
    private String lon;


    @PrimaryKey(autoGenerate = true)
    private int id;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public RepoActivity(String city,String state,String lat,String lon) {
        this.city = city;
        this.state = state;
        this.lat = lat;
        this.lon = lon;

    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }
}
