package com.lbs.nettyserver.model.request.my;

import java.util.ArrayList;

/**
 * 意见反馈请求类
 * @author visual
 *
 */
public class MyUserSuggestRequest {

	/**
	 * 意见反馈类型码
	 */
	private String typeCode;
	
	/**
	 * 文本描述
	 */
	private String describeText;
	
	/**
	 * 图片列表
	 */
	private ArrayList<String> imageList;
	
	/**
	 * 反馈的用户id
	 */
	private String userId;

	public String getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	public String getDescribeText() {
		return describeText;
	}

	public void setDescribeText(String describeText) {
		this.describeText = describeText;
	}

	public ArrayList<String> getImageList() {
		return imageList;
	}

	public void setImageList(ArrayList<String> imageList) {
		this.imageList = imageList;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
}
