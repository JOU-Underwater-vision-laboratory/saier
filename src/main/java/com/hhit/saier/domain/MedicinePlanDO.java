package com.hhit.saier.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * @author ray
 */
public class MedicinePlanDO implements Serializable {
    private Integer id;
    /** 药准字号*/
    private String medlicense;
    /** 药盒mid  */
    private Integer medbox;
    /** 服药次数*/
    private String times;
    /** 服药时间*/
    private String medicinetime;
    /** 服药剂量*/
    private String num;
    /** 创建时间*/
    private Date ctime;
    /** 创建时期*/
    private String term;
    /** 创建人ID*/
    private Integer created;
    /** 创建人姓名*/
    private String name;


    public MedicinePlanDO() {
    }

    public MedicinePlanDO(String medlicense, Integer medbox, String times, String medicinetime, String num) {
        this.medlicense = medlicense;
        this.medbox = medbox;
        this.times = times;
        this.medicinetime = medicinetime;
        this.num = num;
    }

    public MedicinePlanDO(Integer id, String medlicense, Integer medbox, String times, String medicinetime, String num) {
        this.id = id;
        this.medlicense = medlicense;
        this.medbox = medbox;
        this.times = times;
        this.medicinetime = medicinetime;
        this.num = num;
    }

    public MedicinePlanDO(Integer id, String medlicense, Integer medbox, String times, String medicinetime, String num, Date ctime, String term, Integer created, String name) {
        this.id = id;
        this.medlicense = medlicense;
        this.medbox = medbox;
        this.times = times;
        this.medicinetime = medicinetime;
        this.num = num;
        this.ctime = ctime;
        this.term = term;
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

    public String getTimes() {
        return times;
    }

    public void setTimes(String times) {
        this.times = times;
    }

    public String getMedicinetime() {
        return medicinetime;
    }

    public void setMedicinetime(String medicinetime) {
        this.medicinetime = medicinetime;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getTerm() {
        return term;
    }

    @Override
    public String toString() {
        return "MedicinePlanDO{" +
                "id=" + id +
                ", medlicense='" + medlicense + '\'' +
                ", medbox=" + medbox +
                ", times='" + times + '\'' +
                ", medicinetime='" + medicinetime + '\'' +
                ", num='" + num + '\'' +
                ", ctime=" + ctime +
                ", term='" + term + '\'' +
                ", created=" + created +
                ", name='" + name + '\'' +
                '}';
    }
}
