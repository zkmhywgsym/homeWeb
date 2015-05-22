package com.yisisoftware.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * WbWeight entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "WB_Weight")
public class WbWeight implements java.io.Serializable {

	// Fields

	private String billNo;
	private Integer id;
	private String outBillNo;
	private String contractBillNo;
	private String cars;
	private String carsTeam;
	private String carsType;
	private String supply;
	private String customer;
	private String drivers;
	private String material;
	private String materialKind;
	private String trafficCompany;
	private String store;
	private String weightNo;
	private String lightNo;
	private Date weightDate;
	private Date lightDate;
	private Date createDate;
	private Date printDate;
	private Date updateDate;
	private Date deleteDate;
	private Double yfgross;
	private Double yftare;
	private Double yfsuttle;
	private Double gross;
	private Double tare;
	private Double suttle;
	private Double fbgross;
	private Double fbtare;
	private Double fbsuttle;
	private Double handGross;
	private Double handTare;
	private Double handSuttle;
	private Double inCimpurity;
	private Double inCwater;
	private Double inCweight;
	private Double outCimpurity;
	private Double outCwater;
	private Double outCweight;
	private Double price;
	private Double charge;
	private Double taxPrice;
	private Double taxCharge;
	private Double trafficPrice;
	private Double trafficCharge;
	private Double loadPrice;
	private Double loadCharge;
	private Double weightPrice;
	private Double weightCharge;
	private String loadCars;
	private String loadUser;
	private String loadAddress;
	private String inGbuser;
	private String inDbuser;
	private String outGbuser;
	private String outDbuser;
	private Integer printCount;
	private Short type;
	private Short computerType;
	private String memo;
	private Short isPic;
	private Short upload;
	private String receive;
	private String receiveUser;
	private String send;
	private String sendUser;
	private String iccard;
	private String idcard;
	private String rfidcard;
	private String contractType;
	private Short twoBang;
	private Double cutWeight;
	private Short cutWeightFlag;
	private Double remainCharge;
	private Double creditCharge;
	private String reservationChar1;
	private String reservationChar2;
	private String reservationChar3;
	private Double reservationNums1;
	private Double reservationNums2;
	private Double reservationNums3;
	private String reservationList1;
	private String reservationList2;
	private String reservationList3;
	private byte[] picture;
	private Short operateType;
	private String operateUser;
	private Date operateDate;
	private Short test;
	private Short finish;
	private String autoBillNo;
	private String reservationChar4;
	private String reservationChar5;
	private String reservationChar6;
	private String reservationChar7;
	private String reservationChar8;
	private String reservationChar9;
	private Double reservationNums4;
	private Double reservationNums5;
	private Double reservationNums6;
	private Double reservationNums7;
	private String reservationList4;
	private String reservationList5;
	private String reservationList6;
	private String reservationList7;
	private String reservationList8;
	private String reservationList9;
	private String reservationChar10;
	private String reservationChar11;
	private String reservationChar12;

	// Constructors

	/** default constructor */
	public WbWeight() {
	}

	/** minimal constructor */
	public WbWeight(String billNo, Integer id) {
		this.billNo = billNo;
		this.id = id;
	}

