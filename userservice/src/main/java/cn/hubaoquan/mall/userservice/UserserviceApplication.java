package cn.hubaoquan.mall.userservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("cn.hubaoquan.mall.userservice.mapper")
public class UserserviceApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserserviceApplication.class, args);
    }
}
