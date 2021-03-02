package net.sinlo.vchat.util;

import com.baidu.aip.speech.AipSpeech;
import com.tencentcloudapi.asr.v20190614.AsrClient;
import com.tencentcloudapi.asr.v20190614.models.SentenceRecognitionRequest;
import com.tencentcloudapi.asr.v20190614.models.SentenceRecognitionResponse;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.ocr.v20181119.OcrClient;
import com.tencentcloudapi.ocr.v20181119.models.GeneralBasicOCRRequest;
import com.tencentcloudapi.ocr.v20181119.models.GeneralBasicOCRResponse;
import com.tencentcloudapi.tmt.v20180321.TmtClient;
import com.tencentcloudapi.tmt.v20180321.models.TextTranslateRequest;
import com.tencentcloudapi.tmt.v20180321.models.TextTranslateResponse;
import org.json.JSONObject;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Base64;

@Component
@PropertySource(value = {"classpath:key.properties",}, encoding = "utf-8")
@ConfigurationProperties(prefix = "tencent")
public class TencentAipUtil {

    private  String apyKey;
    private  String  SecretKey;

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
        TencentAipUtil.client = client;
    }

    private static AipSpeech client = null;

    public    String asrWav(String path,String format){
        //采用本地语音上传方式调用
        try{
            Credential cred = new Credential(apyKey, SecretKey);
            File file = new File(path);
            FileInputStream inputFile = new FileInputStream(file);
            byte[] buffer = new byte[(int)file.length()];
            inputFile.read(buffer);
            inputFile.close();
            String encodeData = Base64.getEncoder().encodeToString(buffer);

            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint("asr.tencentcloudapi.com");
            httpProfile.setProtocol("https://");
            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setDebug(true);
            clientProfile.setHttpProfile(httpProfile);
            AsrClient client = new AsrClient(cred, "", clientProfile);
            SentenceRecognitionRequest req = new SentenceRecognitionRequest();
            req.setProjectId(0L);
            req.setSubServiceType(2L);
            req.setEngSerViceType("16k_zh");
            req.setSourceType(1L);
            req.setVoiceFormat(format);
            req.setUsrAudioKey("test");
            req.setData(encodeData);
            SentenceRecognitionResponse resp = client.SentenceRecognition(req);
       return  SentenceRecognitionResponse.toJsonString(resp);
        } catch (TencentCloudSDKException | FileNotFoundException e ) {
            System.out.println(e.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  "";
    }
    public   String imgToText(String path){
        //采用本地语音上传方式调用
        try{
            Credential cred = new Credential(apyKey, SecretKey);
            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint("ocr.tencentcloudapi.com");
            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);
            OcrClient client = new OcrClient(cred, "ap-guangzhou", clientProfile);
            GeneralBasicOCRRequest req = new GeneralBasicOCRRequest();
            File file = new File(path);
            FileInputStream inputFile = new FileInputStream(file);
            byte[] buffer = new byte[(int)file.length()];
            inputFile.read(buffer);
            inputFile.close();
            String encodeData = Base64.getEncoder().encodeToString(buffer);
            req.setImageBase64(encodeData);
            GeneralBasicOCRResponse resp = client.GeneralBasicOCR(req);
            return  GeneralBasicOCRResponse.toJsonString(resp);
        } catch (TencentCloudSDKException | FileNotFoundException e ) {
            System.out.println(e.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  "";
    }
    public    String translated(String text,String source,String taeget){
        //采用本地语音上传方式调用
        try{
            Credential cred = new Credential(apyKey, SecretKey);
            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint("tmt.tencentcloudapi.com");
            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);
            TmtClient client = new TmtClient(cred, "ap-guangzhou", clientProfile);
            TextTranslateRequest req = new TextTranslateRequest();
            req.setSourceText(text);
            req.setSource(source);
            req.setTarget(taeget);
            req.setProjectId(0L);

            TextTranslateResponse resp = client.TextTranslate(req);

            return  GeneralBasicOCRResponse.toJsonString(resp);
        } catch (TencentCloudSDKException e ) {
            System.out.println(e.toString());
        }
        return  "";
    }

}
