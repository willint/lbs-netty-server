package com.lbs.nettyserver.model.common;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by will on 18/1/5.
 *
 */
public class ToInfo {

    private List<String> userIds;

    private String specailId;

    public List<String> getUserIds() {
        return userIds;
    }

    public void setUserIds(List<String> userIds) {
        this.userIds = userIds;
    }

    public String getSpecailId() {
        return specailId;
    }

    public void setSpecailId(String specailId) {
        this.specailId = specailId;
    }
}
