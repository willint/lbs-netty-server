package com.lbs.nettyserver.model.request.smart;

import java.math.BigDecimal;

import com.lbs.nettyserver.model.common.SmartMessageContent;

/**
 * 智囊-发布智囊消息请求类
 * @author visual
 *
 */
public class SmartPublishMessageRequest {

	/**
	 * 用户ID
	 */
	private String userId;
	/**
	 * 经度
	 */
	private BigDecimal jd;
	/**
	 * 纬度
	 */
	private BigDecimal wd;
	/**
	 * 现场内容
	 */
	private SmartMessageContent smartMessageContent;
	
	/**
	 * 地理位置名称
	 */
	private String locationName;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public BigDecimal getJd() {
		return jd;
	}

	public void setJd(BigDecimal jd) {
		this.jd = jd;
	}

	public BigDecimal getWd() {
		return wd;
	}

	public void setWd(BigDecimal wd) {
		this.wd = wd;
	}

	public SmartMessageContent getSmartMessageContent() {
		return smartMessageContent;
	}

	public void setSmartMessageContent(SmartMessageContent smartMessageContent) {
		this.smartMessageContent = smartMessageContent;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

}
