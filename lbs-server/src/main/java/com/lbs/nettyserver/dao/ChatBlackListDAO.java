package com.lbs.nettyserver.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lbs.nettyserver.model.pojo.ChatBlackList;

/**
 * 黑名单数据库操作类
 * @author visual
 *
 */
@Transactional
@Repository("chatBlackListDAO")
public class ChatBlackListDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	/**
	 * 添加黑名单
	 * @param user
	 */
	public void saveBlackUser(ChatBlackList chatBlackList){
		this.sessionFactory.getCurrentSession().save(chatBlackList);
		this.sessionFactory.getCurrentSession().flush();
	}
	
	/**
	 * 从黑名单中删除某一用户
	 * @param user
	 */
	public int deleteOneBlackUser(String ownUserId,String blackUserId){
		String hql = "Delete ChatBlackList where ownUserId = :ownUserId and blackUserId=:blackUserId"; 
		Query query = this.sessionFactory.getCurrentSession().createQuery(hql);  
		    
		query.setParameter("ownUserId", ownUserId);
		query.setParameter("blackUserId", blackUserId); 
	
		return query.executeUpdate();
	}
	
	/**
	 * 删除某一用户的所有黑名单列表
	 * @param user
	 */
	public int deleteOneUserAllBlackUser(String ownUserId){
		String hql = "Delete ChatBlackList where ownUserId = :ownUserId"; 
		Query query = this.sessionFactory.getCurrentSession().createQuery(hql);  
		    
		query.setParameter("ownUserId", ownUserId);
	
		return query.executeUpdate();
	}
	
	/**
	 * 查询一个用户是否在另一个用户黑名单里面
	 * @param ownUserId
	 * @param blackUserId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public ChatBlackList getOneBlackUser(String ownUserId,String blackUserId){
		String hql = "from ChatBlackList where ownUserId = :ownUserId and blackUserId=:blackUserId"; 
		Query query = this.sessionFactory.getCurrentSession().createQuery(hql);  
		    
		query.setParameter("ownUserId", ownUserId);
		query.setParameter("blackUserId", blackUserId); 
	
		List<ChatBlackList> list = query.list();  
		this.sessionFactory.getCurrentSession().flush();  
		
		return (list!=null && list.size()>0)?list.get(0):null;
	}
	
	/**
	 * 查询某一用户的所有黑明单
	 * @param ownUserId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<ChatBlackList> getOneUserAllBlackList(String ownUserId){
		String hql = "from ChatBlackList where ownUserId = :ownUserId"; 
		Query query = this.sessionFactory.getCurrentSession().createQuery(hql);  
		    
		query.setParameter("ownUserId", ownUserId);
		
		List<ChatBlackList> list = query.list();  
		this.sessionFactory.getCurrentSession().flush();  
		
		return (list!=null && list.size()>0)?list:null;
	}
}