	/** full constructor */
	public WbWeight(String billNo, Integer id, String outBillNo,
			String contractBillNo, String cars, String carsTeam,
			String carsType, String supply, String customer, String drivers,
			String material, String materialKind, String trafficCompany,
			String store, String weightNo, String lightNo, Date weightDate,
			Date lightDate, Date createDate, Date printDate, Date updateDate,
			Date deleteDate, Double yfgross, Double yftare, Double yfsuttle,
			Double gross, Double tare, Double suttle, Double fbgross,
			Double fbtare, Double fbsuttle, Double handGross, Double handTare,
			Double handSuttle, Double inCimpurity, Double inCwater,
			Double inCweight, Double outCimpurity, Double outCwater,
			Double outCweight, Double price, Double charge, Double taxPrice,
			Double taxCharge, Double trafficPrice, Double trafficCharge,
			Double loadPrice, Double loadCharge, Double weightPrice,
			Double weightCharge, String loadCars, String loadUser,
			String loadAddress, String inGbuser, String inDbuser,
			String outGbuser, String outDbuser, Integer printCount, Short type,
			Short computerType, String memo, Short isPic, Short upload,
			String receive, String receiveUser, String send, String sendUser,
			String iccard, String idcard, String rfidcard, String contractType,
			Short twoBang, Double cutWeight, Short cutWeightFlag,
			Double remainCharge, Double creditCharge, String reservationChar1,
			String reservationChar2, String reservationChar3,
			Double reservationNums1, Double reservationNums2,
			Double reservationNums3, String reservationList1,
			String reservationList2, String reservationList3, byte[] picture,
			Short operateType, String operateUser, Date operateDate,
			Short test, Short finish, String autoBillNo,
			String reservationChar4, String reservationChar5,
			String reservationChar6, String reservationChar7,
			String reservationChar8, String reservationChar9,
			Double reservationNums4, Double reservationNums5,
			Double reservationNums6, Double reservationNums7,
			String reservationList4, String reservationList5,
			String reservationList6, String reservationList7,
			String reservationList8, String reservationList9,
			String reservationChar10, String reservationChar11,
			String reservationChar12) {
		this.billNo = billNo;
		this.id = id;
		this.outBillNo = outBillNo;
		this.contractBillNo = contractBillNo;
		this.cars = cars;
		this.carsTeam = carsTeam;
		this.carsType = carsType;
		this.supply = supply;
		this.customer = customer;
		this.drivers = drivers;
		this.material = material;
		this.materialKind = materialKind;
		this.trafficCompany = trafficCompany;
		this.store = store;
		this.weightNo = weightNo;
		this.lightNo = lightNo;
		this.weightDate = weightDate;
		this.lightDate = lightDate;
		this.createDate = createDate;
		this.printDate = printDate;
		this.updateDate = updateDate;
		this.deleteDate = deleteDate;
		this.yfgross = yfgross;
		this.yftare = yftare;
		this.yfsuttle = yfsuttle;
		this.gross = gross;
		this.tare = tare;
		this.suttle = suttle;
		this.fbgross = fbgross;
		this.fbtare = fbtare;
		this.fbsuttle = fbsuttle;
		this.handGross = handGross;
		this.handTare = handTare;
		this.handSuttle = handSuttle;
		this.inCimpurity = inCimpurity;
		this.inCwater = inCwater;
		this.inCweight = inCweight;
		this.outCimpurity = outCimpurity;
		this.outCwater = outCwater;
		this.outCweight = outCweight;
		this.price = price;
		this.charge = charge;
		this.taxPrice = taxPrice;
		this.taxCharge = taxCharge;
		this.trafficPrice = trafficPrice;
		this.trafficCharge = trafficCharge;
		this.loadPrice = loadPrice;
		this.loadCharge = loadCharge;
		this.weightPrice = weightPrice;
		this.weightCharge = weightCharge;
		this.loadCars = loadCars;
		this.loadUser = loadUser;
		this.loadAddress = loadAddress;
		this.inGbuser = inGbuser;
		this.inDbuser = inDbuser;
		this.outGbuser = outGbuser;
		this.outDbuser = outDbuser;
		this.printCount = printCount;
		this.type = type;
		this.computerType = computerType;
		this.memo = memo;
		this.isPic = isPic;
		this.upload = upload;
		this.receive = receive;
		this.receiveUser = receiveUser;
		this.send = send;
		this.sendUser = sendUser;
		this.iccard = iccard;
		this.idcard = idcard;
		this.rfidcard = rfidcard;
		this.contractType = contractType;
		this.twoBang = twoBang;
		this.cutWeight = cutWeight;
		this.cutWeightFlag = cutWeightFlag;
		this.remainCharge = remainCharge;
		this.creditCharge = creditCharge;
		this.reservationChar1 = reservationChar1;
		this.reservationChar2 = reservationChar2;
		this.reservationChar3 = reservationChar3;
		this.reservationNums1 = reservationNums1;
		this.reservationNums2 = reservationNums2;
		this.reservationNums3 = reservationNums3;
		this.reservationList1 = reservationList1;
		this.reservationList2 = reservationList2;
		this.reservationList3 = reservationList3;
		this.picture = picture;
		this.operateType = operateType;
		this.operateUser = operateUser;
		this.operateDate = operateDate;
		this.test = test;
		this.finish = finish;
		this.autoBillNo = autoBillNo;
		this.reservationChar4 = reservationChar4;
		this.reservationChar5 = reservationChar5;
		this.reservationChar6 = reservationChar6;
		this.reservationChar7 = reservationChar7;
		this.reservationChar8 = reservationChar8;
		this.reservationChar9 = reservationChar9;
		this.reservationNums4 = reservationNums4;
		this.reservationNums5 = reservationNums5;
		this.reservationNums6 = reservationNums6;
		this.reservationNums7 = reservationNums7;
		this.reservationList4 = reservationList4;
		this.reservationList5 = reservationList5;
		this.reservationList6 = reservationList6;
		this.reservationList7 = reservationList7;
		this.reservationList8 = reservationList8;
		this.reservationList9 = reservationList9;
		this.reservationChar10 = reservationChar10;
		this.reservationChar11 = reservationChar11;
		this.reservationChar12 = reservationChar12;
	}

