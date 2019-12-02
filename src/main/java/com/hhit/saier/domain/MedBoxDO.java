package com.hhit.saier.domain;

import java.io.Serializable;

public class MedBoxDO implements Serializable {

    private Integer id;
    private Integer mid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMid() {
        return mid;
    }

    public void setMid(Integer mid) {
        this.mid = mid;
    }

    @Override
    public String toString() {
        return "MedBoxDO{" +
                "id=" + id +
                ", mid=" + mid +
                '}';
    }
}
