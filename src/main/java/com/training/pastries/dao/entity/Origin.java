package com.training.pastries.dao.entity;

import com.training.pastries.dto.OriginDto;
import jakarta.persistence.*;

@Entity
@Table(name = "origins")
public class Origin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private double latitude;
    private double longitude;


    public static Origin from(OriginDto dto) {
        Origin origin = new Origin();
        origin.setId(dto.getId());
        origin.setName(dto.getName());
        origin.setLatitude(dto.getLatitude());
        origin.setLongitude(dto.getLongitude());
        return origin;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
