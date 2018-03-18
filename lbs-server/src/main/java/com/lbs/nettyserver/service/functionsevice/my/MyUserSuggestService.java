package com.lbs.nettyserver.service.functionsevice.my;

import java.lang.reflect.Field;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lbs.nettyserver.dao.my.MyOptionDAO;
import com.lbs.nettyserver.model.pojo.UserSuggest;
import com.lbs.nettyserver.model.request.my.MyUserSuggestRequest;
import com.lbs.nettyserver.model.sys.SuggestionHandleStateCodeConstants;
import com.lbs.nettyserver.service.impl.CommonBizService;
import com.lbs.nettyserver.utils.functionutils.ResponseBodyResultUtil;
import com.lbs.nettyserver.utils.sysutils.TimeUtil;
import com.lbs.nettyserver.utils.sysutils.UUIDUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 我的-用户意见反馈接口
 * @author visual
 *
 */
@Service("myUserSuggestService")
public class MyUserSuggestService implements CommonBizService {
	
	private static final Log logger = LogFactory.getLog(MyUserSuggestService.class);
	
	@Autowired
	private MyOptionDAO myOptionDAO;
	
	private MyUserSuggestRequest myUserSuggestRequest;
	
	@Override
	public boolean checkBizData(JSONObject data) {
		boolean resultflag = false;
		try {
			if(data != null){
				this.myUserSuggestRequest = (MyUserSuggestRequest)JSONObject.toBean(data, MyUserSuggestRequest.class);
				resultflag = true;
				//非空校验
				for (Field field : this.myUserSuggestRequest.getClass().getDeclaredFields()) {
					field.setAccessible(true);
					
					if(field.getName()=="imageList")continue;//图片可以为空
					
					if(field.get(this.myUserSuggestRequest)==null || "".equals(field.get(this.myUserSuggestRequest))){
						resultflag = false;
						break;
					}
				}
			}
		} catch (Exception e) {
			logger.error("我的-用户意见反馈接口checkBizData错误："+e.getMessage());
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
        	UserSuggest userSuggest=new UserSuggest();
			
        	userSuggest.setTusId(UUIDUtil.getUUID());
        	userSuggest.setDescribeText(this.myUserSuggestRequest.getDescribeText());
			userSuggest.setHandleState(SuggestionHandleStateCodeConstants.wait_state);
			userSuggest.setTypeCode(this.myUserSuggestRequest.getTypeCode());
			userSuggest.setSuggestTime(TimeUtil.getChinaLocalDateTimeNow());
			userSuggest.setUserId(this.myUserSuggestRequest.getUserId());
			
			if(this.myUserSuggestRequest.getImageList()!=null && this.myUserSuggestRequest.getImageList().size()>0){
				userSuggest.setImageList(JSONArray.fromObject(this.myUserSuggestRequest.getImageList()).toString());
			}
			myOptionDAO.saveUserSuggest(userSuggest);
			result=responseBodyResultUtil.getSuccess_result(null, "发送成功，我们将尽快处理");
		}
		return result;

	}

}
