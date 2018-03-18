package com.lbs.nettyserver.model.response.common;

import java.util.Date;

import com.lbs.nettyserver.utils.sysutils.TimeUtil;

/**
 * 拉取用户与用户之间的私信消息响应类
 * @author visual
 *
 */
public class PrivatePullChatResponse {

	/**
	 * 消息id
	 */
	private String msgId;
	
	/**
	 * 发送方的用户id
	 */
	private String fromUserId;
	
	/**
	 * 接收方的用户id
	 */
	private String toUserId;

	/**
	 * 消息内容
	 */
	private String chatContent;
	/**
	 * 发送时间
	 */
	private String sendTime;

	public String getMsgId() {
		return msgId;
	}

	public void setMsgId(String msgId) {
		this.msgId = msgId;
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

	public String getSendTime() {
		return sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = TimeUtil.dateTimeFormatToString(sendTime, "yyyy-MM-dd HH:mm:ss.SSS");
	}
}
