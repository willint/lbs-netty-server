package com.lbs.nettyserver.service.functionsevice.common;

import java.lang.reflect.Field;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lbs.nettyserver.dao.PrivateChatDAO;
import com.lbs.nettyserver.model.request.common.PrivatePullNotInClientChatRequest;
import com.lbs.nettyserver.model.response.common.PrivatePullChatResponse;
import com.lbs.nettyserver.service.impl.CommonBizService;
import com.lbs.nettyserver.utils.functionutils.ResponseBodyResultUtil;

import net.sf.json.JSONObject;

/**
 * 私信-拉取不在客户端私信列表中的用户的其他私信请求类
 * @author visual
 *
 */
@Service("privatePullNotInClientChatService")
public class PrivatePullNotInClientChatService implements CommonBizService {


	private static final Log logger = LogFactory.getLog(PrivatePullNotInClientChatService.class);
	
	@Autowired
	private PrivateChatDAO privateChatDAO;
	
	
	private PrivatePullNotInClientChatRequest privatePullNotInClientChatRequest;

	@Override
	public boolean checkBizData(JSONObject data) {
		boolean resultflag = false;
		try {
			if(data != null){
				this.privatePullNotInClientChatRequest = (PrivatePullNotInClientChatRequest)JSONObject.toBean(data, PrivatePullNotInClientChatRequest.class);
				resultflag = true;
				//非空校验
				for (Field field : this.privatePullNotInClientChatRequest.getClass().getDeclaredFields()) {
					field.setAccessible(true);
					if(field.getName()=="clientUserIdArry"){//用户列表可以为空
						continue;
					}
					
					if(field.get(this.privatePullNotInClientChatRequest)==null || "".equals(field.get(this.privatePullNotInClientChatRequest))){
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
        		
        		List<PrivatePullChatResponse> list=privateChatDAO.getNotInClientPrivateChatList(this.privatePullNotInClientChatRequest);

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
