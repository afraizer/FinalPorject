package com.example.finalproject;

public class Listing {
    private String address;
    private String rent;
    private int bedrooms;
    private String bathrooms;
    private String landlordPhone;
    private String landlordEmail;
    private String lengthOfLease;

    public Listing(String address, String rent, int bedrooms, String bathrooms, String landlordPhone, String landlordEmail, String lengthOfLease) {
        this.address = address;
        this.rent = rent;
        this.bedrooms = bedrooms;
        this.bathrooms = bathrooms;
        this.landlordPhone = landlordPhone;
        this.landlordEmail = landlordEmail;
        this.lengthOfLease = lengthOfLease;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRent() {
        return rent;
    }

    public void setRent(String rent) {
        this.rent = rent;
    }

    public int getBedrooms() {
        return bedrooms;
    }

    public void setBedrooms(int bedrooms) {
        this.bedrooms = bedrooms;
    }

    public String getBathrooms() {
        return bathrooms;
    }

    public void setBathrooms(String bathrooms) {
        this.bathrooms = bathrooms;
    }

    public String getLandlordPhone() {
        return landlordPhone;
    }

    public void setLandlordPhone(String landlordPhone) {
        this.landlordPhone = landlordPhone;
    }

    public String getLandlordEmail() {
        return landlordEmail;
    }

    public void setLandlordEmail(String landlordEmail) {
        this.landlordEmail = landlordEmail;
    }

    public String getLengthOfLease() {
        return lengthOfLease;
    }

    public void setLengthOfLease(String lengthOfLease) {
        this.lengthOfLease = lengthOfLease;
    }
}
