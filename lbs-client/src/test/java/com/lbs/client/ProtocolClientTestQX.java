package com.lbs.client;

import com.lbs.nettyclient.bizconst.MediaTypeConstants;
import com.lbs.nettyclient.bizconst.MessageSourceConstants;
import com.lbs.nettyclient.protocol.LbsMessageDispatcher;
import com.lbs.nettyclient.protocol.LbsMessageQueue;
import com.lbs.nettyclient.protocol.ProtocolClient;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.math.BigDecimal;

/**
 * @author qiux
 * @Created on 18/2/2.
 */
public class ProtocolClientTestQX {

    public static void main(String[] args) {
        ProtocolClient client = ProtocolClient.getInstance();
        try{

            boolean conResult = client.connect("127.0.0.1", 8191);
            if(conResult){
                JSONObject data = new JSONObject();
                data.put("loginId","f4c7fb0fb4854d2dbf7c18e50858853d");
                data.put("userId","9b84953da3eb4d8e8488d1d63b2d5b80");

                JSONObject data2 = new JSONObject();
                data2.put("loginId","40e0908c4aad445eb1207ca4e3a15c7e");
                data2.put("userId","a4344e3acc914cfb8636ffd43be2ff24");
                client.openBizChannel(data,new ClientHanldeInMsg());

                client.openBizChannel(data2,new ClientHanldeInMsg());

                LbsMessageQueue.getInstance().pushOutBizMsg("xxx");

                client.closeBizChannel();

                //启动线程发送业务消息

                new Thread(new Runnable() {

                    public void run() {
//                        System.out.println("test flag "+LbsMessageDispatcher.isOpen);
                        int count = 0;
                        JSONArray useridarr = new JSONArray();
                        while (true){
                            if(LbsMessageDispatcher.isOpen){
                                //获取业务消息
                                JSONObject msgbody = new JSONObject();
                                JSONObject messageContent = new JSONObject();
                                JSONObject from2 = new JSONObject();
                                JSONObject toInfo = new JSONObject();
                                from2.put("loginId","f4c7fb0fb4854d2dbf7c18e50858853d");
                                from2.put("userId","9b84953da3eb4d8e8488d1d63b2d5b80");
                                from2.put("jd",new BigDecimal(102.78949).doubleValue());
                                from2.put("wd",new BigDecimal(24.987175).doubleValue());
                                from2.put("locationName","location address");
                                from2.put("nickName","testUser2");
                                msgbody.put("msgSource", MessageSourceConstants.MSG_COMMON.PRIVATE_LETTER);
                                useridarr.add("a4344e3acc914cfb8636ffd43be2ff24");
                                toInfo.put("userId",useridarr);
                                toInfo.put("specailId","");


                                messageContent.put("type", MediaTypeConstants.TXT);
                                messageContent.put("txt", "this is test text .count="+System.currentTimeMillis());
                                msgbody.put("msgContent", messageContent);
                                msgbody.put("fromInfo", from2);
                                msgbody.put("toInfo", toInfo);
                                LbsMessageQueue.getInstance().pushOutBizMsg(msgbody);
                                count++;
                                if(count >=1){
                                    System.out.println("end push message");

                                    break;
                                }
                            }
                            try {
                                Thread.sleep(5*1000);
                            }catch (Exception e){

                            }
                        }
                    }
                }).start();
            }
            System.out.println("end.");
        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
