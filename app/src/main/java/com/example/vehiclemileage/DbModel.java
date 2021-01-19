package com.example.vehiclemileage;

public class DbModel {

    /*lastReserve number,currentReserve number,price number,fuel number,date text,mileageKm float,mileageInr float,mileageInrLtr float*/

    String lastReserve,currentReserve,price,fuel,date,mileageKm,mileageInr,mileageInrLtr;

    public DbModel(String lastReserve, String currentReserve, String price, String fuel, String date, String mileageKm, String mileageInr, String mileageInrLtr) {
        this.lastReserve = lastReserve;
        this.currentReserve = currentReserve;
        this.price = price;
        this.fuel = fuel;
        this.date = date;
        this.mileageKm = mileageKm;
        this.mileageInr = mileageInr;
        this.mileageInrLtr = mileageInrLtr;
    }

    public DbModel() {
    }

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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getFuel() {
        return fuel;
    }

    public void setFuel(String fuel) {
        this.fuel = fuel;
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