	// Property accessors
	@Id
	@Column(name = "BillNO", unique = true, nullable = false, length = 20)
	public String getBillNo() {
		return this.billNo;
	}

	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}

	@Column(name = "Id", nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "OutBillNO", length = 20)
	public String getOutBillNo() {
		return this.outBillNo;
	}

	public void setOutBillNo(String outBillNo) {
		this.outBillNo = outBillNo;
	}

	@Column(name = "ContractBillNO", length = 20)
	public String getContractBillNo() {
		return this.contractBillNo;
	}

	public void setContractBillNo(String contractBillNo) {
		this.contractBillNo = contractBillNo;
	}

	@Column(name = "Cars", length = 20)
	public String getCars() {
		return this.cars;
	}

	public void setCars(String cars) {
		this.cars = cars;
	}

	@Column(name = "CarsTeam", length = 20)
	public String getCarsTeam() {
		return this.carsTeam;
	}

	public void setCarsTeam(String carsTeam) {
		this.carsTeam = carsTeam;
	}

	@Column(name = "CarsType", length = 20)
	public String getCarsType() {
		return this.carsType;
	}

	public void setCarsType(String carsType) {
		this.carsType = carsType;
	}

	@Column(name = "Supply", length = 20)
	public String getSupply() {
		return this.supply;
	}

	public void setSupply(String supply) {
		this.supply = supply;
	}

	@Column(name = "Customer", length = 20)
	public String getCustomer() {
		return this.customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	@Column(name = "Drivers", length = 20)
	public String getDrivers() {
		return this.drivers;
	}

	public void setDrivers(String drivers) {
		this.drivers = drivers;
	}

	@Column(name = "Material", length = 20)
	public String getMaterial() {
		return this.material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}

	@Column(name = "MaterialKind", length = 20)
	public String getMaterialKind() {
		return this.materialKind;
	}

	public void setMaterialKind(String materialKind) {
		this.materialKind = materialKind;
	}

	@Column(name = "TrafficCompany", length = 20)
	public String getTrafficCompany() {
		return this.trafficCompany;
	}

	public void setTrafficCompany(String trafficCompany) {
		this.trafficCompany = trafficCompany;
	}

	@Column(name = "Store", length = 20)
	public String getStore() {
		return this.store;
	}

	public void setStore(String store) {
		this.store = store;
	}

	@Column(name = "WeightNO", length = 10)
	public String getWeightNo() {
		return this.weightNo;
	}

	public void setWeightNo(String weightNo) {
		this.weightNo = weightNo;
	}

	@Column(name = "LightNO", length = 10)
	public String getLightNo() {
		return this.lightNo;
	}

	public void setLightNo(String lightNo) {
		this.lightNo = lightNo;
	}

	@Column(name = "WeightDate", length = 23)
	public Date getWeightDate() {
		return this.weightDate;
	}

	public void setWeightDate(Date weightDate) {
		this.weightDate = weightDate;
	}

	@Column(name = "LightDate", length = 23)
	public Date getLightDate() {
		return this.lightDate;
	}

	public void setLightDate(Date lightDate) {
		this.lightDate = lightDate;
	}

	@Column(name = "CreateDate", length = 23)
	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Column(name = "PrintDate", length = 23)
	public Date getPrintDate() {
		return this.printDate;
	}

	public void setPrintDate(Date printDate) {
		this.printDate = printDate;
	}

	@Column(name = "UpdateDate", length = 23)
	public Date getUpdateDate() {
		return this.updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	@Column(name = "DeleteDate", length = 23)
	public Date getDeleteDate() {
		return this.deleteDate;
	}

	public void setDeleteDate(Date deleteDate) {
		this.deleteDate = deleteDate;
	}

	@Column(name = "YFGross", precision = 18, scale = 3)
	public Double getYfgross() {
		return this.yfgross;
	}

	public void setYfgross(Double yfgross) {
		this.yfgross = yfgross;
	}

	@Column(name = "YFTare", precision = 18, scale = 3)
	public Double getYftare() {
		return this.yftare;
	}

	public void setYftare(Double yftare) {
		this.yftare = yftare;
	}

	@Column(name = "YFSuttle", precision = 18, scale = 3)
	public Double getYfsuttle() {
		return this.yfsuttle;
	}

	public void setYfsuttle(Double yfsuttle) {
		this.yfsuttle = yfsuttle;
	}

	@Column(name = "Gross", precision = 18, scale = 3)
	public Double getGross() {
		return this.gross;
	}

	public void setGross(Double gross) {
		this.gross = gross;
	}

	@Column(name = "Tare", precision = 18, scale = 3)
	public Double getTare() {
		return this.tare;
	}

	public void setTare(Double tare) {
		this.tare = tare;
	}

	@Column(name = "Suttle", precision = 18, scale = 3)
	public Double getSuttle() {
		return this.suttle;
	}

	public void setSuttle(Double suttle) {
		this.suttle = suttle;
	}

	@Column(name = "FBGross", precision = 18, scale = 3)
	public Double getFbgross() {
		return this.fbgross;
	}

	public void setFbgross(Double fbgross) {
		this.fbgross = fbgross;
	}

	@Column(name = "FBTare", precision = 18, scale = 3)
	public Double getFbtare() {
		return this.fbtare;
	}

	public void setFbtare(Double fbtare) {
		this.fbtare = fbtare;
	}

	@Column(name = "FBSuttle", precision = 18, scale = 3)
	public Double getFbsuttle() {
		return this.fbsuttle;
	}

	public void setFbsuttle(Double fbsuttle) {
		this.fbsuttle = fbsuttle;
	}

	@Column(name = "HandGross", precision = 18, scale = 3)
	public Double getHandGross() {
		return this.handGross;
	}

	public void setHandGross(Double handGross) {
		this.handGross = handGross;
	}

	@Column(name = "HandTare", precision = 18, scale = 3)
	public Double getHandTare() {
		return this.handTare;
	}

	public void setHandTare(Double handTare) {
		this.handTare = handTare;
	}

	@Column(name = "HandSuttle", precision = 18, scale = 3)
	public Double getHandSuttle() {
		return this.handSuttle;
	}

	public void setHandSuttle(Double handSuttle) {
		this.handSuttle = handSuttle;
	}

	@Column(name = "InCImpurity", precision = 18, scale = 3)
	public Double getInCimpurity() {
		return this.inCimpurity;
	}

	public void setInCimpurity(Double inCimpurity) {
		this.inCimpurity = inCimpurity;
	}

	@Column(name = "InCWater", precision = 18, scale = 3)
	public Double getInCwater() {
		return this.inCwater;
	}

	public void setInCwater(Double inCwater) {
		this.inCwater = inCwater;
	}

	@Column(name = "InCWeight", precision = 18, scale = 3)
	public Double getInCweight() {
		return this.inCweight;
	}

	public void setInCweight(Double inCweight) {
		this.inCweight = inCweight;
	}

	@Column(name = "OutCImpurity", precision = 18, scale = 3)
	public Double getOutCimpurity() {
		return this.outCimpurity;
	}

	public void setOutCimpurity(Double outCimpurity) {
		this.outCimpurity = outCimpurity;
	}

	@Column(name = "OutCWater", precision = 18, scale = 3)
	public Double getOutCwater() {
		return this.outCwater;
	}

	public void setOutCwater(Double outCwater) {
		this.outCwater = outCwater;
	}

	@Column(name = "OutCWeight", precision = 18, scale = 3)
	public Double getOutCweight() {
		return this.outCweight;
	}

	public void setOutCweight(Double outCweight) {
		this.outCweight = outCweight;
	}

	@Column(name = "Price", precision = 18, scale = 3)
	public Double getPrice() {
		return this.price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@Column(name = "Charge", precision = 18, scale = 3)
	public Double getCharge() {
		return this.charge;
	}

	public void setCharge(Double charge) {
		this.charge = charge;
	}

	@Column(name = "TaxPrice", precision = 18, scale = 3)
	public Double getTaxPrice() {
		return this.taxPrice;
	}

	public void setTaxPrice(Double taxPrice) {
		this.taxPrice = taxPrice;
	}

	@Column(name = "TaxCharge", precision = 18, scale = 3)
	public Double getTaxCharge() {
		return this.taxCharge;
	}

	public void setTaxCharge(Double taxCharge) {
		this.taxCharge = taxCharge;
	}

	@Column(name = "TrafficPrice", precision = 18, scale = 3)
	public Double getTrafficPrice() {
		return this.trafficPrice;
	}

	public void setTrafficPrice(Double trafficPrice) {
		this.trafficPrice = trafficPrice;
	}

	@Column(name = "TrafficCharge", precision = 18, scale = 3)
	public Double getTrafficCharge() {
		return this.trafficCharge;
	}

	public void setTrafficCharge(Double trafficCharge) {
		this.trafficCharge = trafficCharge;
	}

	@Column(name = "LoadPrice", precision = 18, scale = 3)
	public Double getLoadPrice() {
		return this.loadPrice;
	}

	public void setLoadPrice(Double loadPrice) {
		this.loadPrice = loadPrice;
	}

	@Column(name = "LoadCharge", precision = 18, scale = 3)
	public Double getLoadCharge() {
		return this.loadCharge;
	}

	public void setLoadCharge(Double loadCharge) {
		this.loadCharge = loadCharge;
	}

	@Column(name = "WeightPrice", precision = 18, scale = 3)
	public Double getWeightPrice() {
		return this.weightPrice;
	}

	public void setWeightPrice(Double weightPrice) {
		this.weightPrice = weightPrice;
	}

	@Column(name = "WeightCharge", precision = 18, scale = 3)
	public Double getWeightCharge() {
		return this.weightCharge;
	}

	public void setWeightCharge(Double weightCharge) {
		this.weightCharge = weightCharge;
	}

	@Column(name = "LoadCars", length = 20)
	public String getLoadCars() {
		return this.loadCars;
	}

	public void setLoadCars(String loadCars) {
		this.loadCars = loadCars;
	}

	@Column(name = "LoadUser")
	public String getLoadUser() {
		return this.loadUser;
	}

	public void setLoadUser(String loadUser) {
		this.loadUser = loadUser;
	}

	@Column(name = "LoadAddress")
	public String getLoadAddress() {
		return this.loadAddress;
	}

	public void setLoadAddress(String loadAddress) {
		this.loadAddress = loadAddress;
	}

	@Column(name = "InGBUser", length = 20)
	public String getInGbuser() {
		return this.inGbuser;
	}

	public void setInGbuser(String inGbuser) {
		this.inGbuser = inGbuser;
	}

	@Column(name = "InDBUser", length = 20)
	public String getInDbuser() {
		return this.inDbuser;
	}

	public void setInDbuser(String inDbuser) {
		this.inDbuser = inDbuser;
	}

	@Column(name = "OutGBUser", length = 20)
	public String getOutGbuser() {
		return this.outGbuser;
	}

	public void setOutGbuser(String outGbuser) {
		this.outGbuser = outGbuser;
	}

	@Column(name = "OutDBUser", length = 20)
	public String getOutDbuser() {
		return this.outDbuser;
	}

	public void setOutDbuser(String outDbuser) {
		this.outDbuser = outDbuser;
	}

	@Column(name = "PrintCount")
	public Integer getPrintCount() {
		return this.printCount;
	}

	public void setPrintCount(Integer printCount) {
		this.printCount = printCount;
	}

	@Column(name = "Type")
	public Short getType() {
		return this.type;
	}

	public void setType(Short type) {
		this.type = type;
	}

	@Column(name = "ComputerType")
	public Short getComputerType() {
		return this.computerType;
	}

	public void setComputerType(Short computerType) {
		this.computerType = computerType;
	}

	@Column(name = "Memo")
	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	@Column(name = "IsPic")
	public Short getIsPic() {
		return this.isPic;
	}

	public void setIsPic(Short isPic) {
		this.isPic = isPic;
	}

	@Column(name = "upload")
	public Short getUpload() {
		return this.upload;
	}

	public void setUpload(Short upload) {
		this.upload = upload;
	}

	@Column(name = "Receive", length = 20)
	public String getReceive() {
		return this.receive;
	}

	public void setReceive(String receive) {
		this.receive = receive;
	}

	@Column(name = "ReceiveUser", length = 20)
	public String getReceiveUser() {
		return this.receiveUser;
	}

	public void setReceiveUser(String receiveUser) {
		this.receiveUser = receiveUser;
	}

	@Column(name = "Send", length = 20)
	public String getSend() {
		return this.send;
	}

	public void setSend(String send) {
		this.send = send;
	}

	@Column(name = "SendUser", length = 20)
	public String getSendUser() {
		return this.sendUser;
	}

	public void setSendUser(String sendUser) {
		this.sendUser = sendUser;
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

	@Column(name = "RFIDCard", length = 50)
	public String getRfidcard() {
		return this.rfidcard;
	}

	public void setRfidcard(String rfidcard) {
		this.rfidcard = rfidcard;
	}

	@Column(name = "ContractType", length = 20)
	public String getContractType() {
		return this.contractType;
	}

	public void setContractType(String contractType) {
		this.contractType = contractType;
	}

	@Column(name = "TwoBang")
	public Short getTwoBang() {
		return this.twoBang;
	}

	public void setTwoBang(Short twoBang) {
		this.twoBang = twoBang;
	}

	@Column(name = "CutWeight", precision = 10, scale = 3)
	public Double getCutWeight() {
		return this.cutWeight;
	}

	public void setCutWeight(Double cutWeight) {
		this.cutWeight = cutWeight;
	}

	@Column(name = "CutWeightFlag")
	public Short getCutWeightFlag() {
		return this.cutWeightFlag;
	}

	public void setCutWeightFlag(Short cutWeightFlag) {
		this.cutWeightFlag = cutWeightFlag;
	}

	@Column(name = "RemainCharge", precision = 18, scale = 3)
	public Double getRemainCharge() {
		return this.remainCharge;
	}

	public void setRemainCharge(Double remainCharge) {
		this.remainCharge = remainCharge;
	}

	@Column(name = "CreditCharge", precision = 18, scale = 3)
	public Double getCreditCharge() {
		return this.creditCharge;
	}

	public void setCreditCharge(Double creditCharge) {
		this.creditCharge = creditCharge;
	}

	@Column(name = "ReservationChar1")
	public String getReservationChar1() {
		return this.reservationChar1;
	}

	public void setReservationChar1(String reservationChar1) {
		this.reservationChar1 = reservationChar1;
	}

	@Column(name = "ReservationChar2")
	public String getReservationChar2() {
		return this.reservationChar2;
	}

	public void setReservationChar2(String reservationChar2) {
		this.reservationChar2 = reservationChar2;
	}

	@Column(name = "ReservationChar3")
	public String getReservationChar3() {
		return this.reservationChar3;
	}

	public void setReservationChar3(String reservationChar3) {
		this.reservationChar3 = reservationChar3;
	}

	@Column(name = "ReservationNums1", precision = 18, scale = 3)
	public Double getReservationNums1() {
		return this.reservationNums1;
	}

	public void setReservationNums1(Double reservationNums1) {
		this.reservationNums1 = reservationNums1;
	}

	@Column(name = "ReservationNums2", precision = 18, scale = 3)
	public Double getReservationNums2() {
		return this.reservationNums2;
	}

	public void setReservationNums2(Double reservationNums2) {
		this.reservationNums2 = reservationNums2;
	}

	@Column(name = "ReservationNums3", precision = 18, scale = 3)
	public Double getReservationNums3() {
		return this.reservationNums3;
	}

	public void setReservationNums3(Double reservationNums3) {
		this.reservationNums3 = reservationNums3;
	}

	@Column(name = "ReservationList1")
	public String getReservationList1() {
		return this.reservationList1;
	}

	public void setReservationList1(String reservationList1) {
		this.reservationList1 = reservationList1;
	}

	@Column(name = "ReservationList2")
	public String getReservationList2() {
		return this.reservationList2;
	}

	public void setReservationList2(String reservationList2) {
		this.reservationList2 = reservationList2;
	}

	@Column(name = "ReservationList3")
	public String getReservationList3() {
		return this.reservationList3;
	}

	public void setReservationList3(String reservationList3) {
		this.reservationList3 = reservationList3;
	}

	@Column(name = "Picture")
	public byte[] getPicture() {
		return this.picture;
	}

	public void setPicture(byte[] picture) {
		this.picture = picture;
	}

	@Column(name = "OperateType")
	public Short getOperateType() {
		return this.operateType;
	}

	public void setOperateType(Short operateType) {
		this.operateType = operateType;
	}

	@Column(name = "OperateUser", length = 20)
	public String getOperateUser() {
		return this.operateUser;
	}

	public void setOperateUser(String operateUser) {
		this.operateUser = operateUser;
	}

	@Column(name = "OperateDate", length = 23)
	public Date getOperateDate() {
		return this.operateDate;
	}

	public void setOperateDate(Date operateDate) {
		this.operateDate = operateDate;
	}

	@Column(name = "Test")
	public Short getTest() {
		return this.test;
	}

	public void setTest(Short test) {
		this.test = test;
	}

	@Column(name = "Finish")
	public Short getFinish() {
		return this.finish;
	}

	public void setFinish(Short finish) {
		this.finish = finish;
	}

	@Column(name = "AutoBillNO", length = 20)
	public String getAutoBillNo() {
		return this.autoBillNo;
	}

	public void setAutoBillNo(String autoBillNo) {
		this.autoBillNo = autoBillNo;
	}

	@Column(name = "ReservationChar4")
	public String getReservationChar4() {
		return this.reservationChar4;
	}

	public void setReservationChar4(String reservationChar4) {
		this.reservationChar4 = reservationChar4;
	}

	@Column(name = "ReservationChar5")
	public String getReservationChar5() {
		return this.reservationChar5;
	}

	public void setReservationChar5(String reservationChar5) {
		this.reservationChar5 = reservationChar5;
	}

	@Column(name = "ReservationChar6")
	public String getReservationChar6() {
		return this.reservationChar6;
	}

	public void setReservationChar6(String reservationChar6) {
		this.reservationChar6 = reservationChar6;
	}

	@Column(name = "ReservationChar7")
	public String getReservationChar7() {
		return this.reservationChar7;
	}

	public void setReservationChar7(String reservationChar7) {
		this.reservationChar7 = reservationChar7;
	}

	@Column(name = "ReservationChar8")
	public String getReservationChar8() {
		return this.reservationChar8;
	}

	public void setReservationChar8(String reservationChar8) {
		this.reservationChar8 = reservationChar8;
	}

	@Column(name = "ReservationChar9")
	public String getReservationChar9() {
		return this.reservationChar9;
	}

	public void setReservationChar9(String reservationChar9) {
		this.reservationChar9 = reservationChar9;
	}

	@Column(name = "ReservationNums4", precision = 18, scale = 3)
	public Double getReservationNums4() {
		return this.reservationNums4;
	}

	public void setReservationNums4(Double reservationNums4) {
		this.reservationNums4 = reservationNums4;
	}

	@Column(name = "ReservationNums5", precision = 18, scale = 3)
	public Double getReservationNums5() {
		return this.reservationNums5;
	}

	public void setReservationNums5(Double reservationNums5) {
		this.reservationNums5 = reservationNums5;
	}

	@Column(name = "ReservationNums6", precision = 18, scale = 3)
	public Double getReservationNums6() {
		return this.reservationNums6;
	}

	public void setReservationNums6(Double reservationNums6) {
		this.reservationNums6 = reservationNums6;
	}

	@Column(name = "ReservationNums7", precision = 18, scale = 3)
	public Double getReservationNums7() {
		return this.reservationNums7;
	}

	public void setReservationNums7(Double reservationNums7) {
		this.reservationNums7 = reservationNums7;
	}

	@Column(name = "ReservationList4")
	public String getReservationList4() {
		return this.reservationList4;
	}

	public void setReservationList4(String reservationList4) {
		this.reservationList4 = reservationList4;
	}

	@Column(name = "ReservationList5")
	public String getReservationList5() {
		return this.reservationList5;
	}

	public void setReservationList5(String reservationList5) {
		this.reservationList5 = reservationList5;
	}

	@Column(name = "ReservationList6")
	public String getReservationList6() {
		return this.reservationList6;
	}

	public void setReservationList6(String reservationList6) {
		this.reservationList6 = reservationList6;
	}

	@Column(name = "ReservationList7")
	public String getReservationList7() {
		return this.reservationList7;
	}

	public void setReservationList7(String reservationList7) {
		this.reservationList7 = reservationList7;
	}

	@Column(name = "ReservationList8")
	public String getReservationList8() {
		return this.reservationList8;
	}

	public void setReservationList8(String reservationList8) {
		this.reservationList8 = reservationList8;
	}

	@Column(name = "ReservationList9")
	public String getReservationList9() {
		return this.reservationList9;
	}

	public void setReservationList9(String reservationList9) {
		this.reservationList9 = reservationList9;
	}

	@Column(name = "ReservationChar10")
	public String getReservationChar10() {
		return this.reservationChar10;
	}

	public void setReservationChar10(String reservationChar10) {
		this.reservationChar10 = reservationChar10;
	}

	@Column(name = "ReservationChar11")
	public String getReservationChar11() {
		return this.reservationChar11;
	}

	public void setReservationChar11(String reservationChar11) {
		this.reservationChar11 = reservationChar11;
	}

	@Column(name = "ReservationChar12")
	public String getReservationChar12() {
		return this.reservationChar12;
	}

	public void setReservationChar12(String reservationChar12) {
		this.reservationChar12 = reservationChar12;
	}

}