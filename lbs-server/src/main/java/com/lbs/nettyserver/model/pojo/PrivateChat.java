package com.lbs.nettyserver.model.pojo;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 私信实体类
 * @author visual
 *
 */
@Entity
@Table(name="t_private_chat")
public class PrivateChat {

	/**
	 * 主键
	 */
	@Id
    @Column(name = "pc_id")
	private String pcId;
	
	@Column(name = "from_user_id")
	private String fromUserId;
	
	@Column(name = "to_user_id")
	private String toUserId;
	
	@Column(name = "content")
	private String chatContent;
	
	@Column(name = "is_back")
	private boolean isBack;
	
	@Column(name = "is_sended")
	private boolean isSended;
	
	@Column(name = "send_jd")
	private BigDecimal sendJd;
	
	@Column(name = "send_wd")
	private BigDecimal sendWd;
	
	@Column(name = "free0")
	private String free0;
	
	@Column(name = "free1")
	private String free1;
	
	@Column(name = "send_time")
	private Date sendTime;

	public String getPcId() {
		return pcId;
	}

	public void setPcId(String pcId) {
		this.pcId = pcId;
	}

	public String getFromUserId() {
		return fromUserId;
	}

	public void setFromUserId(String fromUserId) {
		this.fromUserId = fromUserId;
	}

	public String getToUserId() {
		return toUserId;
	}

	public void setToUserId(String toUserId) {
		this.toUserId = toUserId;
	}

	public String getChatContent() {
		return chatContent;
	}

	public void setChatContent(String chatContent) {
		this.chatContent = chatContent;
	}

	public boolean isBack() {
		return isBack;
	}

	public void setBack(boolean isBack) {
		this.isBack = isBack;
	}

	public boolean isSended() {
		return isSended;
	}

	public void setSended(boolean isSended) {
		this.isSended = isSended;
	}

	public BigDecimal getSendJd() {
		return sendJd;
	}

	public void setSendJd(BigDecimal sendJd) {
		this.sendJd = sendJd;
	}

	public BigDecimal getSendWd() {
		return sendWd;
	}

	public void setSendWd(BigDecimal sendWd) {
		this.sendWd = sendWd;
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

	public Date getSendTime() {
		return sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

}
