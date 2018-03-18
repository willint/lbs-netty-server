package com.lbs.nettyserver.model.response.my;

import java.math.BigDecimal;

/**
 * 获取我得到的赞和点出的赞的总数响应类
 * @author visual
 *
 */
public class MyGetGetZanAndPutZanTotalResponse {

	private BigDecimal getZanTotal;
	
	private BigDecimal putZanTotal;

	public BigDecimal getGetZanTotal() {
		return getZanTotal;
	}

	public void setGetZanTotal(BigDecimal getZanTotal) {
		this.getZanTotal = getZanTotal;
	}

	public BigDecimal getPutZanTotal() {
		return putZanTotal;
	}

	public void setPutZanTotal(BigDecimal putZanTotal) {
		this.putZanTotal = putZanTotal;
	}
	
	
}
