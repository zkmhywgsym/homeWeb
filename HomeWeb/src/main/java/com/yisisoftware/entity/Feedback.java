package com.yisisoftware.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 意见反馈
 * 
 * @author Administrator
 *
 */
@Entity
public class Feedback implements Serializable {

	
	/**
	 * 主键id 自增长
	 */
	private Long id;
	
	/**
	 * 意见类型
	 */
	private String feedBackStyle;
	
	/**
	 * 意见内容
	 */
	private String feedBackContent;
	
	/**
	 * 联系方式 
	 */
	private String feedBackContact;
	
	/**
	 * 手机类型
	 */
	private String feedBackMobileType;
	
	/**
	 * 手机Imei码
	 */
	private String feedBackImei;

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFeedBackStyle() {
		return feedBackStyle;
	}

	public void setFeedBackStyle(String feedBackStyle) {
		this.feedBackStyle = feedBackStyle;
	}

	public String getFeedBackContent() {
		return feedBackContent;
	}

	public void setFeedBackContent(String feedBackContent) {
		this.feedBackContent = feedBackContent;
	}

	public String getFeedBackContact() {
		return feedBackContact;
	}

	public void setFeedBackContact(String feedBackContact) {
		this.feedBackContact = feedBackContact;
	}

	public String getFeedBackMobileType() {
		return feedBackMobileType;
	}

	public void setFeedBackMobileType(String feedBackMobileType) {
		this.feedBackMobileType = feedBackMobileType;
	}

	public String getFeedBackImei() {
		return feedBackImei;
	}

	public void setFeedBackImei(String feedBackImei) {
		this.feedBackImei = feedBackImei;
	}

	@Override
	public String toString() {
		return "Feedback [id=" + id + ", feedBackStyle=" + feedBackStyle
				+ ", feedBackContent=" + feedBackContent + ", feedBackContact="
				+ feedBackContact + ", feedBackMobileType="
				+ feedBackMobileType + ", feedBackImei=" + feedBackImei + "]";
	}
	
}
