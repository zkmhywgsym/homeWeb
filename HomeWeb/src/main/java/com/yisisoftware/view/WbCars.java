package com.yisisoftware.view;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 车牌实体
 * 
 * WbCars entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "WB_Cars")
public class WbCars implements java.io.Serializable {

	// Fields

	private String id;
	private String code;
	private String carType;
	private String trafficCompany;
	private String name;
	private String ShortName;
	private String spellCode;
	private String iccard;
	private String idcard;
	private String rfcard;
	private Double loadWeight;
	private Double tare;
	private Double charge;
	private String owner;
	private String driver;
	private String tel;
	private String area;
	private String address;
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
	private Integer carprop;

	// Constructors

	/** default constructor */
	public WbCars() {
	}

	/** minimal constructor */
	public WbCars(String id) {
		this.id = id;
	}

	/** full constructor */
	public WbCars(String id, String code, String carType,
			String trafficCompany, String name, String ShortName,
			String spellCode, String iccard, String idcard, String rfcard,
			Double loadWeight, Double tare, Double charge, String owner,
			String driver, String tel, String area, String address,
			String createUser, Date createDate, String updateUser,
			Date updateDate, String deleteUser, Date deleteDate, Short isDel,
			Short isUse, Short upload, String memo, Integer carprop) {
		this.id = id;
		this.code = code;
		this.carType = carType;
		this.trafficCompany = trafficCompany;
		this.name = name;
		this.ShortName = ShortName;
		this.spellCode = spellCode;
		this.iccard = iccard;
		this.idcard = idcard;
		this.rfcard = rfcard;
		this.loadWeight = loadWeight;
		this.tare = tare;
		this.charge = charge;
		this.owner = owner;
		this.driver = driver;
		this.tel = tel;
		this.area = area;
		this.address = address;
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
		this.carprop = carprop;
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

	@Column(name = "CarType", length = 20)
	public String getCarType() {
		return this.carType;
	}

	public void setCarType(String carType) {
		this.carType = carType;
	}

	@Column(name = "TrafficCompany", length = 20)
	public String getTrafficCompany() {
		return this.trafficCompany;
	}

	public void setTrafficCompany(String trafficCompany) {
		this.trafficCompany = trafficCompany;
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

	@Column(name = "LoadWeight", precision = 18, scale = 3)
	public Double getLoadWeight() {
		return this.loadWeight;
	}

	public void setLoadWeight(Double loadWeight) {
		this.loadWeight = loadWeight;
	}

	@Column(name = "Tare", precision = 18, scale = 3)
	public Double getTare() {
		return this.tare;
	}

	public void setTare(Double tare) {
		this.tare = tare;
	}

	@Column(name = "Charge", precision = 18, scale = 3)
	public Double getCharge() {
		return this.charge;
	}

	public void setCharge(Double charge) {
		this.charge = charge;
	}

	@Column(name = "Owner")
	public String getOwner() {
		return this.owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	@Column(name = "Driver", length = 20)
	public String getDriver() {
		return this.driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	@Column(name = "Tel")
	public String getTel() {
		return this.tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
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

	@Column(name = "carprop")
	public Integer getCarprop() {
		return this.carprop;
	}

	public void setCarprop(Integer carprop) {
		this.carprop = carprop;
	}

}