package com.hhit.downloadRes.domain;


import java.io.Serializable;
import java.util.Date;

/**
 * @Title: SoftwareDO
 * @description:  
 * @author liujun
 * @create 2018-09-03
 * 
 */
public class SoftwareDO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer softId;
    // 软件/服务创建/发布日期
    private Date softCreateDate;
    // 软件包的id外键
    private Long softFid;
    // 软件/服务包名
    private String softName;
    // 软件/服务介绍
    private String softIntroduction;
    // 软件/服务关键词
    private String softKeywords;
    // 软件/服务功能介绍
    private String softFunction;
    // 软件/服务作者
    private String softAuthor;
    // 软件服务作者邮箱
    private String softAuthorEmail;
    // 软件/服务版本
    private String softVersion;
    // 是否开放
    private boolean softopen;
    // 类型
    private String softType;
    // 创建人
    private Long softCreateby;
    // 说明/备注
    private String softComment;

    public SoftwareDO() {
    }

    public SoftwareDO(Date softCreateDate, Long softFid, String softName, String softIntroduction, String softKeywords, String softFunction, String softAuthor, String softAuthorEmail, String softVersion,String softType,String softComment) {
        this.softCreateDate = softCreateDate;
        this.softFid = softFid;
        this.softName = softName;
        this.softIntroduction = softIntroduction;
        this.softKeywords = softKeywords;
        this.softFunction = softFunction;
        this.softAuthor = softAuthor;
        this.softAuthorEmail = softAuthorEmail;
        this.softVersion = softVersion;
        this.softType = softType;
        this.softComment = softComment;
    }
    public SoftwareDO(Date softCreateDate, Long softFid, String softName, String softIntroduction, String softKeywords, String softFunction, String softAuthor, String softAuthorEmail, String softVersion,String softType,String softComment,boolean softOpen,Long softCreateby) {
        this.softCreateDate = softCreateDate;
        this.softFid = softFid;
        this.softName = softName;
        this.softIntroduction = softIntroduction;
        this.softKeywords = softKeywords;
        this.softFunction = softFunction;
        this.softAuthor = softAuthor;
        this.softAuthorEmail = softAuthorEmail;
        this.softVersion = softVersion;
        this.softType = softType;
        this.softComment = softComment;
        this.softopen = softOpen;
        this.softCreateby = softCreateby;
    }

    public SoftwareDO(Integer softId, Date softCreateDate, Long softFid, String softName, String softIntroduction, String softKeywords, String softFunction, String softAuthor, String softAuthorEmail, String softVersion, boolean softOpen, String softType, Long softCreateby, String softComment) {
        this.softId = softId;
        this.softCreateDate = softCreateDate;
        this.softFid = softFid;
        this.softName = softName;
        this.softIntroduction = softIntroduction;
        this.softKeywords = softKeywords;
        this.softFunction = softFunction;
        this.softAuthor = softAuthor;
        this.softAuthorEmail = softAuthorEmail;
        this.softVersion = softVersion;
        this.softopen = softOpen;
        this.softType = softType;
        this.softCreateby = softCreateby;
        this.softComment = softComment;
    }

    public String getSoftType() {
        return softType;
    }

    public void setSoftType(String softType) {
        this.softType = softType;
    }

    public boolean isSoftopen() {
        return softopen;
    }

    public void setSoftopen(boolean softopen) {
        this.softopen = softopen;
    }

    public Long getSoftCreateby() {
        return softCreateby;
    }

    public void setSoftCreateby(Long softCreateby) {
        this.softCreateby = softCreateby;
    }

    public String getSoftComment() {
        return softComment;
    }

    public void setSoftComment(String softComment) {
        this.softComment = softComment;
    }

    public String getSoftVersion() {
        return softVersion;
    }

    public void setSoftVersion(String softVersion) {
        this.softVersion = softVersion;
    }

    public Integer getSoftId() {
        return softId;
    }

    public void setSoftId(Integer softId) {
        this.softId = softId;
    }

    public Date getSoftCreateDate() {
        return softCreateDate;
    }

    public void setSoftCreateDate(Date softCreateDate) {
        this.softCreateDate = softCreateDate;
    }

    public Long getSoftFid() {
        return softFid;
    }

    public void setSoftFid(Long softFid) {
        this.softFid = softFid;
    }

    public String getSoftName() {
        return softName;
    }

    public void setSoftName(String softName) {
        this.softName = softName;
    }

    public String getSoftIntroduction() {
        return softIntroduction;
    }

    public void setSoftIntroduction(String softIntroduction) {
        this.softIntroduction = softIntroduction;
    }

    public String getSoftKeywords() {
        return softKeywords;
    }

    public void setSoftKeywords(String softKeywords) {
        this.softKeywords = softKeywords;
    }

    public String getSoftFunction() {
        return softFunction;
    }

    public void setSoftFunction(String softFunction) {
        this.softFunction = softFunction;
    }

    public String getSoftAuthor() {
        return softAuthor;
    }

    public void setSoftAuthor(String softAuthor) {
        this.softAuthor = softAuthor;
    }

    public String getSoftAuthorEmail() {
        return softAuthorEmail;
    }

    public void setSoftAuthorEmail(String softAuthorEmail) {
        this.softAuthorEmail = softAuthorEmail;
    }
}