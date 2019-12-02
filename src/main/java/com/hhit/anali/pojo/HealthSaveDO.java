package com.hhit.anali.pojo;


import java.io.Serializable;

public class HealthSaveDO implements Serializable {


    private Bld bld;
    private Heart heart;
    private Tem tem;
    private Oxy oxy;
    private Breath breath;


    public HealthSaveDO(Bld bld, Heart heart, Tem tem, Oxy oxy, Breath breath) {
        this.bld = bld;
        this.heart = heart;
        this.tem = tem;
        this.oxy = oxy;
        this.breath = breath;
    }

    public HealthSaveDO(Bld bld) {
        this.bld = bld;
    }


    public HealthSaveDO(Heart heart, Tem tem, Oxy oxy, Breath breath) {
        this.heart = heart;
        this.tem = tem;
        this.oxy = oxy;
        this.breath = breath;
    }

    public Bld getBld() {
        return bld;
    }

    public void setBld(Bld bld) {
        this.bld = bld;
    }

    public Heart getHeart() {
        return heart;
    }

    public void setHeart(Heart heart) {
        this.heart = heart;
    }

    public Tem getTem() {
        return tem;
    }

    public void setTem(Tem tem) {
        this.tem = tem;
    }

    public Oxy getOxy() {
        return oxy;
    }

    public void setOxy(Oxy oxy) {
        this.oxy = oxy;
    }

    public Breath getBreath() {
        return breath;
    }

    public void setBreath(Breath breath) {
        this.breath = breath;
    }

    @Override
    public String toString() {
        return "HealthSaveDO{" +
                "bld=" + bld +
                ", heart=" + heart +
                ", tem=" + tem +
                ", oxy=" + oxy +
                ", breath=" + breath +
                '}';
    }
}
