package com.lbs.nettyserver.service.functionsevice.vomit;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lbs.nettyserver.dao.vomit.VomitChatDAO;
import com.lbs.nettyserver.model.request.vomit.VomitPullFreeChatRequest;
import com.lbs.nettyserver.model.response.vomit.VomitPullFreeChatResponse;
import com.lbs.nettyserver.service.impl.CommonBizService;
import com.lbs.nettyserver.utils.functionutils.ResponseBodyResultUtil;

import net.sf.json.JSONObject;

/**
 * 吐槽-拉取吐槽自由聊天消息接口
 * @author visual
 *
 */
@Service("vomitPullFreeChatService")
public class VomitPullFreeChatService implements CommonBizService {


	private static final Log logger = LogFactory.getLog(VomitPullFreeChatService.class);
	
	@Autowired
	private VomitChatDAO vomitChatDAO;
	
	
	private VomitPullFreeChatRequest vomitPullFreeChatRequest;

	@Override
	public boolean checkBizData(JSONObject data) {
		boolean resultflag = false;
		try {
			if(data != null){
				this.vomitPullFreeChatRequest = (VomitPullFreeChatRequest)JSONObject.toBean(data, VomitPullFreeChatRequest.class);
				resultflag = true;
				//非空校验
				for (Field field : this.vomitPullFreeChatRequest.getClass().getDeclaredFields()) {
					field.setAccessible(true);
					if(field.getName()=="startTime"){//startTime可以为空
						continue;
					}
					
					if(field.get(this.vomitPullFreeChatRequest)==null || "".equals(field.get(this.vomitPullFreeChatRequest))){
						resultflag = false;
					}
				}	
			}
		} catch (Exception e) {
			logger.error("吐槽-拉取吐槽自由聊天消息接口checkBizData错误："+e.getMessage());
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
        		
        		List<VomitPullFreeChatResponse> list=vomitChatDAO.GetVomitFreeChat(this.vomitPullFreeChatRequest);

				result=responseBodyResultUtil.getSuccess_result(list==null?new Object[0]:list, "消息拉取成功");
				
			}catch(Exception e){
				e.printStackTrace();
				logger.error("吐槽-拉取吐槽自由聊天消息接口handleBiz错误："+e.getMessage());
				result=responseBodyResultUtil.getSys_error_default_result();
			}
		}
		return result;

	}
}
