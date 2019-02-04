package com.css.vob;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class VobUserdetailsApplication {

	public static void main(String[] args) {
		SpringApplication.run(VobUserdetailsApplication.class, args);
	}
}
