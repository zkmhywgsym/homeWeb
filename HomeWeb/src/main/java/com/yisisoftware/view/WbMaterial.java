package com.yisisoftware.view;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 物料实体
 * 
 * WbMaterial entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "WB_Material")
public class WbMaterial implements java.io.Serializable {

	// Fields

	private String id;
	private String code;
	private String name;
	private String ShortName;
	private String spellCode;
	private Short type;
	private Double price;
	private Double trafficPrice;
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
	public WbMaterial() {
	}

	/** minimal constructor */
	public WbMaterial(String id) {
		this.id = id;
	}

	/** full constructor */
	public WbMaterial(String id, String code, String name, String ShortName,
			String spellCode, Short type, Double price, Double trafficPrice,
			String createUser, Date createDate, String updateUser,
			Date updateDate, String deleteUser, Date deleteDate, Short isDel,
			Short isUse, Short upload, String memo) {
		this.id = id;
		this.code = code;
		this.name = name;
		this.ShortName = ShortName;
		this.spellCode = spellCode;
		this.type = type;
		this.price = price;
		this.trafficPrice = trafficPrice;
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
	@Column(name = "Id", unique = true, nullable = false, length = 70)
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

	@Column(name = "Type")
	public Short getType() {
		return this.type;
	}

	public void setType(Short type) {
		this.type = type;
	}

	@Column(name = "Price", precision = 18, scale = 3)
	public Double getPrice() {
		return this.price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@Column(name = "TrafficPrice", precision = 18, scale = 3)
	public Double getTrafficPrice() {
		return this.trafficPrice;
	}

	public void setTrafficPrice(Double trafficPrice) {
		this.trafficPrice = trafficPrice;
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