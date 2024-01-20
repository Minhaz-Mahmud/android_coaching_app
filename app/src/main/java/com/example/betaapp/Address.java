package com.example.betaapp;

public class Address {

    String thana,roadNo,upzilla,houseNo,mobile;

    public Address() {
    }

    public Address(String thana,String roadNo,String upzilla,String houseNo,String mobile) {
        this.thana = thana;
        this.roadNo = roadNo;
        this.upzilla = upzilla;
        this.houseNo = houseNo;
        this.mobile = mobile;
    }

    public void setThana(String thana) {
        this.thana = thana;
    }

    public void setRoadNo(String roadNo) {
        this.roadNo = roadNo;
    }

    public void setUpzilla(String upzilla) {
        this.upzilla = upzilla;
    }

    public void setHouseNo(String houseNo) {
        this.houseNo = houseNo;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getThana() {
        return thana;
    }

    public String getRoadNo() {
        return roadNo;
    }

    public String getUpzilla() {
        return upzilla;
    }

    public String getHouseNo() {
        return houseNo;
    }

    public String getMobile() {
        return mobile;
    }
}
