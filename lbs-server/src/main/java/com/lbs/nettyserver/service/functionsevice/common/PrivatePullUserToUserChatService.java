package com.lbs.nettyserver.service.functionsevice.common;

import java.lang.reflect.Field;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lbs.nettyserver.dao.PrivateChatDAO;
import com.lbs.nettyserver.model.request.common.PrivatePullUserToUserChatRequest;
import com.lbs.nettyserver.model.response.common.PrivatePullChatResponse;
import com.lbs.nettyserver.service.impl.CommonBizService;
import com.lbs.nettyserver.utils.functionutils.ResponseBodyResultUtil;

import net.sf.json.JSONObject;

/**
 * 私信-拉取用户与用户之间的私信消息接口
 * @author visual
 *
 */
@Service("privatePullUserToUserChatService")
public class PrivatePullUserToUserChatService implements CommonBizService {


	private static final Log logger = LogFactory.getLog(PrivatePullUserToUserChatService.class);
	
	@Autowired
	private PrivateChatDAO privateChatDAO;
	
	
	private PrivatePullUserToUserChatRequest privatePullChatRequest;

	@Override
	public boolean checkBizData(JSONObject data) {
		boolean resultflag = false;
		try {
			if(data != null){
				this.privatePullChatRequest = (PrivatePullUserToUserChatRequest)JSONObject.toBean(data, PrivatePullUserToUserChatRequest.class);
				resultflag = true;
				//非空校验
				for (Field field : this.privatePullChatRequest.getClass().getDeclaredFields()) {
					field.setAccessible(true);
					if(field.getName()=="startTime"){//startTime可以为空
						continue;
					}
					
					if(field.get(this.privatePullChatRequest)==null || "".equals(field.get(this.privatePullChatRequest))){
						resultflag = false;
					}
				}	
			}
		} catch (Exception e) {
			logger.error("私信-拉取用户与用户之间的私信消息接口checkBizData错误："+e.getMessage());
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
        		
        		List<PrivatePullChatResponse> list=privateChatDAO.getUserToUserPrivateChatList(privatePullChatRequest);

				result=responseBodyResultUtil.getSuccess_result(list==null?new Object[0]:list, "消息拉取成功");
				
			}catch(Exception e){
				e.printStackTrace();
				logger.error("私信-拉取用户与用户之间的私信消息接口handleBiz错误："+e.getMessage());
				result=responseBodyResultUtil.getSys_error_default_result();
			}
		}
		return result;

	}
}
