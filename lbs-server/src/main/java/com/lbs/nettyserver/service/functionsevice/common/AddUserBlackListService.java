package com.lbs.nettyserver.service.functionsevice.common;

import java.lang.reflect.Field;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lbs.nettyserver.dao.ChatBlackListDAO;
import com.lbs.nettyserver.model.pojo.ChatBlackList;
import com.lbs.nettyserver.model.request.common.AddUserBlackListRequest;
import com.lbs.nettyserver.service.impl.CommonBizService;
import com.lbs.nettyserver.utils.functionutils.ResponseBodyResultUtil;
import com.lbs.nettyserver.utils.sysutils.TimeUtil;
import com.lbs.nettyserver.utils.sysutils.UUIDUtil;

import net.sf.json.JSONObject;

/**
 * 将某一用户添加至黑名单
 * @author visual
 *
 */
@Service("addUserBlackListService")
public class AddUserBlackListService implements CommonBizService {


	private static final Log logger = LogFactory.getLog(AddUserBlackListService.class);
	
	@Autowired
	private ChatBlackListDAO chatBlackListDAO;
	
	private AddUserBlackListRequest addUserBlackListRequest;

	@Override
	public boolean checkBizData(JSONObject data) {
		boolean resultflag = false;
		try {
			if(data != null){
				this.addUserBlackListRequest = (AddUserBlackListRequest)JSONObject.toBean(data, AddUserBlackListRequest.class);
				resultflag = true;
				
				//非空校验
				for (Field field : this.addUserBlackListRequest.getClass().getDeclaredFields()) {
					field.setAccessible(true);
					if(field.get(this.addUserBlackListRequest)==null || "".equals(field.get(this.addUserBlackListRequest))){
						resultflag = false;
						break;
					}
				}
			}
		} catch (Exception e) {
			logger.error("将某一用户添加至黑名单接口checkBizData错误："+e.getMessage());
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
        	
        	ChatBlackList chatBlackList=new ChatBlackList();
        	chatBlackList.setCbkId(UUIDUtil.getUUID());
        	chatBlackList.setOwnUserId(this.addUserBlackListRequest.getOwnUserId());
        	chatBlackList.setBlackUserId(this.addUserBlackListRequest.getBlackUserId());
        	chatBlackList.setCreateTime(TimeUtil.getChinaLocalDateTimeNow());
        	
        	chatBlackListDAO.saveBlackUser(chatBlackList);

			result=responseBodyResultUtil.getSuccess_result(null, "已添加至黑名单");
		}
		return result;

	}
}
