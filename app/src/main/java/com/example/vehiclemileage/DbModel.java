package com.example.vehiclemileage;

public class DbModel {

    /*lastReserve number,currentReserve number,price number,fuel number,date text,mileageKm float,mileageInr float,mileageInrLtr float*/

    String id;
    String lastReserve;
    String currentReserve;
    String number;
    String fuel;
    String price;
    String date;
    String mileageKm;
    String mileageInr;
    String mileageInrLtr;

    public DbModel() {
    }

    public DbModel(String id,String lastReserve, String currentReserve, String number, String fuel, String price, String date, String mileageKm, String mileageInr, String mileageInrLtr) {
        this.id=id;
        this.lastReserve = lastReserve;
        this.currentReserve = currentReserve;
        this.number = number;
        this.fuel = fuel;
        this.price = price;
        this.date = date;
        this.mileageKm = mileageKm;
        this.mileageInr = mileageInr;
        this.mileageInrLtr = mileageInrLtr;
    }

    public String getId(){ return id; }

    public void setId(String id){this.id=id;}

    public String getLastReserve() {
        return lastReserve;
    }

    public void setLastReserve(String lastReserve) {
        this.lastReserve = lastReserve;
    }

    public String getCurrentReserve() {
        return currentReserve;
    }

    public void setCurrentReserve(String currentReserve) {
        this.currentReserve = currentReserve;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getFuel() {
        return fuel;
    }

    public void setFuel(String fuel) {
        this.fuel = fuel;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMileageKm() {
        return mileageKm;
    }

    public void setMileageKm(String mileageKm) {
        this.mileageKm = mileageKm;
    }

    public String getMileageInr() {
        return mileageInr;
    }

    public void setMileageInr(String mileageInr) {
        this.mileageInr = mileageInr;
    }

    public String getMileageInrLtr() {
        return mileageInrLtr;
    }

    public void setMileageInrLtr(String mileageInrLtr) {
        this.mileageInrLtr = mileageInrLtr;
    }
}
