package com.doranco.snapanonyme.presentation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@ComponentScan
@EnableSwagger2
public class PresentationConfig {
    @Bean
    public Docket restApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "Health check API",
                "This API helps Checking AXA Banque Microservices status",
                "v1.0.0",
                "Terms of service",
                new Contact("Philippe SIMO", "https://www.malt.fr/profile/philippeguemkamsimo", "philippe.guemkamsimo@gmail.com"),
                "License of API",
                "API license URL",
                Collections.emptyList());
    }
}
