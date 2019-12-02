package com.hhit.common.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 文件上传
 *
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2017-09-19 16:02:20
 */
public class FileDO implements Serializable {
    private static final long serialVersionUID = 1L;

    //
    private Long id;
    // 文件类型
    private Integer type;
    // 由谁提交
    private Long createby;
    // URL地址
    private String url;
    // 创建时间
    private Date createDate;
    // 是否开放下载
    private Boolean open;
    // 是否开放官网下载
    private Boolean softopen;

    // default constructor
    public FileDO() {
        super();
    }

    public FileDO(Integer type, String url, Date createDate) {
        super();
        this.type = type;
        this.url = url;
        this.createDate = createDate;
    }

    public FileDO(Integer type, String url, Date createDate,Long createby) {
        super();
        this.type = type;
        this.url = url;
        this.createDate = createDate;
        this.createby = createby;
    }
    public FileDO(Integer type, String url, Date createDate, Boolean open,Long createby) {
        super();
        this.type = type;
        this.url = url;
        this.createDate = createDate;
        this.open = open;
        this.createby =createby;
    }

    // full constructor
    public FileDO(Integer type, String url, Date createDate, Boolean open,Long createby,Boolean softopen) {
        super();
        this.type = type;
        this.url = url;
        this.createDate = createDate;
        this.open = open;
        this.createby =createby;
        this.softopen = softopen;
    }


    /**
     * 设置：
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取：
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置：文件类型
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 获取：文件类型
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置：URL地址
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 获取：URL地址
     */
    public String getUrl() {
        return url;
    }

    /**
     * 设置：创建时间
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * 获取：创建时间
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     *  获取： 由谁上传
     */
    public Long getCreateby() {
        return createby;
    }

    /**
     * 设置： 由谁上传
     */
    public void setCreateby(Long createby) {
        this.createby = createby;
    }


    public Boolean getSoftopen() {
        return softopen;
    }

    public void setSoftopen(Boolean softopen) {
        this.softopen = softopen;
    }

    /**
     *
     * 获取: 是否开放下载
     *
     */
    public Boolean getOpen() {
        return open;
    }
    /**
     *
     * 设置: 是否开放下载
     *
     */
    public void setOpen(Boolean open) {
        this.open = open;
    }

    @Override
    public String toString() {
        return "FileDO{" +
                "id=" + id +
                ", type=" + type +
                "create=" + createby +
                ", url='" + url + '\'' +
                ", createDate=" + createDate +
                ", open=" + open +
                ", softopen=" + softopen +
                '}';
    }
}
