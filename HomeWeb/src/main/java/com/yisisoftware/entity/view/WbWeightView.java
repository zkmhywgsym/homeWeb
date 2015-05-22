package com.yisisoftware.entity.view;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class WbWeightView implements Serializable {

	/**
	 * 轻车或重车时间
	 */
	private String time;
	
	/**
	 * 收发类型
	 */
	private Short type;
	
	/**
	 * 实发净重
	 */
	private Double weight;

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public Short getType() {
		return type;
	}

	public void setType(Short type) {
		this.type = type;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}
	
}
