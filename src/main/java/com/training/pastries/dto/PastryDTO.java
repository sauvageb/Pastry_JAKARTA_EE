package com.training.pastries.dto;

import java.util.List;

public class PastryDTO {

    private Long id;
    private String name;
    private String description;

    private String recipeSummary;

    private OriginDto origin;

    private List<ImageDto> images;

    public PastryDTO() {
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

    public OriginDto getOrigin() {
        return origin;
    }

    public void setOrigin(OriginDto origin) {
        this.origin = origin;
    }

    public String getRecipeSummary() {
        return recipeSummary;
    }

    public void setRecipeSummary(String recipeSummary) {
        this.recipeSummary = recipeSummary;
    }

    public List<ImageDto> getImages() {
        return images;
    }

    public void setImages(List<ImageDto> images) {
        this.images = images;
    }
}
