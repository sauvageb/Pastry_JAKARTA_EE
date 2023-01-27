package com.training.pastries.dao.entity;

import com.training.pastries.dto.PastryDTO;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "pastries")
public class Pastry implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @Column(length = 10000)
    private String description;

    @Column(length = 10000)
    private String recipeSummary;

    @OneToMany(cascade = CascadeType.PERSIST)
    private List<Image> images;

    @OneToOne(cascade = CascadeType.PERSIST)
    private Origin origin;

    public Pastry() {
    }

    public static Pastry from(PastryDTO dto) {
        Pastry p = new Pastry();
        p.setId(dto.getId());
        p.setName(dto.getName());
        p.setDescription(dto.getDescription());
        p.setRecipeSummary(dto.getRecipeSummary());
        p.setOrigin(Origin.from(dto.getOrigin()));
        p.setImages(dto.getImages().stream().map(i -> Image.from(i)).collect(Collectors.toList()));

        return p;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRecipeSummary() {
        return recipeSummary;
    }

    public void setRecipeSummary(String recipeSummary) {
        this.recipeSummary = recipeSummary;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public Origin getOrigin() {
        return origin;
    }

    public void setOrigin(Origin origin) {
        this.origin = origin;
    }
}
