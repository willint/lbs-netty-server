package com.lbs.nettyserver.model.request.common;

/**
 * 私信-拉取不在客户端私信列表中的用户的其他私信请求类
 * @author visual
 *
 */
public class PrivatePullNotInClientChatRequest {

	/**
	 * 当前的用户id
	 */
	private String toUserId;
	
	/**
	 * 客户端用户私信列表的用户id
	 */
	private String[] clientUserIdArry;

	public String getToUserId() {
		return toUserId;
	}

	public void setToUserId(String toUserId) {
		this.toUserId = toUserId;
	}

	public String[] getClientUserIdArry() {
		return clientUserIdArry;
	}

	public void setClientUserIdArry(String[] clientUserIdArry) {
		this.clientUserIdArry = clientUserIdArry;
	}
	
	
}
