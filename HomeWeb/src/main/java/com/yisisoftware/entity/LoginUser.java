package com.yisisoftware.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "LoginUser")
public class LoginUser implements Serializable {

	/**
	 * 主键 自增长
	 */
	private int id;
	
	/**
	 * 用户登录id
	 */
	private String adminUserId;
	
	/**
	 * 用户登状态（待审核：0，正常：1，停用：2）
	 */
	private String status="0";
	
	/**
	 * 用户名	
	 */
	private String adminUserName;
	
	/**
	 * 用户密码
	 */
	private String adminPassWord;
	
	/**
	 * 用户创建时间
	 */
	private Date adminDate;
	
	/**
	 * 用户最后登录时间
	 */
	private Date adminLastDate;
	
	/**
	 * 角色
	 */
	private String role;
	
	private String icCard;
	
	private String idCard;
	
	private String rfCard;
	
	private String isUser;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAdminUserId() {
		return adminUserId;
	}

	public void setAdminUserId(String adminUserId) {
		this.adminUserId = adminUserId;
	}

	public String getAdminUserName() {
		return adminUserName;
	}

	public void setAdminUserName(String adminUserName) {
		this.adminUserName = adminUserName;
	}

	public String getAdminPassWord() {
		return adminPassWord;
	}

	public void setAdminPassWord(String adminPassWord) {
		this.adminPassWord = adminPassWord;
	}

	public Date getAdminDate() {
		return adminDate;
	}

	public void setAdminDate(Date adminDate) {
		this.adminDate = adminDate;
	}

	public Date getAdminLastDate() {
		return adminLastDate;
	}

	public void setAdminLastDate(Date adminLastDate) {
		this.adminLastDate = adminLastDate;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getIcCard() {
		return icCard;
	}

	public void setIcCard(String icCard) {
		this.icCard = icCard;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getRfCard() {
		return rfCard;
	}

	public void setRfCard(String rfCard) {
		this.rfCard = rfCard;
	}

	public String getIsUser() {
		return isUser;
	}

	public void setIsUser(String isUser) {
		this.isUser = isUser;
	}
	



	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "WbAdminUser [id=" + id + ", adminUserId=" + adminUserId
				+ ", adminUserName=" + adminUserName + ", adminPassWord="
				+ adminPassWord + ", adminDate=" + adminDate
				+ ", adminLastDate=" + adminLastDate + ", role=" + role
				+ ", icCard=" + icCard + ", idCard=" + idCard + ", rfCard="
				+ rfCard + ", isUser=" + isUser + "]";
	}
	
}
