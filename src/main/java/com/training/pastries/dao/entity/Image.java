package com.training.pastries.dao.entity;

import com.training.pastries.dto.ImageDto;
import jakarta.persistence.*;

@Entity
@Table(name = "images")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    public Image() {
    }

    public static Image from(ImageDto dto) {
        Image i = new Image();
        i.setId(dto.getId());
        i.setName(dto.getName());
        return i;
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
}
