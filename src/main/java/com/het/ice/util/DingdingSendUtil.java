package com.het.ice.util;

import com.het.ice.enums.DingTypeEnum;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.util.CollectionUtils;

import java.util.Iterator;

public class DingdingSendUtil {

    public static String WEBHOOK_TOKEN = "https://oapi.dingtalk.com/robot/send?access_token=7485434bab9c75f8a3b173040db5355c4e31ba555e15d532199e3f0761297fa5";

    public static void main(String args[]) throws Exception{
        /*JSONArray jsonArray = new JSONArray();
        jsonArray.add("18757169657");
        jsonArray.add("18158403937");
        jsonArray.add("18158413937");

        send(DingTypeEnum.TEXT, "测试", "#### 测试一下", jsonArray, false);*/

        System.out.println(CommonUtil.parseTime(510498339));
    }

    public static void send(DingTypeEnum typeEnum, String title, String content, JSONArray atMobiles, boolean isAtAll) {
        HttpClient httpclient = HttpClients.createDefault();

        HttpPost httppost = new HttpPost(WEBHOOK_TOKEN);
        httppost.addHeader("Content-Type", "application/json; charset=utf-8");
        JSONObject object = new JSONObject();
        object.put("msgtype", typeEnum.getCode());

        if (!CollectionUtils.isEmpty(atMobiles)) {
            if (typeEnum == DingTypeEnum.TEXT) {
                JSONObject atObject = new JSONObject();
                atObject.put("atMobiles", atMobiles);

                object.put("at", atObject);
            } else {
                Iterator iterator = atMobiles.iterator();
                while (iterator.hasNext()) {
                    content += ("@" + iterator.next());
                }
            }
        }

        JSONObject jsonObject = new JSONObject();
        if (typeEnum == DingTypeEnum.TEXT) {
            jsonObject.put("content", content);
        } else {
            jsonObject.put("title", title);
            jsonObject.put("text", content);
        }

        object.put(typeEnum.getCode(), jsonObject.toString());

        object.put("isAtAll", isAtAll);

        System.out.println(object.toString());

        StringEntity se = new StringEntity(object.toString(), "utf-8");
        httppost.setEntity(se);

        try {
            HttpResponse response = httpclient.execute(httppost);
            if (response.getStatusLine().getStatusCode()== HttpStatus.SC_OK){
                String result= EntityUtils.toString(response.getEntity(), "utf-8");
                System.out.println(result);
            }
        } catch (Exception e) {
            SmsUtil.send("18757169657", "钉钉发送异常了");
        }
    }
}
