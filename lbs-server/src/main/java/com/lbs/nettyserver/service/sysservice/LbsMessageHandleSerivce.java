package com.lbs.nettyserver.service.sysservice;

import com.lbs.nettyserver.dao.sys.LbsMessageDAO;
import com.lbs.nettyserver.model.common.ToInfo;
import com.lbs.nettyserver.model.sys.MsgConsumer;
import com.lbs.nettyserver.service.functionsevice.common.PrivateMessageService;
import io.netty.channel.ChannelHandlerContext;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lbs.nettyserver.dao.LoginUserDAO;
import com.lbs.nettyserver.dispatcher.HandlerDispatcher;
import com.lbs.nettyserver.model.common.FromInfo;
import com.lbs.nettyserver.model.pojo.LoginUser;
import com.lbs.nettyserver.model.sys.MessageSourceConstants;
import com.lbs.nettyserver.protocol.LbsMessage;
import com.lbs.nettyserver.protocol.LbsMessageConst;
import com.lbs.nettyserver.protocol.LbsMessageService;
import com.lbs.nettyserver.service.functionsevice.vomit.VomitMessagePushService;
import com.lbs.nettyserver.utils.sysutils.ByteUtil;
import com.lbs.nettyserver.utils.sysutils.JsonPluginsUtil;

/**
 *
 * Created by will on 18/1/2.
 */
@Service("lbsMessageHandleSerivce")
public class LbsMessageHandleSerivce implements LbsMessageService {


    @Autowired
    private HandlerDispatcher handlerDispatcher;

    @Autowired
    private VomitMessagePushService vomitMessagePushService;

    @Autowired
    private PrivateMessageService privateMessageService;

    @Autowired
    private LoginUserDAO loginUserDao;

    @Autowired
    private LbsMessageDAO lbsMessageDAO;

    private static final Log logger = LogFactory.getLog(LbsMessageHandleSerivce.class);

    @Override
    public void createConnectChannel(LbsMessage message,ChannelHandlerContext ctx) throws Exception{

        //获取body内容解析成json 例如
        /**
         * {"loginId":"1212","userId":"3333}
         */
        LbsMessage respMessage = new LbsMessage();
        respMessage.setVerCode(LbsMessageConst.VER_CODE);
        respMessage.setPriority(LbsMessageConst.DEFAULT_PRIORITY);
        respMessage.setType(LbsMessageConst.API_TYPE.CONNECT_RESPONSE);
        JSONObject responseJson = new JSONObject();
        logger.info("createConnectChannel "+message.getBody());

        JSONObject json = JSONObject.fromObject(message.getBody());
        String loginId = json.getString("loginId");
        String userId = json.getString("userId");
        //判断用户是否已经登录
        LoginUser user = loginUserDao.getLoginUserByUserId(userId);
        if(null != user && user.getLoginId().equals(loginId)){
            //
//        	System.out.println("loginId="+loginId+"  ctx="+ctx.hashCode());
            handlerDispatcher.getUserLinkedMap().put(userId,ctx);// 用户ID 和链接绑定、不允许同一用户多个地点登录
            responseJson.put(LbsMessageConst.API_BODY_BIZ_TITLE.RESP_CODE,
                    LbsMessageConst.API_BODY.SUCCESS);
            responseJson.put(LbsMessageConst.API_BODY_BIZ_TITLE.RESP_MSG,
                    LbsMessageConst.API_BODY_BIZ_MSG.SUCCESS);
            responseJson.put("loginId", loginId);
        }else{
            //返回链接失败信息
            responseJson.put(LbsMessageConst.API_BODY_BIZ_TITLE.RESP_CODE,
                    LbsMessageConst.API_BODY.BIZ_FAIL_UNLOGIN);
            responseJson.put(LbsMessageConst.API_BODY_BIZ_TITLE.RESP_MSG,
                    LbsMessageConst.API_BODY_BIZ_MSG.FAIL_UNLOGIN);

        }
        respMessage.setBody(responseJson);

        //响应回复
        flushResponseMessage(respMessage,ctx);
    }

    @Override
    public void disconnectChannel() throws Exception{

    }

    @Override
    public void heartbeatCheck(LbsMessage message,ChannelHandlerContext ctx) throws Exception{
        //获取body内容解析成json 例如
        /**
         * {"loginId":"1212","sessionCode":"1221212","timeStamp":"YYYYMMDDhhmmss"}
         *
         */
        LbsMessage respMessage = new LbsMessage();
        respMessage.setVerCode(LbsMessageConst.VER_CODE);
        respMessage.setPriority(LbsMessageConst.DEFAULT_PRIORITY);
        respMessage.setType(LbsMessageConst.API_TYPE.HEARTBEAT_RESPONSE);
        JSONObject responseJson = new JSONObject();
        logger.info("check heartbeat"+message.getBody());
        JSONObject json = JSONObject.fromObject(message.getBody());
        String loginId = json.getString("loginId");
        String sessionCode = json.getString("sessionCode");

        //
        if(this.handlerDispatcher.getUserLinkedMap().containsKey(loginId)){

        }
        //刷新sessionCode信息


        //封装响应
        SimpleDateFormat timeFormat=new SimpleDateFormat("yyyyMMDDHHmmss");
        String timeStamp = timeFormat.format(new Date());

        responseJson.put("timeStamp",timeStamp);
        responseJson.put(LbsMessageConst.API_BODY_BIZ_TITLE.RESP_CODE,
                LbsMessageConst.API_BODY.SUCCESS);
        responseJson.put(LbsMessageConst.API_BODY_BIZ_TITLE.RESP_MSG,
                LbsMessageConst.API_BODY_BIZ_MSG.HEARTBEAT_SUCCESS);

        respMessage.setBody(responseJson);

        //响应回复
        flushResponseMessage(respMessage,ctx);
    }

