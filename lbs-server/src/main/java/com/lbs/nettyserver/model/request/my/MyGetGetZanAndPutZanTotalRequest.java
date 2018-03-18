package com.lbs.nettyserver.model.request.my;

/**
 * 获取我得到的赞和点出的赞的总数
 * @author visual
 *
 */
public class MyGetGetZanAndPutZanTotalRequest {

	private String userId;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
}
