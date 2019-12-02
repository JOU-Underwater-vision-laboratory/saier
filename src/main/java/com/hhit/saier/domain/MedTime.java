package com.hhit.saier.domain;


public class MedTime{
    private String hour;
    private String minute;

    public MedTime() {
    }

    public MedTime(String hour, String minute) {
        this.hour = hour;
        this.minute = minute;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getMinute() {
        return minute;
    }

    public void setMinute(String minute) {
        this.minute = minute;
    }
}
