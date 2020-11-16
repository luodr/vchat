package net.sinlo.vchat;

import net.sinlo.vchat.util.RedisUtil;
import net.sinlo.vchat.util.SMSUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;

@SpringBootTest
@PropertySource(value = {"classpath:sms.properties",}, encoding = "utf-8")
class VchatApplicationTests {
	@Autowired
	SMSUtil smsUtil;
//	@Autowired
//	RedisUtil redisUtil;
	@Test
	void contextLoads() {
//		redisUtil.set("test","test");
//		System.err.println(redisUtil.get("test"));
	}

}
