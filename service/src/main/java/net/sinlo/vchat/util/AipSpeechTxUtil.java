package net.sinlo.vchat.util;

import com.baidu.aip.speech.AipSpeech;
import com.tencentcloudapi.asr.v20190614.AsrClient;
import com.tencentcloudapi.asr.v20190614.models.SentenceRecognitionRequest;
import com.tencentcloudapi.asr.v20190614.models.SentenceRecognitionResponse;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
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
public class AipSpeechTxUtil {

    private static String apyKey;
    private static String  SecretKey;
    private static AipSpeech client = null;

    public    String asrWav(String path,String format){
        //采用本地语音上传方式调用
        try{

            File file = new File(path);
            FileInputStream inputFile = new FileInputStream(file);
            byte[] buffer = new byte[(int)file.length()];

            inputFile.read(buffer);
            inputFile.close();

            String encodeData = Base64.getEncoder().encodeToString(buffer);


            Credential cred = new Credential("AKID93g2dzFtZ1WliUudnfO13XOzKCW6DigS", "4x5D4h3C6j9IWXIn2tjJTVI7hFZcZ0Ff");

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
}
