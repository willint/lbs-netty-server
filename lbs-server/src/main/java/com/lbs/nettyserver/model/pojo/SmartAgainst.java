package com.lbs.nettyserver.model.pojo;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 智囊-反对实体类
 * @author visual
 *
 */
@Entity
@Table(name="t_smart_against_detail")
public class SmartAgainst {

	/**
	 * id
	 */
	@Id
    @Column(name = "sag_id")
	private String sagId;
	
	/**
	 * 智囊消息id
	 */
	@Column(name = "sm_id")
	private String smId;
	
	/**
	 * 发表 反对的用户id
	 */
	@Column(name = "user_id")
	private String userId;
	
	/**
	 * 被 反对的用户id
	 */
	@Column(name = "be_user_id")
	private String beUserId;
	
	/**
	 *  反对时的经度
	 */
	@Column(name = "jd")
	private BigDecimal jd;
	
	/**
	 *  反对时的纬度
	 */
	@Column(name = "wd")
	private BigDecimal wd;
	
	/**
	 * 地理位置名称
	 */
	@Column(name = "location_name")
	private String locationName;
	
	/**
	 * 反对的时间
	 */
	@Column(name = "against_time")
	private Date againstTime;
	
	/**
	 * 预留0
	 */
	@Column(name = "free0")
	private String free0;
	
	/**
	 * 预留1
	 */
	@Column(name = "free1")
	private String free1;

	public String getSagId() {
		return sagId;
	}

	public void setSagId(String sagId) {
		this.sagId = sagId;
	}

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

	public String getBeUserId() {
		return beUserId;
	}

	public void setBeUserId(String beUserId) {
		this.beUserId = beUserId;
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

	public Date getAgainstTime() {
		return againstTime;
	}

	public void setAgainstTime(Date againstTime) {
		this.againstTime = againstTime;
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
