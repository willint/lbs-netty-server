package com.lbs.nettyserver.dao.vomit;

import com.lbs.nettyserver.dao.common.BaseDao;
import com.lbs.nettyserver.model.pojo.FreeChat;
import com.lbs.nettyserver.model.request.vomit.VomitPullFreeChatRequest;
import com.lbs.nettyserver.model.response.vomit.VomitPullFreeChatResponse;
import com.lbs.nettyserver.utils.sysutils.TimeUtil;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 现场消息表
 * @author visual
 *
 */
@Transactional
@Repository("vomitFreeChatDAO")
public class VomitChatDAO {

	@Autowired 
    private BaseDao baseDao;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	/**
	 * 保存吐槽自由消息
	 * @param freeChat
	 */
	public boolean saveFreeChat(FreeChat freeChat){
		boolean success = true;
		Serializable a = this.sessionFactory.getCurrentSession().save(freeChat);
		this.sessionFactory.getCurrentSession().flush();

		return success;
	}
	
	/**
	 * 拉取吐槽自由消息，按照时间从小到大排序
	 * @param pullVomitFreeChatRequest
	 * @return
	 */
	public List<VomitPullFreeChatResponse> GetVomitFreeChat(VomitPullFreeChatRequest vomitPullFreeChatRequest){
		
		String sql="SELECT\r\n" + 
				"	tfc.fc_id AS \"msgId\",\r\n" + 
				"	tfc.\"content\" AS \"chatContent\",\r\n" + 
				"	tfc.send_time AS \"sendTime\",\r\n" + 
				"	earth_distance (\r\n" + 
				"		ll_to_earth (:wd,:jd),\r\n" + 
				"		ll_to_earth (tfc.send_wd, tfc.send_jd)\r\n" + 
				"	) AS \"distance\"\r\n" + 
				"FROM\r\n" + 
				"	t_free_chat tfc\r\n" + 
				"WHERE\r\n" + 
				"	tfc.login_id <> :loginId\r\n" + 
				"AND tfc.is_back = FALSE\r\n" + 
				"AND tfc.is_sended = TRUE\r\n" + 
				"AND tfc.send_time > TIMESTAMP '"+vomitPullFreeChatRequest.getStartTime()+"'\r\n" + 
				"AND tfc.send_time <= TIMESTAMP '"+TimeUtil.dateTimeFormatToString(new Date(), "yyyy-MM-dd HH:mm:ss.SSS")+"'\r\n" + 
				"AND earth_distance (\r\n" + 
				"	ll_to_earth (:wd,:jd),\r\n" + 
				"	ll_to_earth (tfc.send_wd, tfc.send_jd)\r\n" + 
				") <= :vomitRange ORDER BY tfc.send_time ASC";
		//TimeUtil.dateTimeFormatToString(TimeUtil.getChinaLocalDateTimeNow(), "yyyy-MM-dd")
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("loginId", vomitPullFreeChatRequest.getLoginId());
		params.put("wd", vomitPullFreeChatRequest.getWd());
		params.put("jd", vomitPullFreeChatRequest.getJd());
		params.put("vomitRange", vomitPullFreeChatRequest.getVomitRange());
		

		List<VomitPullFreeChatResponse> list=new ArrayList<VomitPullFreeChatResponse>();
		
		try {
			list=baseDao.selectNativeSqlList(sql, params, VomitPullFreeChatResponse.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return (list!=null && list.size()>0)?list:null;
	}
}
