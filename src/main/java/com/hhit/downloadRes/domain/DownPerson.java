package com.hhit.downloadRes.domain;

public class DownPerson implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer person_id;
	// 下载用户的姓名
	private String person_name;
	// 下载用户的邮箱
	private String person_email;
	// 下载用户的地址: 国家
	private String person_country;
	// 下载用户的机构单位
	private String person_institution;
    // 下载文件id
    private String fid;
	
	// Constructors

	/** default constructor */
	public DownPerson() {
	}

    public DownPerson( String person_name, String person_email, String person_country, String person_institution, String fid) {
        this.person_name = person_name;
        this.person_email = person_email;
        this.person_country = person_country;
        this.person_institution = person_institution;
        this.fid = fid;
    }
    public DownPerson(Integer person_id, String person_name, String person_email, String person_country, String person_institution, String fid) {
        this.person_id = person_id;
        this.person_name = person_name;
        this.person_email = person_email;
        this.person_country = person_country;
        this.person_institution = person_institution;
        this.fid = fid;
    }

    public Integer getPerson_id() {
        return person_id;
    }

    public void setPerson_id(Integer person_id) {
        this.person_id = person_id;
    }

    public String getPerson_name() {
        return person_name;
    }

    public void setPerson_name(String person_name) {
        this.person_name = person_name;
    }

    public String getPerson_email() {
        return person_email;
    }

    public void setPerson_email(String person_email) {
        this.person_email = person_email;
    }

    public String getPerson_country() {
        return person_country;
    }

    public void setPerson_country(String person_country) {
        this.person_country = person_country;
    }

    public String getPerson_institution() {
        return person_institution;
    }

    public void setPerson_institution(String person_institution) {
        this.person_institution = person_institution;
    }

    public String getFid() {
        return fid;
    }

    public void setFid(String fid) {
        this.fid = fid;
    }
}