package com.lbs.nettyserver.service.functionsevice.my;

import java.lang.reflect.Field;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lbs.nettyserver.dao.my.MyOptionDAO;
import com.lbs.nettyserver.model.request.my.MyGetGetZanAndPutZanTotalRequest;
import com.lbs.nettyserver.model.response.my.MyGetGetZanAndPutZanTotalResponse;
import com.lbs.nettyserver.service.impl.CommonBizService;
import com.lbs.nettyserver.utils.functionutils.ResponseBodyResultUtil;

import net.sf.json.JSONObject;

/**
 * 我的-获取我得到的赞和点出的赞的总数接口
 * @author visual
 *
 */
@Service("myGetGetZanAndPutZanTotalService")
public class MyGetGetZanAndPutZanTotalService implements CommonBizService {


	private static final Log logger = LogFactory.getLog(MyGetGetZanAndPutZanTotalService.class);
	
	@Autowired
	private MyOptionDAO myOptionDAO;
	
	private  MyGetGetZanAndPutZanTotalRequest myGetGetZanAndPutZanTotalRequest;
	

	@Override
	public boolean checkBizData(JSONObject data) {
		boolean resultflag = false;
		try {
			
			if(data != null){
				this.myGetGetZanAndPutZanTotalRequest = (MyGetGetZanAndPutZanTotalRequest)JSONObject.toBean(data, MyGetGetZanAndPutZanTotalRequest.class);
				resultflag = true;
				
				//非空校验
				for (Field field : this.myGetGetZanAndPutZanTotalRequest.getClass().getDeclaredFields()) {
					field.setAccessible(true);
					if(field.get(this.myGetGetZanAndPutZanTotalRequest)==null || "".equals(field.get(this.myGetGetZanAndPutZanTotalRequest))){
						resultflag = false;
						break;
					}
				}	
			}
		} catch (Exception e) {
			logger.error("我的-获取我得到的赞和点出的赞的总数接口checkBizData错误："+e.getMessage());
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
				MyGetGetZanAndPutZanTotalResponse myGetGetZanAndPutZanTotalResponse=myOptionDAO.getZanAndPutZanTotalByUserId(this.myGetGetZanAndPutZanTotalRequest.getUserId());
				result=responseBodyResultUtil.getSuccess_result(myGetGetZanAndPutZanTotalResponse==null?new Object():myGetGetZanAndPutZanTotalResponse, "获取成功");
			}catch(Exception e){
				result=responseBodyResultUtil.getSys_error_default_result();
				e.printStackTrace();
				logger.error("我的-获取我得到的赞和点出的赞的总数接口handleBiz错误："+e.getMessage());
			}
		}
		return result;
	}
}
