package com.raya.peminjamanservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class PeminjamanserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PeminjamanserviceApplication.class, args);
	}

    @LoadBalanced
	@Bean
    public RestTemplate restTemplate() {
    return new RestTemplate();
    }

}
