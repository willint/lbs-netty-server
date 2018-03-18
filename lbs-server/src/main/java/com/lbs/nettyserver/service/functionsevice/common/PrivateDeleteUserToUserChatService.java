package com.lbs.nettyserver.service.functionsevice.common;

import java.lang.reflect.Field;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lbs.nettyserver.dao.PrivateChatDAO;
import com.lbs.nettyserver.model.request.common.PrivateDeleteUserToUserChatRequest;
import com.lbs.nettyserver.service.impl.CommonBizService;
import com.lbs.nettyserver.utils.functionutils.ResponseBodyResultUtil;

import net.sf.json.JSONObject;

/**
 * 私信-删除用户与用户之间的私信消息接口
 * @author visual
 *
 */
@Service("privateDeleteUserToUserChatService")
public class PrivateDeleteUserToUserChatService implements CommonBizService {


	private static final Log logger = LogFactory.getLog(PrivateDeleteUserToUserChatService.class);
	
	@Autowired
	private PrivateChatDAO privateChatDAO;
	
	
	private PrivateDeleteUserToUserChatRequest privateDeleteUserToUserChatRequest;

	@Override
	public boolean checkBizData(JSONObject data) {
		boolean resultflag = false;
		try {
			if(data != null){
				this.privateDeleteUserToUserChatRequest = (PrivateDeleteUserToUserChatRequest)JSONObject.toBean(data, PrivateDeleteUserToUserChatRequest.class);
				resultflag = true;
				//非空校验
				for (Field field : this.privateDeleteUserToUserChatRequest.getClass().getDeclaredFields()) {
					field.setAccessible(true);
					
					if(field.get(this.privateDeleteUserToUserChatRequest)==null || "".equals(field.get(this.privateDeleteUserToUserChatRequest))){
						resultflag = false;
					}
				}	
			}
		} catch (Exception e) {
			logger.error("私信-删除用户与用户之间的私信消息接口checkBizData错误："+e.getMessage());
			resultflag = false;
		}
		return resultflag;
	}

	@Override
	public JSONObject handleBiz(JSONObject data) {
		JSONObject result = new JSONObject();
	    ResponseBodyResultUtil responseBodyResultUtil=new ResponseBodyResultUtil();
		result=responseBodyResultUtil.getParam_error_result("");
		
        if(checkBizData(data)){
        	try{
        		
        		int delRow=privateChatDAO.deleteUserToUserPrivateChat(this.privateDeleteUserToUserChatRequest.getFromUserId(), this.privateDeleteUserToUserChatRequest.getToUserId());
				result=responseBodyResultUtil.getSuccess_result(null, "删除成功");
				
			}catch(Exception e){
				e.printStackTrace();
				logger.error("私信-删除用户与用户之间的私信消息接口handleBiz错误："+e.getMessage());
				result=responseBodyResultUtil.getSys_error_default_result();
			}
		}
		return result;

	}
}
