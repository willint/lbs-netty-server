package com.lbs.nettyserver.model.request.common;

/**
 * 删除某一用户和用户之间的私信消息请求类
 * @author visual
 *
 */
public class PrivateDeleteUserToUserChatRequest {

	private String fromUserId;
	
	private String toUserId;

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
	
	
}
