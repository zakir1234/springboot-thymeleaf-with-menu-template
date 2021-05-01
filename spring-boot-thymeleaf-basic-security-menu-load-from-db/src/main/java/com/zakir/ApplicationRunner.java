package com.zakir;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.zakir.user.model.entity.AuditorAwareImpl;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
@EnableCaching
public class ApplicationRunner{
	
	public static void main(String[] args) {
		SpringApplication.run(ApplicationRunner.class, args);		
	}
	
	@Bean
	AuditorAware<String> auditorProvider() {
		return new AuditorAwareImpl();
	}
	
}
