package com.hhit.site.domain;


import java.io.Serializable;
import java.util.Date;

/**
 *
 * 
 * @Title: MemberDO
 * @description:  
 * @author liujun
 * @create 2018-10-05
 * 
 */
public class MemberDO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer mid;
    // 姓名
    private String mname;
    // 姓名(英文)
    private String mnameEng;
    // 图片名
    private String pic;
    // 职称
    private String mjob;
    // 组别
    private String mgroup;
    // 介绍
    private String mintroduce;
    // 介绍（英文版）
    private String mintroduceEng;
    // 研究领域(中文版)
    private String mResearea;
    // 研究领域(英文版)
    private String mReseareaEng;
    // 创建人id
    private Long created;
    // 最近修改人id
    private Long modified;
    // 创建时间
    private Date gtmCreate;
    // 修改时间
    private Date gtmModified;
    // 分类
    private String mcategory;

    public MemberDO() {
    }

    public MemberDO(Integer mid, String pic) {
        this.mid = mid;
        this.pic = pic;
    }

    public MemberDO(String mname, String mnameEng, String pic, String mjob, String mintroduce, String mintroduceEng, String mResearea, String mReseareaEng, Long created, Long modified, Date gtmCreate, Date gtmModified,String mgroup,String mcategory) {
        this.mname = mname;
        this.mnameEng = mnameEng;
        this.pic = pic;
        this.mjob = mjob;
        this.mintroduce = mintroduce;
        this.mintroduceEng = mintroduceEng;
        this.mResearea = mResearea;
        this.mReseareaEng = mReseareaEng;
        this.created = created;
        this.modified = modified;
        this.gtmCreate = gtmCreate;
        this.gtmModified = gtmModified;
        this.mgroup = mgroup;
        this.mcategory = mcategory;
    }

    public MemberDO(Integer mid, String mname, String mnameEng, String pic,String mjob, String mintroduce, String mintroduceEng, String mResearea, String mReseareaEng, Long created, Long modified, Date gtmCreate, Date gtmModified,String mgroup,String mcategory) {
        this.mid = mid;
        this.mname = mname;
        this.mnameEng = mnameEng;
        this.pic = pic;
        this.mjob = mjob;
        this.mintroduce = mintroduce;
        this.mintroduceEng = mintroduceEng;
        this.mResearea = mResearea;
        this.mReseareaEng = mReseareaEng;
        this.created = created;
        this.modified = modified;
        this.gtmCreate = gtmCreate;
        this.gtmModified = gtmModified;
        this.mgroup = mgroup;
        this.mcategory = mcategory;
    }

    public String getMcategory() {
        return mcategory;
    }

    public void setMcategory(String mcategory) {
        this.mcategory = mcategory;
    }

    public String getMgroup() {
        return mgroup;
    }

    public void setMgroup(String mgroup) {
        this.mgroup = mgroup;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public Integer getMid() {
        return mid;
    }

    public void setMid(Integer mid) {
        this.mid = mid;
    }

    public String getMname() {
        return mname;
    }

    public void setMname(String mname) {
        this.mname = mname;
    }

    public String getMnameEng() {
        return mnameEng;
    }

    public void setMnameEng(String mnameEng) {
        this.mnameEng = mnameEng;
    }

    public String getMjob() {
        return mjob;
    }

    public void setMjob(String mjob) {
        this.mjob = mjob;
    }

    public String getMintroduce() {
        return mintroduce;
    }

    public void setMintroduce(String mintroduce) {
        this.mintroduce = mintroduce;
    }

    public String getMintroduceEng() {
        return mintroduceEng;
    }

    public void setMintroduceEng(String mintroduceEng) {
        this.mintroduceEng = mintroduceEng;
    }

    public String getmResearea() {
        return mResearea;
    }

    public void setmResearea(String mResearea) {
        this.mResearea = mResearea;
    }

    public String getmReseareaEng() {
        return mReseareaEng;
    }

    public void setmReseareaEng(String mReseareaEng) {
        this.mReseareaEng = mReseareaEng;
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
