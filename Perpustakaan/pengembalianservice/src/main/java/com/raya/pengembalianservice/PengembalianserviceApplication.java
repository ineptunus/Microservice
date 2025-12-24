package com.raya.pengembalianservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced; // <-- 1. TAMBAHKAN IMPORT INI
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class PengembalianserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PengembalianserviceApplication.class, args);
	}

	@Bean
	@LoadBalanced // <-- 2. TAMBAHKAN ANOTASI INI, INI YANG PALING PENTING
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

}