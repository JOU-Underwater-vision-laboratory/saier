package com.hhit.saier.domain;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MedicinePlanRecordDO {
    private Integer id;
    private String week;
    private String month;
    private String year;
    private String day;
    private Date current;
//     是否按时
    private String prop;
    private Integer created;
    private String name;


    public MedicinePlanRecordDO( Date current, String prop, Integer created, String name) {
        this.current = current;
        buildTime(current);
        this.prop = prop;
        this.created = created;
        this.name = name;
    }

    private void buildTime(Date current) {
        SimpleDateFormat simpleDateFormat  = new SimpleDateFormat("yyyy-MM-dd");
        String[] format = simpleDateFormat.format(current).split("-");
        this.year = format[0];
        this.month = format[0]+"-"+format[1];
        this.day = format[0]+"-"+format[1];
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public Date getCurrent() {
        return current;
    }

    public void setCurrent(Date current) {
        this.current = current;
    }

    public String getProp() {
        return prop;
    }

    public void setProp(String prop) {
        this.prop = prop;
    }

    public Integer getCreated() {
        return created;
    }

    public void setCreated(Integer created) {
        this.created = created;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
