import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;

import com.tencentcloudapi.asr.v20190614.AsrClient;
import com.tencentcloudapi.asr.v20190614.models.*;;import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Base64;

public class SentenceRecognition
{
    public static void main(String [] args) {
        //采用本地语音上传方式调用
        try{

            File file = new File("D:\\fileupload\\16k.wav");
            FileInputStream inputFile = new FileInputStream(file);
            byte[] buffer = new byte[(int)file.length()];

            inputFile.read(buffer);
            inputFile.close();

            String encodeData = Base64.getEncoder().encodeToString(buffer);


            Credential cred = new Credential("tencent.apyKey", "tencent.SecretKey");

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
            req.setVoiceFormat("wav");
            req.setUsrAudioKey("test");
            req.setData(encodeData);
            SentenceRecognitionResponse resp = client.SentenceRecognition(req);

            System.out.println(SentenceRecognitionResponse.toJsonString(resp));
        } catch (TencentCloudSDKException | FileNotFoundException e ) {
            System.out.println(e.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


}
