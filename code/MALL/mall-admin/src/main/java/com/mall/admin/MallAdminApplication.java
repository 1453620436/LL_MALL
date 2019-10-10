package com.mall.admin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;

@SpringBootApplication
//mapper接口类的包名,程序运行的时候会自动扫描配置的mapper.xml文件（扫描带@Mapper接口）
@MapperScan({"com.mall.admin.mapper"})
public class MallAdminApplication {

	public static void main(String[] args) {
		SpringApplication.run(MallAdminApplication.class, args);
	}

}
