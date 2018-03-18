package com.lbs.nettyserver.model.response.vomit;

import java.util.Date;

import com.lbs.nettyserver.utils.sysutils.TimeUtil;

/**
 * 拉取吐槽自由消息响应类
 * @author visual
 *
 */
public class VomitPullFreeChatResponse {

	/**
	 * 消息id
	 */
	private String msgId;
	/**
	 * 距离
	 */
	private Double distance; 
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

	public Double getDistance() {
		return distance;
	}

	public void setDistance(Double distance) {
		this.distance = distance;
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
