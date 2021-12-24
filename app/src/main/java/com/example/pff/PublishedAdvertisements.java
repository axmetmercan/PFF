package com.example.pff;

public class PublishedAdvertisements {
    String petName, petCategory;

    public PublishedAdvertisements(String petName, String petCategory) {
        this.petName = petName;
        this.petCategory = petCategory;
    }


    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public String getPetCategory() {
        return petCategory;
    }

    public void setPetCategory(String petCategory) {
        this.petCategory = petCategory;
    }
}
