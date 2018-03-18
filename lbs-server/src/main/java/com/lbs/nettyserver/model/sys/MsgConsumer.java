package com.lbs.nettyserver.model.sys;

import java.math.BigDecimal;

/**
 * @author qiux
 * @Created on 18/3/11.
 *
 * 消息消费者，哪些用户需要推送
 */

public class MsgConsumer {


    private String loginId;

    private String userId;

    private BigDecimal jd;

    private BigDecimal wd;

    private Double distance;

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }
}
