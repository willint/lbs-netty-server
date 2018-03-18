package com.lbs.nettyserver.model.common;

/**
 * 智囊内容结构
 * @author visual
 *
 */
public class SmartMessageContent {

	/**
	 * 智囊标题
	 */
	private String title;
	
	/**
	 * 正文内容
	 */
	private String describe;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}
	
	
}
