package com.hhit.site.domain;


import java.io.Serializable;
import java.util.Date;

/**
 *
 * 
 * @Title: PublicationDO
 * @description:  
 * @author liujun
 * @create 2018-10-05
 * 
 */
public class PublicationDO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer pid;
    private Integer pcategories;
    private String ppublications;
    private Long created;
    private Long modified;
    private Date gtmCreate;
    private Date gtmModified;

    public PublicationDO() {
    }

    public PublicationDO(Integer pcategories, String ppublications, Long created, Long modified, Date gtmCreate, Date gtmModified) {
        this.pcategories = pcategories;
        this.ppublications = ppublications;
        this.created = created;
        this.modified = modified;
        this.gtmCreate = gtmCreate;
        this.gtmModified = gtmModified;
    }

    public PublicationDO(Integer pid, Integer pcategories, String ppublications, Long created, Long modified, Date gtmCreate, Date gtmModified) {
        this.pid = pid;
        this.pcategories = pcategories;
        this.ppublications = ppublications;
        this.created = created;
        this.modified = modified;
        this.gtmCreate = gtmCreate;
        this.gtmModified = gtmModified;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getPcategories() {
        return pcategories;
    }

    public void setPcategories(Integer pcategories) {
        this.pcategories = pcategories;
    }

    public String getPpublications() {
        return ppublications;
    }

    public void setPpublications(String ppublications) {
        this.ppublications = ppublications;
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
