package com.lbs.nettyserver.service.functionsevice.my;

import java.lang.reflect.Field;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lbs.nettyserver.dao.my.MyOptionDAO;
import com.lbs.nettyserver.model.request.my.MyGetGetAndPutLiveCommentTotalRequest;
import com.lbs.nettyserver.model.response.my.MyGetGetAndPutLiveCommentTotalResponse;
import com.lbs.nettyserver.service.impl.CommonBizService;
import com.lbs.nettyserver.utils.functionutils.ResponseBodyResultUtil;

import net.sf.json.JSONObject;

/**
 * 我的-获取现场我得到的评论和发表的评论的总数接口
 * @author visual
 *
 */
@Service("myGetGetAndPutLiveCommentTotalService")
public class MyGetGetAndPutLiveCommentTotalService implements CommonBizService {


	private static final Log logger = LogFactory.getLog(MyGetGetAndPutLiveCommentTotalService.class);
	
	@Autowired
	private MyOptionDAO myOptionDAO;
	
	private  MyGetGetAndPutLiveCommentTotalRequest myGetGetAndPutLiveCommentTotalRequest;
	

	@Override
	public boolean checkBizData(JSONObject data) {
		boolean resultflag = false;
		try {
			
			if(data != null){
				this.myGetGetAndPutLiveCommentTotalRequest = (MyGetGetAndPutLiveCommentTotalRequest)JSONObject.toBean(data, MyGetGetAndPutLiveCommentTotalRequest.class);
				resultflag = true;
				
				//非空校验
				for (Field field : this.myGetGetAndPutLiveCommentTotalRequest.getClass().getDeclaredFields()) {
					field.setAccessible(true);
					if(field.get(this.myGetGetAndPutLiveCommentTotalRequest)==null || "".equals(field.get(this.myGetGetAndPutLiveCommentTotalRequest))){
						resultflag = false;
						break;
					}
				}	
			}
		} catch (Exception e) {
			logger.error("我的-获取现场我得到的评论和发表的评论的总数接口checkBizData错误："+e.getMessage());
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
				MyGetGetAndPutLiveCommentTotalResponse myGetGetAndPutLiveCommentTotalResponse=myOptionDAO.getGetAndPutLiveCommentTotalByUserId(this.myGetGetAndPutLiveCommentTotalRequest.getUserId());
				result=responseBodyResultUtil.getSuccess_result(myGetGetAndPutLiveCommentTotalResponse==null?new Object():myGetGetAndPutLiveCommentTotalResponse, "获取成功");
			}catch(Exception e){
				result=responseBodyResultUtil.getSys_error_default_result();
				e.printStackTrace();
				logger.error("我的-获取现场我得到的评论和发表的评论的总数接口handleBiz错误："+e.getMessage());
			}
		}
		return result;
	}
}
