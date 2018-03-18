package com.lbs.nettyserver.service.functionsevice.common;

import com.lbs.nettyserver.dao.ChatBlackListDAO;
import com.lbs.nettyserver.dao.PrivateChatDAO;
import com.lbs.nettyserver.model.common.FromInfo;
import com.lbs.nettyserver.model.common.ToInfo;
import com.lbs.nettyserver.model.pojo.ChatBlackList;
import com.lbs.nettyserver.model.pojo.PrivateChat;
import com.lbs.nettyserver.service.impl.CommonMsgBizService;
import com.lbs.nettyserver.utils.sysutils.JsonPluginsUtil;
import com.lbs.nettyserver.utils.sysutils.TimeUtil;
import com.lbs.nettyserver.utils.sysutils.UUIDUtil;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author qiux
 * @Created on 18/3/17.
 * 私信消息处理
 */
@Service("privateMessageService")
public class PrivateMessageService implements CommonMsgBizService {

    @Autowired
    private PrivateChatDAO privateChatDAO;

    @Autowired
    private ChatBlackListDAO chatBlackListDAO;

    @Override
    public boolean storeMessage(JSONObject message, FromInfo fromInfo) {
        boolean rstFlag = false;

        JSONObject toJson = JSONObject.fromObject(message.get("toInfo"));
        String [] userArr = JsonPluginsUtil.jsonToStringArray(toJson.get("userId").toString());
        String pcId = UUIDUtil.getUUID();
        Date sendTime = new Date();
        message.put("msgId",pcId);
        message.put("sendTime", TimeUtil.dateTimeFormatToString(sendTime,"yyyy-MM-dd HH:mm:ss.SSS"));
        String fromuserid = fromInfo.getUserId();
        String touserid ="" ;
        if(null != userArr && userArr.length>0){
            touserid = userArr[0];
        }

        // 判断发送用户是否在接收用户黑名单中
        ChatBlackList chatBlackList = chatBlackListDAO.getOneBlackUser(touserid,fromuserid);
        if(null == chatBlackList){
            message.put("isBlack","0");
        }else{
            message.put("isBlack","1");
        }

        PrivateChat privateChat = new PrivateChat();
        privateChat.setPcId(pcId);
        privateChat.setFromUserId(fromuserid);
        privateChat.setToUserId(touserid);
        privateChat.setChatContent(message.toString());
        privateChat.setBack(false);//默认没有撤销
        privateChat.setSended(true);//默认发送
        privateChat.setSendJd(fromInfo.getJd());
        privateChat.setSendWd(fromInfo.getWd());
        privateChat.setSendTime(sendTime);

        rstFlag = privateChatDAO.savePrivateChat(privateChat);
        return rstFlag;
    }

    @Override
    public boolean pushMessage(List<String> consumerList) {
        return false;
    }
}
