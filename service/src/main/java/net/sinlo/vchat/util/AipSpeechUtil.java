package net.sinlo.vchat.util;

import com.baidu.aip.speech.AipSpeech;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource(value = {"classpath:key.properties",}, encoding = "utf-8")
@ConfigurationProperties(prefix = "baidu")
public class AipSpeechUtil {
    private    String appID=null;

    private   String apyKey=null;

    private   String SecretKey=null;


    public String getAppID() {
        return appID;
    }

    public void setAppID(String appID) {
        this.appID = appID;
    }

    public String getApyKey() {
        return apyKey;
    }

    public void setApyKey(String apyKey) {
        this.apyKey = apyKey;
    }

    public String getSecretKey() {
        return SecretKey;
    }

    public void setSecretKey(String secretKey) {
        SecretKey = secretKey;
    }

    public static AipSpeech getClient() {
        return client;
    }

    public static void setClient(AipSpeech client) {
        AipSpeechUtil.client = client;
    }

    private static AipSpeech client = null;

    public    JSONObject asrWav(String path,String format){
        if(AipSpeechUtil.client==null)client=new AipSpeech(appID,apyKey, SecretKey);
        JSONObject res = client.asr(path, format, 16000, null);
         return res;

    }
}
