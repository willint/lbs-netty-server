package com.lbs.nettyserver.model.response.smart;

import java.math.BigDecimal;

/**
 * 智囊-发布智囊消息相应类
 * @author visual
 *
 */
public class SmartPublishMessageResponse {

	/**
	 * 主键
	 */
	private String smId;
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
	 * 地理位置名称
	 */
	private String locationName;
	
	
	/**
	 * 具体的消息内容
	 */
	private String content;
	
	/**
	 * 得分
	 */
	private BigDecimal smartScore;
	
	/**
	 * 发布时间
	 */
	private String sendTime;
	/**
	 * 预留
	 */
	private String free0;
	/**
	 * 预留
	 */
	private String free1;
	public String getSmId() {
		return smId;
	}
	public void setSmId(String smId) {
		this.smId = smId;
	}
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
	public String getLocationName() {
		return locationName;
	}
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public BigDecimal getSmartScore() {
		return smartScore;
	}
	public void setSmartScore(BigDecimal smartScore) {
		this.smartScore = smartScore;
	}
	public String getSendTime() {
		return sendTime;
	}
	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}
	public String getFree0() {
		return free0;
	}
	public void setFree0(String free0) {
		this.free0 = free0;
	}
	public String getFree1() {
		return free1;
	}
	public void setFree1(String free1) {
		this.free1 = free1;
	}
	
	
	
	
}
