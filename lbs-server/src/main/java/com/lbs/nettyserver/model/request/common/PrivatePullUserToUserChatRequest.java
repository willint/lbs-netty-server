package com.lbs.nettyserver.model.request.common;

import java.util.Date;

import com.lbs.nettyserver.utils.sysutils.TimeUtil;

/**
 * 拉取用户与用户之间的私信消息请求类
 * @author visual
 *
 */
public class PrivatePullUserToUserChatRequest {
	
	/**
	 * 发送方的用户id
	 */
	private String fromUserId;

	/**
	 * 接收者的用户id
	 */
	private String toUserId;
	
	/**
	 * 拉取消息的开始时间点(精确到毫秒yyyy-MM-dd HH:mm:ss.SSS)
	 */
	private String startTime;
	
	

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

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		if(startTime==null || "".equals(startTime)){
			startTime=TimeUtil.dateTimeFormatToString(new Date(), "yyyy-MM-dd HH:mm:ss.SSS");
		}
		this.startTime = startTime;
	}
	
	
}
