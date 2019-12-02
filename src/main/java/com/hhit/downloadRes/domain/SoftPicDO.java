package com.hhit.downloadRes.domain;


import java.io.Serializable;

/**
 *
 * 
 * @Title: SoftPicDO
 * @description:  
 * @author liujun
 * @create 2018-09-05
 * 
 */
public class SoftPicDO implements Serializable {
    private Long soft_fid;
    private String soft_pic01;

    public SoftPicDO() {
    }

    public SoftPicDO(Long soft_fid, String soft_pic01) {
        this.soft_fid = soft_fid;
        this.soft_pic01 = soft_pic01;
    }

    public String getSoft_pic01() {
        return soft_pic01;
    }

    public void setSoft_pic01(String soft_pic01) {
        this.soft_pic01 = soft_pic01;
    }

    public Long getSoft_fid() {
        return soft_fid;
    }

    public void setSoft_fid(Long soft_fid) {
        this.soft_fid = soft_fid;
    }
}
