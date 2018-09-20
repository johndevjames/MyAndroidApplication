package com.johndevjames.myandroidapplication;

public class Tutorial {
    private String Added_by;
    private String Land_mark;
    private String Tin;
    private String address;
    private String district;
    private String email;
    private Integer id;
    private String mobile1;
    private String mobile2;
    private String ownerName;
    private Integer pin;
    private String place;
    private String shopArea;
    private String shopname;

    public Tutorial(Integer id, String shopname, String ownerName, String place, String address, String mobile1, String mobile2, String shopArea, String district, String email, Integer pin, String Tin, String Land_mark, String Added_by) {
        this.id = id;
        this.shopname = shopname;
        this.ownerName = ownerName;
        this.place = place;
        this.address = address;
        this.mobile1 = mobile1;
        this.mobile2 = mobile2;
        this.shopArea = shopArea;
        this.district = district;
        this.email = email;
        this.pin = pin;
        this.Tin = Tin;
        this.Land_mark = Land_mark;
        this.Added_by = Added_by;
    }

    public Integer getid() {
        return this.id;
    }

    public void setid(Integer title) {
        this.id = title;
    }

    public String getShopname() {
        return this.shopname;
    }

    public void setShopname(String title) {
        this.shopname = title;
    }

    public String getOwnerName() {
        return this.ownerName;
    }

    public void setOwnerName(String quality) {
        this.ownerName = quality;
    }

    public String getPlace() {
        return this.place;
    }

    public void setPlace(String views) {
        this.place = views;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String title2) {
        this.address = title2;
    }

    public String getMobile1() {
        return this.mobile1;
    }

    public void setMobile1(String quality2) {
        this.mobile1 = quality2;
    }

    public String getMobile2() {
        return this.mobile2;
    }

    public void setMobile2(String views2) {
        this.mobile2 = views2;
    }

    public String getShopArea() {
        return this.shopArea;
    }

    public void setShopArea(String title3) {
        this.shopArea = title3;
    }

    public String getDistrict() {
        return this.district;
    }

    public void setDistrict(String title4) {
        this.district = title4;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String quality3) {
        this.email = quality3;
    }

    public Integer getPin() {
        return this.pin;
    }

    public void setPin(Integer views3) {
        this.pin = views3;
    }

    public String getTin() {
        return this.Tin;
    }

    public void setTin(String views4) {
        this.Tin = views4;
    }

    public String getLand_mark() {
        return this.Land_mark;
    }

    public void setLand_mark(String quality4) {
        this.Land_mark = quality4;
    }

    public String getAdded_by() {
        return this.Added_by;
    }

    public void setAdded_by(String quality5) {
        this.Added_by = quality5;
    }
}
