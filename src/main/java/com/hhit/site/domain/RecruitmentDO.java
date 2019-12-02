package com.hhit.site.domain;

import java.io.Serializable;
import java.util.Date;

public class RecruitmentDO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long rid;
    // 招生类型
    private String rType;
    // 截至时间
    private String rEndtime;
    // 联系人
    private String rLinkman;
    // 联系邮箱
    private String rLinkemail;
    // 办公地址
    private String rLinkaddress;
    // 组织简述
    private String rintroduce;
    // 研究方向/招聘方向
    private String rResearea;
    // 招聘要求
    private String requirment;
    // 招募说明
    private String rComment;
    // 创建人
    private Long Created;
    // 修改人
    private Long Modified;
    // 创建时间
    private Date gtmCreate;
    // 修改时间
    private Date gtmModified;

    public RecruitmentDO() {
        super();
    }

    public RecruitmentDO(String rEndtime, String rType,String rLinkman, String rLinkemail, String rLinkaddress, String rintroduce, String rResearea, String requirment, String rComment, Date gtmCreate, Date gtmModified) {
        this.rEndtime = rEndtime;
        this.rType = rType;
        this.rLinkman = rLinkman;
        this.rLinkemail = rLinkemail;
        this.rLinkaddress = rLinkaddress;
        this.rintroduce = rintroduce;
        this.rResearea = rResearea;
        this.requirment = requirment;
        this.rComment = rComment;
        this.gtmCreate = gtmCreate;
        this.gtmModified = gtmModified;
    }

    public RecruitmentDO(Long rid, String rEndtime, String rType,String rLinkman, String rLinkemail, String rLinkaddress, String rintroduce, String rResearea, String requirment, String rComment, Date gtmCreate, Date gtmModified) {
        this.rid = rid;
        this.rEndtime = rEndtime;
        this.rType = rType;
        this.rLinkman = rLinkman;
        this.rLinkemail = rLinkemail;
        this.rLinkaddress = rLinkaddress;
        this.rintroduce = rintroduce;
        this.rResearea = rResearea;
        this.requirment = requirment;
        this.rComment = rComment;
        this.gtmCreate = gtmCreate;
        this.gtmModified = gtmModified;
    }

    public Long getCreated() {
        return Created;
    }

    public void setCreated(Long created) {
        Created = created;
    }

    public Long getModified() {
        return Modified;
    }

    public void setModified(Long modified) {
        Modified = modified;
    }

    public Long getRid() {
        return rid;
    }

    public void setRid(Long rid) {
        this.rid = rid;
    }

    public String getrType() {
        return rType;
    }

    public void setrType(String rType) {
        this.rType = rType;
    }

    public String getrEndtime() {
        return rEndtime;
    }

    public void setrEndtime(String rEndtime) {
        this.rEndtime = rEndtime;
    }

    public String getrLinkman() {
        return rLinkman;
    }

    public void setrLinkman(String rLinkman) {
        this.rLinkman = rLinkman;
    }

    public String getrLinkemail() {
        return rLinkemail;
    }

    public void setrLinkemail(String rLinkemail) {
        this.rLinkemail = rLinkemail;
    }

    public String getrLinkaddress() {
        return rLinkaddress;
    }

    public void setrLinkaddress(String rLinkaddress) {
        this.rLinkaddress = rLinkaddress;
    }

    public String getRintroduce() {
        return rintroduce;
    }

    public void setRintroduce(String rintroduce) {
        this.rintroduce = rintroduce;
    }

    public String getrResearea() {
        return rResearea;
    }

    public void setrResearea(String rResearea) {
        this.rResearea = rResearea;
    }

    public String getRequirment() {
        return requirment;
    }

    public void setRequirment(String requirment) {
        this.requirment = requirment;
    }

    public String getrComment() {
        return rComment;
    }

    public void setrComment(String rComment) {
        this.rComment = rComment;
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