package com.hhit.downloadRes.domain.VO;


import java.io.Serializable;
import java.util.Date;

/**
 * @Title: SoftwareDO
 * @description:
 * @author liujun
 * @create 2018-09-03
 *
 */
public class SoftWareVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer soft_id;
    private Date soft_create_date;
    private Long soft_fid;
    private String soft_name;
    private String soft_introduction;
    private String soft_keywords;
    private String soft_function;
    private String soft_author;
    private String soft_authorEmail;
    private String soft_version;
    private String soft_pic;

    public SoftWareVO() {
    }

    public SoftWareVO(Date soft_create_date, Long soft_fid, String soft_name, String soft_introduction, String soft_keywords, String soft_function, String soft_author, String soft_authorEmail, String soft_version,String soft_pic) {
        this.soft_create_date = soft_create_date;
        this.soft_fid = soft_fid;
        this.soft_name = soft_name;
        this.soft_introduction = soft_introduction;
        this.soft_keywords = soft_keywords;
        this.soft_function = soft_function;
        this.soft_author = soft_author;
        this.soft_authorEmail = soft_authorEmail;
        this.soft_version = soft_version;
        this.soft_pic = soft_pic;
    }
    public SoftWareVO(Integer soft_id, Date soft_create_date, Long soft_fid, String soft_name, String soft_introduction, String soft_keywords, String soft_function, String soft_author, String soft_authorEmail, String soft_version) {
        this.soft_id = soft_id;
        this.soft_create_date = soft_create_date;
        this.soft_fid = soft_fid;
        this.soft_name = soft_name;
        this.soft_introduction = soft_introduction;
        this.soft_keywords = soft_keywords;
        this.soft_function = soft_function;
        this.soft_author = soft_author;
        this.soft_authorEmail = soft_authorEmail;
        this.soft_version = soft_version;
    }

    public SoftWareVO(Integer soft_id, Date soft_create_date, Long soft_fid, String soft_name, String soft_introduction, String soft_keywords, String soft_function, String soft_author, String soft_authorEmail, String soft_version,String soft_pic) {
        this.soft_id = soft_id;
        this.soft_create_date = soft_create_date;
        this.soft_fid = soft_fid;
        this.soft_name = soft_name;
        this.soft_introduction = soft_introduction;
        this.soft_keywords = soft_keywords;
        this.soft_function = soft_function;
        this.soft_author = soft_author;
        this.soft_authorEmail = soft_authorEmail;
        this.soft_version = soft_version;
        this.soft_pic = soft_pic;
    }

    public String getSoft_pic() {
        return soft_pic;
    }

    public void setSoft_pic(String soft_pic) {
        this.soft_pic = soft_pic;
    }

    public String getSoft_version() {
        return soft_version;
    }

    public void setSoft_version(String soft_version) {
        this.soft_version = soft_version;
    }

    public Integer getSoft_id() {
        return soft_id;
    }

    public void setSoft_id(Integer soft_id) {
        this.soft_id = soft_id;
    }

    public Date getSoft_create_date() {
        return soft_create_date;
    }

    public void setSoft_create_date(Date soft_create_date) {
        this.soft_create_date = soft_create_date;
    }

    public Long getSoft_fid() {
        return soft_fid;
    }

    public void setSoft_fid(Long soft_fid) {
        this.soft_fid = soft_fid;
    }

    public String getSoft_name() {
        return soft_name;
    }

    public void setSoft_name(String soft_name) {
        this.soft_name = soft_name;
    }

    public String getSoft_introduction() {
        return soft_introduction;
    }

    public void setSoft_introduction(String soft_introduction) {
        this.soft_introduction = soft_introduction;
    }

    public String getSoft_keywords() {
        return soft_keywords;
    }

    public void setSoft_keywords(String soft_keywords) {
        this.soft_keywords = soft_keywords;
    }

    public String getSoft_function() {
        return soft_function;
    }

    public void setSoft_function(String soft_function) {
        this.soft_function = soft_function;
    }

    public String getSoft_author() {
        return soft_author;
    }

    public void setSoft_author(String soft_author) {
        this.soft_author = soft_author;
    }

    public String getSoft_authorEmail() {
        return soft_authorEmail;
    }

    public void setSoft_authorEmail(String soft_authorEmail) {
        this.soft_authorEmail = soft_authorEmail;
    }
}