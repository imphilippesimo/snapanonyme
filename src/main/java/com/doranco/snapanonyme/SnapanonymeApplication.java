package com.doranco.snapanonyme;

import com.doranco.snapanonyme.application.ApplicationConfig;
import com.doranco.snapanonyme.infrastructure.InfrastructureConfig;
import com.doranco.snapanonyme.presentation.PresentationConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackageClasses = {ApplicationConfig.class, InfrastructureConfig.class, PresentationConfig.class})
public class SnapanonymeApplication {

	public static void main(String[] args) {
		SpringApplication.run(SnapanonymeApplication.class, args);
	}

	//TODO Set database uri ,username and pwd dynamically via env var when starting the app

}

