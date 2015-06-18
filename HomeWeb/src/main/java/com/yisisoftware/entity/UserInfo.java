package com.yisisoftware.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

//提交后台数据
@Entity
@Table(name = "user_info")
public class UserInfo {
	@Id
	private int id;
	private String carNum;
	private String dearName;
	private String realName;
	private Date createDate;
	private Date lastLoginTime;
	private String phone;
	private String identification;
	private String email;
	
	public UserInfo() {
		super();
	}

	public String getCarNum() {
		return carNum;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setCarNum(String carNum) {
		this.carNum = carNum;
	}

	public String getDearName() {
		return dearName;
	}

	public void setDearName(String dearName) {
		this.dearName = dearName;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}


	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getIdentification() {
		return identification;
	}

	public void setIdentification(String identification) {
		this.identification = identification;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	
}
