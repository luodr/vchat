import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;

import com.tencentcloudapi.asr.v20190614.AsrClient;
import com.tencentcloudapi.asr.v20190614.models.*;
import com.tencentcloudapi.ocr.v20181119.OcrClient;
import com.tencentcloudapi.ocr.v20181119.models.GeneralBasicOCRRequest;
import com.tencentcloudapi.ocr.v20181119.models.GeneralBasicOCRResponse;
import com.tencentcloudapi.tmt.v20180321.TmtClient;
import com.tencentcloudapi.tmt.v20180321.models.TextTranslateRequest;
import com.tencentcloudapi.tmt.v20180321.models.TextTranslateResponse;;import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Base64;

public class SentenceRecognition
{
    public static void main(String [] args) throws TencentCloudSDKException, IOException {
//        Credential cred = new Credential("", "");
        try{

            Credential cred = new Credential("AKID93g2dzFtZ1WliUudnfO13XOzKCW6DigS", "4x5D4h3C6j9IWXIn2tjJTVI7hFZcZ0Ff");
            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint("tmt.tencentcloudapi.com");

            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);

            TmtClient client = new TmtClient(cred, "ap-guangzhou", clientProfile);

            TextTranslateRequest req = new TextTranslateRequest();
            req.setSourceText("带翻译文档");
            req.setSource("zh");
            req.setTarget("en");
            req.setProjectId(0L);

            TextTranslateResponse resp = client.TextTranslate(req);

            System.out.println(TextTranslateResponse.toJsonString(resp));
        } catch (TencentCloudSDKException e) {
            System.out.println(e.toString());
        }

    }


}
