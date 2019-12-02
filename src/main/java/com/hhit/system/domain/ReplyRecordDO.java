package com.hhit.system.domain;

import java.io.Serializable;
import java.util.Date;


/**
 * 周报发送记录
 */
public class ReplyRecordDO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     *  编号
     */
    private Long id;
    //通知通告ID
    private Integer reportId;
    //接受人
    private Long userId;
    //阅读标记
    private Integer isRead;
    //阅读时间
    private Date readDate;

    /**
     * 设置：编号
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取：编号
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置：通知通告ID
     * @param notifyId
     */
    public void setReportId(Integer notifyId) {
        this.reportId = notifyId;
    }

    /**
     * 获取：通知通告ID
     */
    public Integer getReportId() {
        return reportId;
    }

    /**
     * 设置：接受人
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * 获取：接受人
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * 设置：阅读标记
     */
    public void setIsRead(Integer isRead) {
        this.isRead = isRead;
    }

    /**
     * 获取：阅读标记
     */
    public Integer getIsRead() {
        return isRead;
    }

    /**
     * 设置：阅读时间
     */
    public void setReadDate(Date readDate) {
        this.readDate = readDate;
    }

    /**
     * 获取：阅读时间
     */
    public Date getReadDate() {
        return readDate;
    }

    @Override
    public String toString() {
        return "ReplyRecordDO{" +
                "id=" + id +
                ", reportId=" + reportId +
                ", userId=" + userId +
                ", isRead=" + isRead +
                ", readDate=" + readDate +
                '}';
    }
}
