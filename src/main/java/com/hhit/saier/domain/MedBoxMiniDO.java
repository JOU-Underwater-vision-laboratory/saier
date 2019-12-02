package com.hhit.saier.domain;

import java.io.Serializable;

/**
 * @author ray
 */
public class MedBoxMiniDO implements Serializable {
    /** 小药盒ID*/
    private Integer mid;
    /** 格1*/
    private String first;
    /** 格2*/
    private String second;
    /** 格3*/
    private String third;
    /** 格4*/
    private String forth;
    /** 格5*/
    private String fifth;

    public MedBoxMiniDO() {
    }

    public MedBoxMiniDO(Integer mid, String first, String second, String third, String forth, String fifth) {
        this.mid = mid;
        this.first = first;
        this.second = second;
        this.third = third;
        this.forth = forth;
        this.fifth = fifth;
    }

    public Integer getMid() {
        return mid;
    }

    public void setMid(Integer mid) {
        this.mid = mid;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getSecond() {
        return second;
    }

    public void setSecond(String second) {
        this.second = second;
    }

    public String getThird() {
        return third;
    }

    public void setThird(String third) {
        this.third = third;
    }

    public String getForth() {
        return forth;
    }

    public void setForth(String forth) {
        this.forth = forth;
    }

    public String getFifth() {
        return fifth;
    }

    public void setFifth(String fifth) {
        this.fifth = fifth;
    }

    @Override
    public String toString() {
        return "MedBoxMiniDO{" +
                "mid=" + mid +
                ", first='" + first + '\'' +
                ", second='" + second + '\'' +
                ", third='" + third + '\'' +
                ", forth='" + forth + '\'' +
                ", fifth='" + fifth + '\'' +
                '}';
    }
}
