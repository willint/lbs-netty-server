package com.lbs.nettyserver.model.response.my;

import java.math.BigDecimal;

/**
 * 我的-获取现场我得到的评论和发表的评论的总数响应类
 * @author visual
 *
 */
public class MyGetGetAndPutLiveCommentTotalResponse {

	private BigDecimal getLiveCommentTotal;
	
	private BigDecimal putLiveCommentTotal;

	public BigDecimal getGetLiveCommentTotal() {
		return getLiveCommentTotal;
	}

	public void setGetLiveCommentTotal(BigDecimal getLiveCommentTotal) {
		this.getLiveCommentTotal = getLiveCommentTotal;
	}

	public BigDecimal getPutLiveCommentTotal() {
		return putLiveCommentTotal;
	}

	public void setPutLiveCommentTotal(BigDecimal putLiveCommentTotal) {
		this.putLiveCommentTotal = putLiveCommentTotal;
	}
	
	
}
