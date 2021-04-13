package net.sinlo.vchat;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;




@SpringBootApplication
@MapperScan("net.sinlo.vchat.mapper")
public class VChatApplication {

	public static void main(String[] args) {
		SpringApplication.run(VChatApplication.class, args);
	}

}
