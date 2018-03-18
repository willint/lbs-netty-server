package com.lbs.nettyserver.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import com.lbs.nettyserver.model.pojo.PrivateChat;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lbs.nettyserver.dao.common.BaseDao;
import com.lbs.nettyserver.model.request.common.PrivatePullNotInClientChatRequest;
import com.lbs.nettyserver.model.request.common.PrivatePullUserToUserChatRequest;
import com.lbs.nettyserver.model.response.common.PrivatePullChatResponse;
import com.lbs.nettyserver.utils.sysutils.TimeUtil;

/**
 * 私信数据库操作类
 * @author visual
 *
 */
@Transactional
@Repository("privateChatDAO")
public class PrivateChatDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired 
    private BaseDao baseDao;
	
	/**
	 * 删除用户和某一用户的私信
	 * @param user
	 */
	public int deleteUserToUserPrivateChat(String fromUserId,String toUserId){
		
		String hql = "Delete PrivateChat where fromUserId = :fromUserId and toUserId=:toUserId"; 
		Query query = this.sessionFactory.getCurrentSession().createQuery(hql);  
		    
		query.setParameter("fromUserId", fromUserId);
		query.setParameter("toUserId", toUserId); 
	
		return query.executeUpdate();
	}
	
	
	/**
	 * 拉取用户和用户之间的startTime值nowTime时间段内的私信消息(双向同时拉取)
	 * @param List<PrivatePullChatResponse>
	 * @return
	 */
	public List<PrivatePullChatResponse> getUserToUserPrivateChatList(PrivatePullUserToUserChatRequest privatePullUserToUserChatRequest){
		
		String sql="SELECT\r\n" + 
				"	tpc.pc_id AS \"msgId\",\r\n" + 
				"	tpc.from_user_id AS \"fromUserId\",\r\n" + 
				"	tpc.to_user_id AS \"toUserId\",\r\n" + 
				"	tpc. CONTENT AS \"chatContent\",\r\n" + 
				"	tpc.send_time AS \"sendTime\"\r\n" + 
				"FROM\r\n" + 
				"	t_private_chat tpc\r\n" + 
				"WHERE\r\n" + 
"(" + 
"		(\r\n" + 
"			tpc.from_user_id = :fromUserId\r\n" + 
"			AND tpc.to_user_id = :toUserId\r\n" + 
"		)\r\n" + 
"		OR (\r\n" + 
"			(\r\n" + 
"				tpc.from_user_id = :toUserId\r\n" + 
"				AND tpc.to_user_id = :fromUserId\r\n" + 
"			)\r\n" + 
"		)\r\n" + 
"	)\r\n" + 
""+
				"AND tpc.is_back = FALSE\r\n" + 
				"AND tpc.is_sended = TRUE\r\n" + 
				"AND tpc.send_time > TIMESTAMP '"+privatePullUserToUserChatRequest.getStartTime()+"'\r\n" + 
				"AND tpc.send_time <= TIMESTAMP '"+TimeUtil.dateTimeFormatToString(new Date(), "yyyy-MM-dd HH:mm:ss.SSS")+"'\r\n"+
				"ORDER BY\r\n" + 
				"	tpc.send_time ASC";
		
		
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("fromUserId", privatePullUserToUserChatRequest.getFromUserId());
		params.put("toUserId", privatePullUserToUserChatRequest.getToUserId());


		List<PrivatePullChatResponse> list=new ArrayList<PrivatePullChatResponse>();
		
		try {
			list=baseDao.selectNativeSqlList(sql, params, PrivatePullChatResponse.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return (list!=null && list.size()>0)?list:null;
	}
	
	/**
	 * 查询不在客户端私信列表中的用户的其他私信
	 * @param List<PrivatePullChatResponse>
	 * @return
	 */
	public List<PrivatePullChatResponse> getNotInClientPrivateChatList(PrivatePullNotInClientChatRequest privatePullNotInClientChatRequest){
		

		String clientUserIdStr="''";
		if(privatePullNotInClientChatRequest.getClientUserIdArry().length>0){
			StringBuffer sb=new StringBuffer();
		    for(int i=0;i<privatePullNotInClientChatRequest.getClientUserIdArry().length;i++){
		    	sb.append("'"+privatePullNotInClientChatRequest.getClientUserIdArry()[i]+"'");
		    	if((i+1)!=privatePullNotInClientChatRequest.getClientUserIdArry().length){
		    		sb.append(",");
		    	}
		    }
		    clientUserIdStr=sb.toString();
		}
		String sql="SELECT\r\n" + 
				"	tpc.pc_id AS \"msgId\",\r\n" + 
				"	tpc.from_user_id AS \"fromUserId\",\r\n" + 
				"	tpc.to_user_id AS \"toUserId\",\r\n" + 
				"	tpc. CONTENT AS \"chatContent\",\r\n" + 
				"	tpc.send_time AS \"sendTime\"\r\n" + 
				"FROM\r\n" + 
				"	t_private_chat tpc\r\n" + 
				"WHERE\r\n" + 
				" tpc.to_user_id = :toUserId\r\n" + 
				"AND tpc.is_back = FALSE\r\n" + 
				"AND tpc.is_sended = TRUE\r\n" + 
				"AND tpc.from_user_id NOT IN ("+clientUserIdStr+")\r\n"+
				"ORDER BY\r\n" + 
				" tpc.send_time ASC";
		
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("toUserId", privatePullNotInClientChatRequest.getToUserId());

		List<PrivatePullChatResponse> list=new ArrayList<PrivatePullChatResponse>();
		
		try {
			list=baseDao.selectNativeSqlList(sql, params, PrivatePullChatResponse.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return (list!=null && list.size()>0)?list:null;
	}


	/**
	 * 保存私信消息
	 * @param privateChat
	 * @return
	 */
	public boolean savePrivateChat(PrivateChat privateChat){
		boolean success = true;
		this.sessionFactory.getCurrentSession().save(privateChat);
		this.sessionFactory.getCurrentSession().flush();

		return success;

	}
}
