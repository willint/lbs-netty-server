package com.lbs.nettyserver.model.request.vomit;

import java.math.BigDecimal;
import java.util.Date;

import com.lbs.nettyserver.utils.sysutils.TimeUtil;

/**
 * 吐槽自由消息拉取请求类
 * @author visual
 *
 */
public class VomitPullFreeChatRequest {

	/**
	 * 登录ID
	 */
	private String loginId;
	/**
	 * 经度
	 */
	private BigDecimal jd;
	/**
	 * 纬度
	 */
	private BigDecimal wd;
	/**
	 * 吐槽接收范围
	 */
	private BigDecimal vomitRange;
	
	/**
	 * 拉取消息的开始时间点(精确到毫秒yyyy-MM-dd HH:mm:ss.SSS)
	 */
	private String startTime;
	

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public BigDecimal getJd() {
		return jd;
	}

	public void setJd(BigDecimal jd) {
		this.jd = jd;
	}

	public BigDecimal getWd() {
		return wd;
	}

	public void setWd(BigDecimal wd) {
		this.wd = wd;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		if(startTime==null || "".equals(startTime)){
			startTime=TimeUtil.dateTimeFormatToString(new Date(), "yyyy-MM-dd HH:mm:ss.SSS");
		}
		this.startTime = startTime;
	}
	
	public BigDecimal getVomitRange() {
		return vomitRange;
	}

	public void setVomitRange(BigDecimal vomitRange) {
		this.vomitRange = vomitRange;
	}
	
	

}
