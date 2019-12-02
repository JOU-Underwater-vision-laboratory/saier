package com.hhit.site.domain;


import java.io.Serializable;
import java.util.Date;

/**
 *
 * 
 * @Title: NewsDO
 * @description:  
 * @author liujun
 * @create 2018-10-05
 * 
 */
public class NewsDO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer nid;
    // 日期
    private Integer nyear;
    // 月份，可被修改
    private Integer nmonth;
    // 内容
    private String nContent;
    // 创建人
    private Long created;
    // 修改人
    private Long modified;
    // 创建时间
    private Date gtmCreate;
    // 修改时间
    private Date gtmModified;

    public NewsDO() {
    }

    public NewsDO(Integer nyear, Integer nmonth, String nContent, Long created, Long modified, Date gtmCreate, Date gtmModified) {
        this.nyear = nyear;
        this.nmonth = nmonth;
        this.nContent = nContent;
        this.created = created;
        this.modified = modified;
        this.gtmCreate = gtmCreate;
        this.gtmModified = gtmModified;
    }

    public NewsDO(Integer nid, Integer nyear, Integer nmonth, String nContent, Long created, Long modified, Date gtmCreate, Date gtmModified) {
        this.nid = nid;
        this.nyear = nyear;
        this.nmonth = nmonth;
        this.nContent = nContent;
        this.created = created;
        this.modified = modified;
        this.gtmCreate = gtmCreate;
        this.gtmModified = gtmModified;
    }

    public Integer getNid() {
        return nid;
    }

    public void setNid(Integer nid) {
        this.nid = nid;
    }

    public Long getCreated() {
        return created;
    }

    public void setCreated(Long created) {
        this.created = created;
    }

    public Long getModified() {
        return modified;
    }

    public void setModified(Long modified) {
        this.modified = modified;
    }


    public Integer getNyear() {
        return nyear;
    }

    public void setNyear(Integer nyear) {
        this.nyear = nyear;
    }

    public Integer getNmonth() {
        return nmonth;
    }

    public void setNmonth(Integer nmonth) {
        this.nmonth = nmonth;
    }

    public String getnContent() {
        return nContent;
    }

    public void setnContent(String nContent) {
        this.nContent = nContent;
    }

    public Date getGtmCreate() {
        return gtmCreate;
    }

    public void setGtmCreate(Date gtmCreate) {
        this.gtmCreate = gtmCreate;
    }

    public Date getGtmModified() {
        return gtmModified;
    }

    public void setGtmModified(Date gtmModified) {
        this.gtmModified = gtmModified;
    }
}

