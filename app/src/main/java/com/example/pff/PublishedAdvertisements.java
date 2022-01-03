package com.example.pff;

public class PublishedAdvertisements {
    String petName, petCategory, imgUrl, documentId;

    public PublishedAdvertisements(String petName, String petCategory, String imgUrl, String documentId) {
        this.petName = petName;
        this.petCategory = petCategory;
        this.imgUrl = imgUrl;
        this.documentId = documentId;
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

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getDocumentId(){return documentId;}

    public void setDocumentId(String documentId){this.documentId = documentId;}
}
