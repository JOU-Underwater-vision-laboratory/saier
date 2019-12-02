package com.hhit.saier.domain;

/**
 * @author ray
 */
public class BoxDO {

    private Integer id;
    private Integer gridId;
    private Integer boxId;
    private String mboxid;
    private Integer rest;
    /* 创建人ID*/
    private Integer created;
    /* 创建人姓名*/
    private String name;

    public BoxDO(String mboxid) {
        this.mboxid = mboxid;
    }

    public BoxDO() {
    }

    public BoxDO(Integer gridId, Integer boxId, String mboxid, Integer rest) {
        this.gridId = gridId;
        this.boxId = boxId;
        this.mboxid = mboxid;
        this.rest = rest;
    }

    public BoxDO(Integer id, Integer gridId, Integer boxId, String mboxid, Integer rest, Integer created, String name) {
        this.id = id;
        this.gridId = gridId;
        this.boxId = boxId;
        this.mboxid = mboxid;
        this.rest = rest;
        this.created = created;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGridId() {
        return gridId;
    }

    public void setGridId(Integer gridId) {
        this.gridId = gridId;
    }

    public Integer getBoxId() {
        return boxId;
    }

    public void setBoxId(Integer boxId) {
        this.boxId = boxId;
    }

    public String getMboxid() {
        return mboxid;
    }

    public void setMboxid(String mboxid) {
        this.mboxid = mboxid;
    }

    public Integer getRest() {
        return rest;
    }

    public void setRest(Integer rest) {
        this.rest = rest;
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

    @Override
    public String toString() {
        return "BoxDO{" +
                "id=" + id +
                ", gridId=" + gridId +
                ", boxId=" + boxId +
                ", mboxid='" + mboxid + '\'' +
                ", rest=" + rest +
                ", created=" + created +
                ", name='" + name + '\'' +
                '}';
    }
}