    @Override
    public void broadcastMessage(LbsMessage message) throws Exception{
        JSONObject bodyJson = JSONObject.fromObject(message.getBody());
        logger.info("接收到广播消息:"+bodyJson);
        try {
        	String msgSource = bodyJson.getString("msgSource");
            List<MsgConsumer> comsumerList = new ArrayList<MsgConsumer>();
            
            // 自由聊天模式
            if(MessageSourceConstants.MSG_VOMIT.FREE_CHAT.equals(msgSource)){
            	FromInfo fromInfo = JsonPluginsUtil.jsonToBean(bodyJson.get("fromInfo").toString(),FromInfo.class);
                //存储消息
                vomitMessagePushService.storeMessage(bodyJson,fromInfo);// 保存广播消息的时候bodyJson注入了消息的msgId和发送时间
                message.setBody(bodyJson);//
                //算出推送范围
                List<MsgConsumer>  broadcastList = lbsMessageDAO.getBroadcastUser(fromInfo.getJd(),fromInfo.getWd());
                //将推送用户放入list
                if(null != broadcastList && broadcastList.size()>0){
                	for(MsgConsumer user :broadcastList){
                		comsumerList.add(user);
                	}
                }
            }else if(MessageSourceConstants.MSG_VOMIT.FREE_ATA.equals(msgSource)){
                FromInfo fromInfo = JsonPluginsUtil.jsonToBean(bodyJson.get("fromInfo").toString(),FromInfo.class);
                JSONObject toJson = JSONObject.fromObject(bodyJson.get("toInfo"));
                String [] userArr = JsonPluginsUtil.jsonToStringArray(toJson.get("userId").toString());
                ToInfo toInfo = new ToInfo();
                List<String> userIds =  new ArrayList<>();
                for(String s: userArr){
                    userIds.add(s);
                }
                toInfo.setSpecailId(toJson.getString("specailId"));
                toInfo.setUserIds(userIds);


                //存储消息
                vomitMessagePushService.storeMessage(bodyJson,fromInfo);
                //算出推送范围
                List<MsgConsumer>  broadcastList = lbsMessageDAO.getBroadcastUser(fromInfo.getJd(),fromInfo.getWd());
                //将推送用户放入list
                if(null != broadcastList && broadcastList.size()>0){
                    for(MsgConsumer user :broadcastList){
                        // 广播给@用户和自己
                        if(userIds.contains(user.getUserId())||fromInfo.getUserId().equals(user.getUserId())){
                            comsumerList.add(user);
                        }
                    }
                }

            }else if(MessageSourceConstants.MSG_COMMON.PRIVATE_LETTER.equals(msgSource)){
                // 私信消息处理
                FromInfo fromInfo = JsonPluginsUtil.jsonToBean(bodyJson.get("fromInfo").toString(),FromInfo.class);
                JSONObject toJson = JSONObject.fromObject(bodyJson.get("toInfo"));
                String [] userArr = JsonPluginsUtil.jsonToStringArray(toJson.get("userId").toString());
                privateMessageService.storeMessage(bodyJson,fromInfo);
                message.setBody(bodyJson);//
                String isBlack = bodyJson.getString("isBlack");

                MsgConsumer fromConsumer = new MsgConsumer();
                fromConsumer.setUserId(fromInfo.getUserId());
                comsumerList.add(fromConsumer);
                if(null != userArr && "0".equals(isBlack) && userArr.length>0){
                    MsgConsumer toConsumer = new MsgConsumer();
                    toConsumer.setUserId(userArr[0]);
                    comsumerList.add(toConsumer);
                }
            }else{

            }
            //封装放到消息处理队列
            Map<String, Object> msgMap = new HashMap<String, Object>();
            msgMap.put(LbsMessageConst.MSG_DISPATCH_KEY.DISPATCH_LIST, comsumerList);
            msgMap.put(LbsMessageConst.MSG_DISPATCH_KEY.DISPATCH_MSG, message);
            handlerDispatcher.addMessage(msgMap);

		} catch (Exception e) {
			logger.error(" error :"+e);
			throw e;
		}
        
    }

    private void flushResponseMessage(LbsMessage message,ChannelHandlerContext ctx) throws Exception{
        ctx.writeAndFlush(message);
    }
    
}
