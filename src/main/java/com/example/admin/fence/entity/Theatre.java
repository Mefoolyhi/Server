package com.example.admin.fence.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "theatre")
public class Theatre {

    @Column(name = "title", nullable = false, columnDefinition = "TEXT")
    String title;

    @Column(name = "address", nullable = false, columnDefinition = "TEXT")
    String address;
    @Column(name = "image", nullable = false, columnDefinition = "TEXT")
    String image;
    @Column(name = "link", nullable = false, columnDefinition = "TEXT")
    String link;
    @Column(name = "number", nullable = false, columnDefinition = "TEXT")
    String number;


    @Id
    String id;


    @Column(name = "latitude", nullable = false, columnDefinition = "REAL")
    double latitude;
    @Column(name = "longitude", nullable = false, columnDefinition = "REAL")
    double longintude;

    public Theatre(String id, String title, String address, String image, String link, String number, double latitude, double longintude){
        this.latitude = latitude;
        this.title = title;
        this.longintude = longintude;
        this.link = link;
        this.number = number;
        this.address = address;
        this.id = id;
        this.image = image;
    }

public  Theatre(){}




}
