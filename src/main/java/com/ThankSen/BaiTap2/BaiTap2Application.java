package com.ThankSen.BaiTap2;


import com.ThankSen.BaiTap2.Service.InterfaceService.StorageServiceImp;
import com.ThankSen.BaiTap2.Service.StorageConfigs.StorageProperties;
import com.ThankSen.BaiTap2.Service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class BaiTap2Application {

	public static void main(String[] args) {
		SpringApplication.run(BaiTap2Application.class, args);
	}
	@Bean
	CommandLineRunner init(StorageServiceImp storageServiceImp){
		return (args) -> {
			storageServiceImp.init();
		};
	}

}
