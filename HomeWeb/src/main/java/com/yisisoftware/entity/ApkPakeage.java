package com.yisisoftware.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 手机版本升级信息
 * 
 * @author Administrator
 *
 */
@Entity
public class ApkPakeage implements Serializable {

	/**
	 * 主键id 自增长
	 */
	private Long id;
	
	/**
	 * 版本号
	 */
	private Long verCode;
	
	/**
	 * 版本名称
	 */
	private String verName;
	
	/**
	 * 下载地址
	 */
	private String apkUrl;
	
	/**
	 * apk包名
	 */
	private String apkname;
	
	/**
	 * 上传时间
	 */
	private Date updateTime;
	
	/**
	 * 升级内容
	 */
	private String verContent;
	
	/**
	 * 应用名称
	 */
	private String appname;

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getVerCode() {
		return verCode;
	}

	public void setVerCode(Long verCode) {
		this.verCode = verCode;
	}

	public String getVerName() {
		return verName;
	}

	public void setVerName(String verName) {
		this.verName = verName;
	}

	public String getApkUrl() {
		return apkUrl;
	}

	public void setApkUrl(String apkUrl) {
		this.apkUrl = apkUrl;
	}

	public String getApkname() {
		return apkname;
	}

	public void setApkname(String apkname) {
		this.apkname = apkname;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getVerContent() {
		return verContent;
	}

	public void setVerContent(String verContent) {
		this.verContent = verContent;
	}

	public String getAppname() {
		return appname;
	}

	public void setAppname(String appname) {
		this.appname = appname;
	}

	@Override
	public String toString() {
		return "ApkPakeage [id=" + id + ", verCode=" + verCode + ", verName="
				+ verName + ", apkUrl=" + apkUrl + ", apkname=" + apkname
				+ ", updateTime=" + updateTime + ", verContent=" + verContent
				+ ", appname=" + appname + "]";
	}

}
