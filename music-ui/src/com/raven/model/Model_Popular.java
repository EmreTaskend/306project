package com.raven.model;

import javax.swing.Icon;

public class Model_Popular {

    public Icon getImage() {
        return image;
    }

    public void setImage(Icon image) {
        this.image = image;
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

    public Model_Popular(Icon image, String title, String description) {
        this.image = image;
        this.title = title;
        this.description = description;
    }

    public Model_Popular(Icon image, String title, String description, double avg_rating) {
        this.image = image;
        this.title = title;
        this.description = description;
        this.avg_rating = avg_rating;
    }

    private Icon image;
    private String title;
    private String description;
    public double avg_rating = 0;
}
