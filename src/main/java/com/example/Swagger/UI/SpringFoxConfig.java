package com.example.Swagger.UI;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
@Import(BeanValidatorPluginsConfiguration.class)
public class SpringFoxConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }
   /* @Bean
    public Docket filterAPI() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.deft.swagger"))
                .paths(PathSelectors.ant("/product/*"))
                .build()
                .apiInfo(apiInfo());
    } */
    private ApiInfo apiInfo() {
        return new ApiInfo(
                "Swagger API DEMO",
                "Some custom description of API.",
                "API TOS",
                "Terms of service",
                new Contact("Deft", "https://shareprogramming.net//", "huynhgiao1912@gmail.com"),
                "License of API", "API license URL", Collections.emptyList());
    }
}
