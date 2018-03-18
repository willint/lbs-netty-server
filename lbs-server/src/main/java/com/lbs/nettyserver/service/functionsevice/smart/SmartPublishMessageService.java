package com.lbs.nettyserver.service.functionsevice.smart;

import java.lang.reflect.Field;
import java.math.BigDecimal;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lbs.nettyserver.dao.smart.SmartOptionDAO;
import com.lbs.nettyserver.model.common.SmartMessageContent;
import com.lbs.nettyserver.model.pojo.SmartMessage;
import com.lbs.nettyserver.model.request.smart.SmartPublishMessageRequest;
import com.lbs.nettyserver.model.response.smart.SmartPublishMessageResponse;
import com.lbs.nettyserver.service.impl.CommonBizService;
import com.lbs.nettyserver.utils.functionutils.ResponseBodyResultUtil;
import com.lbs.nettyserver.utils.sysutils.TimeUtil;
import com.lbs.nettyserver.utils.sysutils.UUIDUtil;

import net.sf.json.JSONObject;

/**
 * 智囊-发布智囊消息接口
 * @author visual
 *
 */
@Service("smartPublishMessageService")
public class SmartPublishMessageService implements CommonBizService {


	private static final Log logger = LogFactory.getLog(SmartPublishMessageService.class);
	
	@Autowired
	private SmartOptionDAO smartOptionDAO;
	
	private SmartPublishMessageRequest smartPublishMessageRequest;

	/**
	 * 校验消息内容是否有空存在
	 * @return
	 */
	private boolean checkContentIsNull(){
		boolean checkResult=true;
		
		SmartMessageContent lmc=this.smartPublishMessageRequest.getSmartMessageContent();
		if(lmc==null) return false;
		//标题
		if(lmc.getTitle() == null || "".equals(lmc.getTitle())){
			return checkResult=false;
		}
		//正文
		if(lmc.getDescribe() == null || "".equals(lmc.getDescribe())){
			return checkResult=false;
		}
		
		
		return checkResult;
	}
	
	
	@Override
	public boolean checkBizData(JSONObject data) {
		boolean resultflag = false;
		try {
			if(data != null){
				this.smartPublishMessageRequest = (SmartPublishMessageRequest)JSONObject.toBean(data, SmartPublishMessageRequest.class);
				resultflag = true;
				
				//非空校验
				//校验外层
				for (Field field : this.smartPublishMessageRequest.getClass().getDeclaredFields()) {
					field.setAccessible(true);
					if(field.get(this.smartPublishMessageRequest)==null || "".equals(field.get(this.smartPublishMessageRequest))){
						resultflag = false;
						break;
					}
				}
				//校验内容结构
				if(resultflag){
					resultflag=checkContentIsNull();
				}
				
			}
		} catch (Exception e) {
			logger.error("智囊-发布智囊消息接口checkBizData错误："+e.getMessage());
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
        	
        	SmartMessage smartMessage=new SmartMessage();
        	smartMessage.setSmId(UUIDUtil.getUUID());
        	smartMessage.setUserId(this.smartPublishMessageRequest.getUserId());
        	smartMessage.setJd(this.smartPublishMessageRequest.getJd());
        	smartMessage.setWd(this.smartPublishMessageRequest.getWd());
        	smartMessage.setLocationName(this.smartPublishMessageRequest.getLocationName());
        	smartMessage.setContent(JSONObject.fromObject(this.smartPublishMessageRequest.getSmartMessageContent()).toString());
        	smartMessage.setSmartScore(BigDecimal.ZERO);
        	smartMessage.setSendTime(TimeUtil.getChinaLocalDateTimeNow());
        	smartMessage.setIsDelete('0');

        	smartOptionDAO.saveSmartMessage(smartMessage);
        	
        	SmartPublishMessageResponse smartPublishMessageResponse=new SmartPublishMessageResponse();
		
			BeanUtils.copyProperties(smartMessage, smartPublishMessageResponse);
			smartPublishMessageResponse.setSendTime(TimeUtil.dateTimeFormatToString(smartMessage.getSendTime()));
			
			result=responseBodyResultUtil.getSuccess_result(smartPublishMessageResponse, "发布成功");
		}
		return result;

	}
}
