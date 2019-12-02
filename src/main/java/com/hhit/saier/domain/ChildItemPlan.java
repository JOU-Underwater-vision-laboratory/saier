package com.hhit.saier.domain;


import java.text.SimpleDateFormat;
import java.util.Date;

public class ChildItemPlan {

    private Integer id;
    /**药准字号*/
    private String group;
    /**药名*/
    private String name;
    /** 服药时间*/
    private String times;
    /** 服药次数*/
    private String num;
    /** 药物完成*/
    private String vaccineStatus;


    public ChildItemPlan build(MedicinePlanDO planDO){
        this.id = planDO.getId();
        this.group = buildGroup(planDO.getCtime());
        this.name = planDO.getMedlicense();
        this.times = planDO.getTimes();
        this.num = planDO.getNum();
        this.vaccineStatus = checkStatus(planDO.getTerm(),planDO.getCtime());
        return this;
    }

    private String buildGroup(Date ctime) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String format = simpleDateFormat.format(ctime);
        return format;
    }

    private String checkStatus(String term, Date ctime) {
        long t = new Date().getTime() - ctime.getTime();
        long weeks = t / (1000 * 60 * 60 * 24 * 7);
        long l = Long.parseLong(term);
        if (l-weeks>0){
            return "ing";
        }else {
            return "over";
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTimes() {
        return times;
    }

    public void setTimes(String times) {
        this.times = times;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getVaccineStatus() {
        return vaccineStatus;
    }

    public void setVaccineStatus(String vaccineStatus) {
        this.vaccineStatus = vaccineStatus;
    }
}