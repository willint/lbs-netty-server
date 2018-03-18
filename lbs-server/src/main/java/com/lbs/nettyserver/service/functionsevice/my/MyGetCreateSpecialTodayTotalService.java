package com.lbs.nettyserver.service.functionsevice.my;

import java.lang.reflect.Field;
import java.math.BigInteger;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lbs.nettyserver.dao.my.MyOptionDAO;
import com.lbs.nettyserver.model.request.my.MyGetCreateSpecialTodayTotalRequest;
import com.lbs.nettyserver.model.response.my.MyGetCreateSpecialTodayTotalResponse;
import com.lbs.nettyserver.service.impl.CommonBizService;
import com.lbs.nettyserver.utils.functionutils.ResponseBodyResultUtil;

import net.sf.json.JSONObject;

/**
 * 我的-获取用户今天创建的专题总数接口
 * @author visual
 *
 */
@Service("myGetCreateSpecialTodayTotalService")
public class MyGetCreateSpecialTodayTotalService implements CommonBizService {


	private static final Log logger = LogFactory.getLog(MyGetCreateSpecialTodayTotalService.class);
	
	@Autowired
	private MyOptionDAO myOptionDAO;
	
	private MyGetCreateSpecialTodayTotalRequest myGetCreateSpecialTodayTotalRequest;
	
	private MyGetCreateSpecialTodayTotalResponse myGetCreateSpecialTodayTotalResponse;

	@Override
	public boolean checkBizData(JSONObject data) {
		boolean resultflag = false;
		try {
			
			if(data != null){
				this.myGetCreateSpecialTodayTotalRequest = (MyGetCreateSpecialTodayTotalRequest)JSONObject.toBean(data, MyGetCreateSpecialTodayTotalRequest.class);
				resultflag = true;
				
				//非空校验
				for (Field field : this.myGetCreateSpecialTodayTotalRequest.getClass().getDeclaredFields()) {
					field.setAccessible(true);
					if(field.get(this.myGetCreateSpecialTodayTotalRequest)==null || "".equals(field.get(this.myGetCreateSpecialTodayTotalRequest))){
						resultflag = false;
						break;
					}
				}	
			}
		} catch (Exception e) {
			logger.error("获取用户今天创建的专题总数接口checkBizData错误："+e.getMessage());
			resultflag = false;
		}
		return resultflag;
	}

	@Override
	public JSONObject handleBiz(JSONObject data) {
		JSONObject result = new JSONObject();
		ResponseBodyResultUtil responseBodyResultUtil=new ResponseBodyResultUtil();
		result=responseBodyResultUtil.getParam_error_result("");
		this.myGetCreateSpecialTodayTotalResponse=new MyGetCreateSpecialTodayTotalResponse();
		if(checkBizData(data)){
			try{
				this.myGetCreateSpecialTodayTotalResponse.setCreateSpecialTodayTotal((BigInteger)myOptionDAO.getCreateSpecialTodayTotalByUserId(this.myGetCreateSpecialTodayTotalRequest.getUserId()));	
				result=responseBodyResultUtil.getSuccess_result(this.myGetCreateSpecialTodayTotalResponse, "获取成功");
			}catch(Exception e){
				result=responseBodyResultUtil.getSys_error_default_result();
				e.printStackTrace();
				logger.error("获取用户今天创建的专题总数接口handleBiz错误："+e.getMessage());
			}
		}
		return result;
	}
}
