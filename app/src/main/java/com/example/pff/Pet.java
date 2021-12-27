package com.example.pff;

public class Pet {

    private String name, color, Category, imageUrl, sex, ownerPhone, age;

    public Pet(String name, String color, String category, String imageUrl, String sex, String ownerPhone, String age) {
        this.name = name;
        this.color = color;
        Category = category;
        this.imageUrl = imageUrl;
        this.sex = sex;
        this.ownerPhone = ownerPhone;
        this.age = age;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getOwnerPhone() {
        return ownerPhone;
    }

    public void setOwnerPhone(String ownerPhone) {
        this.ownerPhone = ownerPhone;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}