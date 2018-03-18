package com.lbs.nettyserver.service.functionsevice.my;

import java.lang.reflect.Field;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lbs.nettyserver.dao.my.MyOptionDAO;
import com.lbs.nettyserver.model.request.my.MyGetGetCaiAndPutCaiTotalRequest;
import com.lbs.nettyserver.model.response.my.MyGetGetCaiAndPutCaiTotalResponse;
import com.lbs.nettyserver.service.impl.CommonBizService;
import com.lbs.nettyserver.utils.functionutils.ResponseBodyResultUtil;

import net.sf.json.JSONObject;

/**
 * 我的-获取我得到的踩和点出的踩的总数接口
 * @author visual
 *
 */
@Service("myGetGetCaiAndPutCaiTotalService")
public class MyGetGetCaiAndPutCaiTotalService implements CommonBizService {


	private static final Log logger = LogFactory.getLog(MyGetGetCaiAndPutCaiTotalService.class);
	
	@Autowired
	private MyOptionDAO myOptionDAO;
	
	private  MyGetGetCaiAndPutCaiTotalRequest myGetGetCaiAndPutCaiTotalRequest;
	

	@Override
	public boolean checkBizData(JSONObject data) {
		boolean resultflag = false;
		try {
			
			if(data != null){
				this.myGetGetCaiAndPutCaiTotalRequest = (MyGetGetCaiAndPutCaiTotalRequest)JSONObject.toBean(data, MyGetGetCaiAndPutCaiTotalRequest.class);
				resultflag = true;
				
				//非空校验
				for (Field field : this.myGetGetCaiAndPutCaiTotalRequest.getClass().getDeclaredFields()) {
					field.setAccessible(true);
					if(field.get(this.myGetGetCaiAndPutCaiTotalRequest)==null || "".equals(field.get(this.myGetGetCaiAndPutCaiTotalRequest))){
						resultflag = false;
						break;
					}
				}	
			}
		} catch (Exception e) {
			logger.error("我的-获取我得到的踩和点出的踩的总数接口checkBizData错误："+e.getMessage());
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
				MyGetGetCaiAndPutCaiTotalResponse myGetGetCaiAndPutCaiTotalResponse=myOptionDAO.getCaiAndPutCaiTotalByUserId(this.myGetGetCaiAndPutCaiTotalRequest.getUserId());
				result=responseBodyResultUtil.getSuccess_result(myGetGetCaiAndPutCaiTotalResponse==null?new Object():myGetGetCaiAndPutCaiTotalResponse, "获取成功");
			}catch(Exception e){
				result=responseBodyResultUtil.getSys_error_default_result();
				e.printStackTrace();
				logger.error("我的-获取我得到的踩和点出的踩的总数接口handleBiz错误："+e.getMessage());
			}
		}
		return result;
	}
}
