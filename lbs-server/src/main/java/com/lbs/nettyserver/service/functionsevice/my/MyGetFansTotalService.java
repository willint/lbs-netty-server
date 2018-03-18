package com.lbs.nettyserver.service.functionsevice.my;

import java.lang.reflect.Field;
import java.math.BigDecimal;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lbs.nettyserver.dao.my.MyOptionDAO;
import com.lbs.nettyserver.model.request.my.MyGetFansTotalRequest;
import com.lbs.nettyserver.model.response.my.MyGetFansTotalResponse;
import com.lbs.nettyserver.service.impl.CommonBizService;
import com.lbs.nettyserver.utils.functionutils.ResponseBodyResultUtil;

import net.sf.json.JSONObject;

/**
 * 我的-获取用户粉丝总数的接口
 * @author visual
 *
 */
@Service("myGetFansTotalService")
public class MyGetFansTotalService implements CommonBizService {


	private static final Log logger = LogFactory.getLog(MyGetFansTotalService.class);
	
	@Autowired
	private MyOptionDAO myOptionDAO;
	
	private  MyGetFansTotalRequest myGetFansTotalRequest;
	
	private  MyGetFansTotalResponse myGetFansTotalResponse;

	@Override
	public boolean checkBizData(JSONObject data) {
		boolean resultflag = false;
		try {
			
			if(data != null){
				this.myGetFansTotalRequest = (MyGetFansTotalRequest)JSONObject.toBean(data, MyGetFansTotalRequest.class);
				resultflag = true;
				
				//非空校验
				for (Field field : this.myGetFansTotalRequest.getClass().getDeclaredFields()) {
					field.setAccessible(true);
					if(field.get(this.myGetFansTotalRequest)==null || "".equals(field.get(this.myGetFansTotalRequest))){
						resultflag = false;
						break;
					}
				}	
			}
		} catch (Exception e) {
			logger.error("我的-获取用户现场粉丝总数的接口checkBizData错误："+e.getMessage());
			resultflag = false;
		}
		return resultflag;
	}

	@Override
	public JSONObject handleBiz(JSONObject data) {
		JSONObject result = new JSONObject();
		ResponseBodyResultUtil responseBodyResultUtil=new ResponseBodyResultUtil();
		result=responseBodyResultUtil.getParam_error_result("");
		this.myGetFansTotalResponse=new MyGetFansTotalResponse();
		if(checkBizData(data)){
			try{
				this.myGetFansTotalResponse.setFansTotal((BigDecimal)myOptionDAO.getFansTotalByUserId(this.myGetFansTotalRequest.getUserId()));	
				result=responseBodyResultUtil.getSuccess_result(this.myGetFansTotalResponse, "获取成功");
			}catch(Exception e){
				result=responseBodyResultUtil.getSys_error_default_result();
				e.printStackTrace();
				logger.error("我的-获取用户现场粉丝总数的接口handleBiz错误："+e.getMessage());
			}
		}
		return result;
	}
}
