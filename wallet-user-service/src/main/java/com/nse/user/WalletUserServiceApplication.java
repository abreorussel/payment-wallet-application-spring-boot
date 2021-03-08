package com.nse.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableEurekaClient
@EnableSwagger2
public class WalletUserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(WalletUserServiceApplication.class, args);
	}
	
	
	@Bean
	@LoadBalanced
	public WebClient.Builder getWebClient(){
		return WebClient.builder();
	}

}
