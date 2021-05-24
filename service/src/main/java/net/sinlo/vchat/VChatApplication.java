package net.sinlo.vchat;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@MapperScan("net.sinlo.vchat.mapper")
@EnableScheduling
public class VChatApplication {

    public static void main(String[] args) {
        SpringApplication.run(VChatApplication.class, args);
    }



}
