package com.example.task71p;

public class DATA {

    // Data fields
    private int id;
    private String isLostOrFound;
    private String name;
    private String phone;
    private String description;
    private String date;
    private String location;

    // Getter and setter methods for each data field

    public String getIsLostOrFound() {
        return isLostOrFound;
    }

    public void setIsLostOrFound(String isLostOrFound) {
        this.isLostOrFound = isLostOrFound;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Constructor for creating a DATA object

    public DATA(String isLostOrFound, String name, String phone, String description, String date, String location, int id) {
        this.isLostOrFound = isLostOrFound;
        this.name = name;
        this.phone = phone;
        this.description = description;
        this.date = date;
        this.location = location;
        this.id = id;
    }
}
