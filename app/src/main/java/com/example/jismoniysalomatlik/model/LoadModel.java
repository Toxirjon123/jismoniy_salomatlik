package com.example.jismoniysalomatlik.model;

public class LoadModel {
    int dav_sis;
    int dav_di;
    double pols;
    double rost;
    double massa;
    int vozrast;
    int phone;
   String data;
    double uroven;

    public LoadModel(int dav_sis, int dav_di, double pols, double rost, double massa, int vozrast, int phone, String data, double uroven) {
        this.dav_sis = dav_sis;
        this.dav_di = dav_di;
        this.pols = pols;
        this.rost = rost;
        this.massa = massa;
        this.vozrast = vozrast;
        this.phone = phone;
        this.data = data;
        this.uroven = uroven;
    }

    public int getDav_sis() {
        return dav_sis;
    }

    public void setDav_sis(int dav_sis) {
        this.dav_sis = dav_sis;
    }

    public int getDav_di() {
        return dav_di;
    }

    public void setDav_di(int dav_di) {
        this.dav_di = dav_di;
    }

    public double getPols() {
        return pols;
    }

    public void setPols(double pols) {
        this.pols = pols;
    }

    public double getRost() {
        return rost;
    }

    public void setRost(double rost) {
        this.rost = rost;
    }

    public double getMassa() {
        return massa;
    }

    public void setMassa(double massa) {
        this.massa = massa;
    }

    public int getVozrast() {
        return vozrast;
    }

    public void setVozrast(int vozrast) {
        this.vozrast = vozrast;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public double getUroven() {
        return uroven;
    }

    public void setUroven(double uroven) {
        this.uroven = uroven;
    }
}
