package com.example.jismoniysalomatlik.model;

public class HistoryModel {
    String name;
    String value;
    String time;

    public HistoryModel(String name, String value, String time) {
        this.name = name;
        this.value = value;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
