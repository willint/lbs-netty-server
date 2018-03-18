package com.lbs.nettyserver.model.pojo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 用户意见反馈实体类
 * @author visual
 *
 */
@Entity
@Table(name="t_user_suggest")
public class UserSuggest {

	/**
	 * 主键
	 */
	@Id
    @Column(name = "tus_id")
	private String tusId;
	
	/**
	 * 意见反馈类型码
	 */
	@Column(name = "type_code")
	private String typeCode;
	
	/**
	 * 文本描述
	 */
	@Column(name = "describe_text")
	private String describeText;
	
	/**
	 * 图片列表
	 */
	@Column(name = "image_list")
	private String imageList;
	
	/**
	 * 反馈的用户id
	 */
	@Column(name = "user_id")
	private String userId;
	
	/**
	 * 反馈时间
	 */
	@Column(name = "suggest_time")
	private Date suggestTime;
	
	/**
	 * 处理状态码
	 */
	@Column(name = "handle_state")
	private String handleState;

	public String getTusId() {
		return tusId;
	}

	public void setTusId(String tusId) {
		this.tusId = tusId;
	}

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

	public String getImageList() {
		return imageList;
	}

	public void setImageList(String imageList) {
		this.imageList = imageList;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Date getSuggestTime() {
		return suggestTime;
	}

	public void setSuggestTime(Date suggestTime) {
		this.suggestTime = suggestTime;
	}

	public String getHandleState() {
		return handleState;
	}

	public void setHandleState(String handleState) {
		this.handleState = handleState;
	}
	
}
