package com.hhit.system.domain;

import java.io.Serializable;
import java.util.Date;

public class WeekreportDO implements Serializable {

    private Integer wid;
    // 周报内容
    private String wContent;
    // 创建人id
    private Long uid;
    // 创建人姓名
    private String created;
    // 所属组ID
    private Long deptid;
    // 创建时间
    private Date gtmCreate;
    // 修改时间
    private Date gtmModified;
    // 通知类型
    private Integer type;
    // 批复人员接收名单
    private Long[] userIds;
    // 批复内容
    private String wreply;
    // 备注
    private String remarks;
    // 当前由谁批注 id
    private Long pushBy;
    // 由谁批注
    private String createBy;
    // 是否已批阅
    private Integer isRead;

    public WeekreportDO() {
    }

    public WeekreportDO(String wContent, Integer type, String created, Long deptid,Date gtmCreate, Date gtmModified) {
        this.wContent = wContent;
        this.type = type;
        this.created = created;
        this.deptid = deptid ;
        this.gtmCreate = gtmCreate;
        this.gtmModified = gtmModified;
    }

    public WeekreportDO(Integer wid, String wContent,Integer type, Long deptid,String created, Date gtmCreate, Date gtmModified) {
        this.wid = wid;
        this.wContent = wContent;
        this.type = type;
        this.deptid = deptid;
        this.created = created;
        this.gtmCreate = gtmCreate;
        this.gtmModified = gtmModified;
    }

    public WeekreportDO(Integer wid, String wContent,Integer type, Long uid, String created, Long deptid, Date gtmCreate, Date gtmModified) {
        this.wid = wid;
        this.wContent = wContent;
        this.uid = uid;
        this.type = type;
        this.created = created;
        this.deptid = deptid;
        this.gtmCreate = gtmCreate;
        this.gtmModified = gtmModified;
    }

    public WeekreportDO(Integer wid, String wContent,Integer type, Long uid, String created, Long deptid, Date gtmCreate, Date gtmModified, Long[] userIds) {
        this.wid = wid;
        this.wContent = wContent;
        this.type = type;
        this.uid = uid;
        this.created = created;
        this.deptid = deptid;
        this.gtmCreate = gtmCreate;
        this.gtmModified = gtmModified;
        this.userIds = userIds;
    }

    public Long getPushBy() {
        return pushBy;
    }

    public void setPushBy(Long pushBy) {
        this.pushBy = pushBy;
    }

    public Integer getIsRead() {
        return isRead;
    }

    public void setIsRead(Integer isRead) {
        this.isRead = isRead;
    }

    public Integer getWid() {
        return wid;
    }

    public void setWid(Integer wid) {
        this.wid = wid;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getWreply() {
        return wreply;
    }

    public void setWreply(String wreply) {
        this.wreply = wreply;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getCreateBy() {
        return  createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Long getDeptid() {
        return deptid;
    }

    public void setDeptid(Long deptid) {
        this.deptid = deptid;
    }

    public String getwContent() {
        return wContent;
    }

    public void setwContent(String wContent) {
        this.wContent = wContent;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public Long[] getUserIds() {
        return userIds;
    }

    public void setUserIds(Long[] userIds) {
        this.userIds = userIds;
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
