package com.zerofiltre.snapanonyme.presentation;

import com.zerofiltre.snapanonyme.presentation.security.JwtConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@ComponentScan
@EnableSwagger2
public class PresentationConfig {

    @Bean
    public JwtConfig jwtConfig() {
        return new JwtConfig();
    }

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
                "Article'anoyme API",
                "This API exposes core supplied snap'anonyme services",
                "v1.0.0",
                "Terms of services",
                new Contact("Philippe GUEMKAM SIMO", "https://www.malt.fr/profile/philippeguemkamsimo", "philippe.guemkamsimo@gmail.com"),
                "License of API",
                "API license URL",
                Collections.emptyList());
    }
}
