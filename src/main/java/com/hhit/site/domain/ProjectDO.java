package com.hhit.site.domain;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * 
 * @Title: ProjectDO
 * @description:  
 * @author liujun
 * @create 2018-10-05
 * 
 */
public class ProjectDO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long pid;
    // 项目时期
    private String pdate;
    // 项目内容
    private String pContent;
    // 创建人
    private Long created;
    // 修改人
    private Long modified;
    // 创建日期
    private Date gtmCreate;
    // 修改日期
    private Date gtmModified;

    public ProjectDO() {
    }

    public ProjectDO(String pdate, String pContent, Long created, Long modified, Date gtmCreate, Date gtmModified) {
        this.pdate = pdate;
        this.pContent = pContent;
        this.created = created;
        this.modified = modified;
        this.gtmCreate = gtmCreate;
        this.gtmModified = gtmModified;
    }

    public ProjectDO(Long pid, String pdate, String pContent, Long created, Long modified, Date gtmCreate, Date gtmModified) {
        this.pid = pid;
        this.pdate = pdate;
        this.pContent = pContent;
        this.created = created;
        this.modified = modified;
        this.gtmCreate = gtmCreate;
        this.gtmModified = gtmModified;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
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

    public String getPdate() {
        return pdate;
    }

    public void setPdate(String pdate) {
        this.pdate = pdate;
    }

    public String getpContent() {
        return pContent;
    }

    public void setpContent(String pContent) {
        this.pContent = pContent;
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
