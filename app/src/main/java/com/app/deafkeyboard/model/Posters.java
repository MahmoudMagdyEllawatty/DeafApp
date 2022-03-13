package com.app.deafkeyboard.model;

public class Posters {
    private String key;
    private String title;
    private String description;
    private String image;

    public Posters() {
    }

    public Posters(String key, String title, String description, String image) {
        this.key = key;
        this.title = title;
        this.description = description;
        this.image = image;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
