package com.hhit.saier.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class MedicinePlanVO {
    private Integer id;
    /** 药准字号*/
    private String medlicense;

    /** 药盒mid  */
    private Integer medbox;
    /** 服药次数*/
    private String times;
    /** 服药时间*/
    private HashMap<String,MedTime> medTimeList;
    /** 服药剂量*/
    private String num;
    /** 创建时间*/
//    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date ctime;

    private MedicineDO medicineDO;

    /** 创建人ID*/
    private Integer created;
    /** 创建人姓名*/
    private String name;

    public MedicinePlanVO() {
    }

    public MedicinePlanVO(Integer id, String medlicense, Integer medbox, String times, HashMap<String, MedTime> medTimeList, String num) {
        this.id = id;
        this.medlicense = medlicense;
        this.medbox = medbox;
        this.times = times;
        this.medTimeList = medTimeList;
        this.num = num;
    }

    public MedicinePlanVO(Integer id, String medlicense, Integer medbox, String times, HashMap<String, MedTime> medTimeList, String num, MedicineDO medicineDO, Date ctime) {
        this.id = id;
        this.medlicense = medlicense;
        this.medbox = medbox;
        this.times = times;
        this.medTimeList = medTimeList;
        this.num = num;
        this.medicineDO = medicineDO;
    }

    public MedicinePlanVO(Integer id, String medlicense, Integer medbox, String times, HashMap<String, MedTime> medTimeList, String num,  MedicineDO medicineDO,Date ctime, Integer created, String name) {
        this.id = id;
        this.medlicense = medlicense;
        this.medbox = medbox;
        this.times = times;
        this.medTimeList = medTimeList;
        this.num = num;
        this.ctime = ctime;
        this.medicineDO = medicineDO;
        this.created = created;
        this.name = name;
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

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    public MedicineDO getMedicineDO() {
        return medicineDO;
    }

    public void setMedicineDO(MedicineDO medicineDO) {
        this.medicineDO = medicineDO;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMedlicense() {
        return medlicense;
    }

    public void setMedlicense(String medlicense) {
        this.medlicense = medlicense;
    }

    public Integer getMedbox() {
        return medbox;
    }

    public void setMedbox(Integer medbox) {
        this.medbox = medbox;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getTimes() {
        return times;
    }

    public void setTimes(String times) {
        this.times = times;
    }

    public HashMap<String, MedTime> getMedTimeList() {
        return medTimeList;
    }

    public void setMedTimeList(HashMap<String, MedTime> medTimeList) {
        this.medTimeList = medTimeList;
    }

    @Override
    public String toString() {
        return "MedicinePlanVO{" +
                "id=" + id +
                ", medlicense='" + medlicense + '\'' +
                ", medbox=" + medbox +
                ", times='" + times + '\'' +
                ", medTimeList=" + medTimeList +
                ", num='" + num + '\'' +
                ", ctime=" + ctime +
                ", medicineDO=" + medicineDO +
                ", created=" + created +
                ", name='" + name + '\'' +
                '}';
    }
}

