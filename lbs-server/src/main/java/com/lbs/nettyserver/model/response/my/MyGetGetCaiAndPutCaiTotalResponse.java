package com.lbs.nettyserver.model.response.my;

import java.math.BigDecimal;

/**
 * 获取我得到的踩和点出的踩的总数响应类
 * @author visual
 *
 */
public class MyGetGetCaiAndPutCaiTotalResponse {


	private BigDecimal getCaiTotal;
	
	private BigDecimal putCaiTotal;

	public BigDecimal getGetCaiTotal() {
		return getCaiTotal;
	}

	public void setGetCaiTotal(BigDecimal getCaiTotal) {
		this.getCaiTotal = getCaiTotal;
	}

	public BigDecimal getPutCaiTotal() {
		return putCaiTotal;
	}

	public void setPutCaiTotal(BigDecimal putCaiTotal) {
		this.putCaiTotal = putCaiTotal;
	}
	
	
}
