package com.lbs.nettyserver.model.pojo;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 智囊-智囊消息实体类
 * @author visual
 *
 */
@Entity
@Table(name="t_smart_message")
public class SmartMessage {

	/**
	 * 主键
	 */
	@Id
    @Column(name = "sm_id")
	private String smId;
	/**
	 * 用户ID
	 */
	@Column(name = "user_id")
	private String userId;
	/**
	 * 经度
	 */
	@Column(name = "jd")
	private BigDecimal jd;
	/**
	 * 纬度
	 */
	@Column(name = "wd")
	private BigDecimal wd;
	
	/**
	 * 地理位置名称
	 */
	@Column(name = "location_name")
	private String locationName;
	
	
	/**
	 * 具体的消息内容
	 */
	@Column(name = "content")
	private String content;
	
	/**
	 * 得分
	 */
	@Column(name = "smart_score")
	private BigDecimal smartScore;
	
	/**
	 * 发布时间
	 */
	@Column(name = "send_time")
	private Date sendTime;
	/**
	 * 预留
	 */
	@Column(name = "free0")
	private String free0;
	/**
	 * 预留
	 */
	@Column(name = "free1")
	private String free1;

	/**
	 * 是否已删除，0-未删除，1-已删除
	 */
	@Column(name = "is_delete")
	private Character isDelete;

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

	public Date getSendTime() {
		return sendTime;
	}

	public void setSendTime(Date sendTime) {
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

	public Character getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Character isDelete) {
		this.isDelete = isDelete;
	}
	
	
}
