package com.lbs.nettyserver.model.request.my;

/**
 * 我的-获取我得到的现场评论和发表的评论请求类
 * @author visual
 *
 */
public class MyGetGetAndPutLiveCommentTotalRequest {

	private String userId;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
}
