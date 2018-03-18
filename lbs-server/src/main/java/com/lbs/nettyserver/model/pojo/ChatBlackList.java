package com.lbs.nettyserver.model.pojo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 私信-黑名单实体类
 * @author visual
 *
 */
@Entity
@Table(name="t_chat_blacklist")
public class ChatBlackList {

	/**
	 * 主键
	 */
	@Id
    @Column(name = "cbk_id")
	private String cbkId;
	/**
	 * 所属用户ID
	 */
	@Column(name = "own_userid")
	private String ownUserId;
	
	/**
	 * 被加入黑明单的用户id
	 */
	@Column(name = "black_userid")
	private String blackUserId;
	
	/**
	 * 创建时间
	 */
	@Column(name = "create_time")
	private Date createTime;
	
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

	public String getCbkId() {
		return cbkId;
	}

	public void setCbkId(String cbkId) {
		this.cbkId = cbkId;
	}

	public String getOwnUserId() {
		return ownUserId;
	}

	public void setOwnUserId(String ownUserId) {
		this.ownUserId = ownUserId;
	}

	public String getBlackUserId() {
		return blackUserId;
	}

	public void setBlackUserId(String blackUserId) {
		this.blackUserId = blackUserId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
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
