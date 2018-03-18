package com.lbs.nettyserver.dao.sys;

import com.lbs.nettyserver.dao.common.BaseDao;
import com.lbs.nettyserver.model.sys.MsgConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author qiux
 * @Created on 18/3/12.
 * 广播消息相关计算
 */
@Transactional
@Repository("lbsMessageDAO")
public class LbsMessageDAO {

    @Autowired
    private BaseDao baseDao;

    /**
     *
     * @param jd
     * @param wd
     * @return
     */
    public List<MsgConsumer> getBroadcastUser(BigDecimal jd, BigDecimal wd){

        List<MsgConsumer> list=new ArrayList<MsgConsumer>();

        String hql = "SELECT login_id as \"loginId\", user_id as \"userId\", jd as jd, wd as wd ," +
                "round(earth_distance (ll_to_earth (:wd, :jd),ll_to_earth (lu.wd, lu.jd) ))  AS distance " +
                "from t_login_user AS lu where earth_distance(ll_to_earth(:jd,:wd),ll_to_earth(lu.jd,lu.wd))<= lu.vomit_range";

        Map<String,Object> params=new HashMap<String,Object>();
        params.put("jd", jd);
        params.put("wd",wd);

        try {
            list=baseDao.selectNativeSqlList(hql, params, MsgConsumer.class);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return (list!=null && list.size()>0)?list:null;

    }
}
