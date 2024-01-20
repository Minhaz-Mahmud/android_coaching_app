package com.example.betaapp.Show;

public class item {

    private String imageUrl,name,education;

    public item(String imageUrl, String name, String education) {
        this.imageUrl = imageUrl;
        this.name = name;
        this.education = education;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }
}
