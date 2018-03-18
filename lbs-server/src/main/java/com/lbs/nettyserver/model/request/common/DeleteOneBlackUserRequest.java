package com.lbs.nettyserver.model.request.common;

/**
 * 将某一用户从用户黑名单中删除
 * @author visual
 *
 */
public class DeleteOneBlackUserRequest {

	/**
	 * 所有者用户id
	 */
	private String ownUserId;
	/**
	 * 被添加至黑名单的用户id
	 */
	private String blackUserId;

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
}
