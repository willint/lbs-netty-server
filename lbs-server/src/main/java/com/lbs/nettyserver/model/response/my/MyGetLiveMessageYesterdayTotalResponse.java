package com.lbs.nettyserver.model.response.my;

import java.math.BigInteger;

/**
 * 我的-获取用户昨天发布的现场消息的总数响应类
 * @author visual
 *
 */
public class MyGetLiveMessageYesterdayTotalResponse {

	private BigInteger liveMessageYesterdayTotal;

	public BigInteger getLiveMessageYesterdayTotal() {
		return liveMessageYesterdayTotal;
	}

	public void setLiveMessageYesterdayTotal(BigInteger liveMessageYesterdayTotal) {
		this.liveMessageYesterdayTotal = liveMessageYesterdayTotal;
	}
	
	
}
