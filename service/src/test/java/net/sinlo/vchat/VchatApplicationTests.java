package net.sinlo.vchat;

import com.baidu.aip.speech.AipSpeech;

import com.tencentcloudapi.asr.v20190614.AsrClient;
import com.tencentcloudapi.asr.v20190614.models.SentenceRecognitionRequest;
import com.tencentcloudapi.asr.v20190614.models.SentenceRecognitionResponse;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;

import net.sinlo.vchat.util.RedisUtil;
import net.sinlo.vchat.util.SMSUtil;
import org.checkerframework.checker.units.qual.A;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Base64;


class VchatApplicationTests {

	public static final String APP_ID = "";
	public static final String API_KEY = "";
	public static final String SECRET_KEY = "";
	// 初始化一个AipSpeech
	public static AipSpeech client = new AipSpeech("", "", "");
   @Test
	void contextLoads() {

	   // 调用接口
	   JSONObject res = client.asr("D:\\fileupload\\16k.wav", "wav", 16000, null);
	   try {
		   System.out.println(res.toString(2));
	   } catch (JSONException e) {
		   e.printStackTrace();
	   }
   }
//	@Test
//void  test(){
//	//采用本地语音上传方式调用
//	try{
//
//		File file = new File("D:\\fileupload\\2021-01-11_11-38-47_1_blob.wav");
//		FileInputStream inputFile = new FileInputStream(file);
//		byte[] buffer = new byte[(int)file.length()];
//
//		inputFile.read(buffer);
//		inputFile.close();
//
//		String encodeData = Base64.getEncoder().encodeToString(buffer);
//
//
//		Credential cred = new Credential("", "");
//
//		HttpProfile httpProfile = new HttpProfile();
//		httpProfile.setEndpoint("asr.tencentcloudapi.com");
//
//		ClientProfile clientProfile = new ClientProfile();
//		clientProfile.setHttpProfile(httpProfile);
//
//		AsrClient client = new AsrClient(cred, "", clientProfile);
//
//		SentenceRecognitionRequest req = new SentenceRecognitionRequest();
//		req.setProjectId(0L);
//		req.setSubServiceType(2L);
//		req.setEngSerViceType("16k_zh");
//		req.setSourceType(1L);
//		req.setVoiceFormat("wav");
//		req.setUsrAudioKey("test");
//			req.setData(encodeData);
//		SentenceRecognitionResponse resp = client.SentenceRecognition(req);
//
//		System.out.println(SentenceRecognitionResponse.toJsonString(resp));
//	} catch (TencentCloudSDKException | FileNotFoundException e ) {
//		System.out.println(e.toString());
//	} catch (IOException e) {
//		e.printStackTrace();
//	}
//
//
//	}
}
