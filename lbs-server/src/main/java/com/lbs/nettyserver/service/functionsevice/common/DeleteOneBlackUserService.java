package com.lbs.nettyserver.service.functionsevice.common;

import java.lang.reflect.Field;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lbs.nettyserver.dao.ChatBlackListDAO;
import com.lbs.nettyserver.model.request.common.DeleteOneBlackUserRequest;
import com.lbs.nettyserver.service.impl.CommonBizService;
import com.lbs.nettyserver.utils.functionutils.ResponseBodyResultUtil;

import net.sf.json.JSONObject;

/**
 * 将某一用户从用户黑名单中移除接口
 * @author visual
 *
 */
@Service("deleteOneBlackUserService")
public class DeleteOneBlackUserService implements CommonBizService {


	private static final Log logger = LogFactory.getLog(AddUserBlackListService.class);
	
	@Autowired
	private ChatBlackListDAO chatBlackListDAO;
	
	private DeleteOneBlackUserRequest deleteOneBlackUserRequest;

	@Override
	public boolean checkBizData(JSONObject data) {
		boolean resultflag = false;
		try {
			if(data != null){
				this.deleteOneBlackUserRequest = (DeleteOneBlackUserRequest)JSONObject.toBean(data, DeleteOneBlackUserRequest.class);
				resultflag = true;
				
				//非空校验
				for (Field field : this.deleteOneBlackUserRequest.getClass().getDeclaredFields()) {
					field.setAccessible(true);
					if(field.get(this.deleteOneBlackUserRequest)==null || "".equals(field.get(this.deleteOneBlackUserRequest))){
						resultflag = false;
						break;
					}
				}
			}
		} catch (Exception e) {
			logger.error("将某一用户从用户黑名单中移除接口checkBizData错误："+e.getMessage());
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
        	
        	int delRow=chatBlackListDAO.deleteOneBlackUser(this.deleteOneBlackUserRequest.getOwnUserId(), this.deleteOneBlackUserRequest.getBlackUserId());
        	
        	if(delRow>0){
        		result=responseBodyResultUtil.getSuccess_result(null, "已从黑名单中移除");
        	}else{
        		result=responseBodyResultUtil.getSuccess_result(null, "不在黑名单中");
        	}
		}
		return result;

	}
}
