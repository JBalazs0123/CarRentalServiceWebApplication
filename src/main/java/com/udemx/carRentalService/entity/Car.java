package com.udemx.carRentalService.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "car")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "daily_price")
    private int dailyPrice;

    @Column(name = "status")
    private String status;

    public Car() {
    }

    public Car(String name, String imageUrl, int dailyPrice, String status) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.dailyPrice = dailyPrice;
        this.status = status;
    }

    public Car(int id, String name, String imageUrl, int dailyPrice, String status) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
        this.dailyPrice = dailyPrice;
        this.status = status;
    }

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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getDailyPrice() {
        return dailyPrice;
    }

    public void setDailyPrice(int dailyPrice) {
        this.dailyPrice = dailyPrice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
