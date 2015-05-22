package com.yisisoftware.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 客户实体
 * 
 * WbCustomer entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "WB_Customer")
public class WbCustomer implements java.io.Serializable {

	// Fields

	private String id;
	private String code;
	private String name;
	private String ShortName;
	private String spellCode;
	private String iccard;
	private String idcard;
	private String rfcard;
	private String area;
	private String address;
	private String contectPerson;
	private String tel;
	private String createUser;
	private Date createDate;
	private String updateUser;
	private Date updateDate;
	private String deleteUser;
	private Date deleteDate;
	private Short isDel;
	private Short isUse;
	private Short upload;
	private String memo;

	// Constructors

	/** default constructor */
	public WbCustomer() {
	}

	/** minimal constructor */
	public WbCustomer(String id) {
		this.id = id;
	}

	/** full constructor */
	public WbCustomer(String id, String code, String name, String ShortName,
			String spellCode, String iccard, String idcard, String rfcard,
			String area, String address, String contectPerson, String tel,
			String createUser, Date createDate, String updateUser,
			Date updateDate, String deleteUser, Date deleteDate, Short isDel,
			Short isUse, Short upload, String memo) {
		this.id = id;
		this.code = code;
		this.name = name;
		this.ShortName = ShortName;
		this.spellCode = spellCode;
		this.iccard = iccard;
		this.idcard = idcard;
		this.rfcard = rfcard;
		this.area = area;
		this.address = address;
		this.contectPerson = contectPerson;
		this.tel = tel;
		this.createUser = createUser;
		this.createDate = createDate;
		this.updateUser = updateUser;
		this.updateDate = updateDate;
		this.deleteUser = deleteUser;
		this.deleteDate = deleteDate;
		this.isDel = isDel;
		this.isUse = isUse;
		this.upload = upload;
		this.memo = memo;
	}

	// Property accessors
	@Id
	@Column(name = "Id", unique = true, nullable = false, length = 20)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "Code", length = 20)
	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "Name")
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "ShortName")
	public String getShortName() {
		return this.ShortName;
	}

	public void setShortName(String ShortName) {
		this.ShortName = ShortName;
	}

	@Column(name = "SpellCode")
	public String getSpellCode() {
		return this.spellCode;
	}

	public void setSpellCode(String spellCode) {
		this.spellCode = spellCode;
	}

	@Column(name = "ICCard", length = 20)
	public String getIccard() {
		return this.iccard;
	}

	public void setIccard(String iccard) {
		this.iccard = iccard;
	}

	@Column(name = "IDCard", length = 30)
	public String getIdcard() {
		return this.idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	@Column(name = "RFCard", length = 50)
	public String getRfcard() {
		return this.rfcard;
	}

	public void setRfcard(String rfcard) {
		this.rfcard = rfcard;
	}

	@Column(name = "Area", length = 20)
	public String getArea() {
		return this.area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	@Column(name = "Address")
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "ContectPerson")
	public String getContectPerson() {
		return this.contectPerson;
	}

	public void setContectPerson(String contectPerson) {
		this.contectPerson = contectPerson;
	}

	@Column(name = "Tel", length = 50)
	public String getTel() {
		return this.tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	@Column(name = "CreateUser", length = 20)
	public String getCreateUser() {
		return this.createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	@Column(name = "CreateDate", length = 23)
	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Column(name = "UpdateUser", length = 20)
	public String getUpdateUser() {
		return this.updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	@Column(name = "UpdateDate", length = 23)
	public Date getUpdateDate() {
		return this.updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	@Column(name = "DeleteUser", length = 20)
	public String getDeleteUser() {
		return this.deleteUser;
	}

	public void setDeleteUser(String deleteUser) {
		this.deleteUser = deleteUser;
	}

	@Column(name = "DeleteDate", length = 23)
	public Date getDeleteDate() {
		return this.deleteDate;
	}

	public void setDeleteDate(Date deleteDate) {
		this.deleteDate = deleteDate;
	}

	@Column(name = "IsDel")
	public Short getIsDel() {
		return this.isDel;
	}

	public void setIsDel(Short isDel) {
		this.isDel = isDel;
	}

	@Column(name = "IsUse")
	public Short getIsUse() {
		return this.isUse;
	}

	public void setIsUse(Short isUse) {
		this.isUse = isUse;
	}

	@Column(name = "upload")
	public Short getUpload() {
		return this.upload;
	}

	public void setUpload(Short upload) {
		this.upload = upload;
	}

	@Column(name = "Memo")
	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

}