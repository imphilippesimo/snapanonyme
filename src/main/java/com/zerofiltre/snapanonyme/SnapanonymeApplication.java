package com.zerofiltre.snapanonyme;

import com.zerofiltre.snapanonyme.application.ApplicationConfig;
import com.zerofiltre.snapanonyme.infrastructure.InfrastructureConfig;
import com.zerofiltre.snapanonyme.presentation.PresentationConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackageClasses = {ApplicationConfig.class, InfrastructureConfig.class, PresentationConfig.class})
public class SnapanonymeApplication {

	public static void main(String[] args) {
		SpringApplication.run(SnapanonymeApplication.class, args);
	}



}

