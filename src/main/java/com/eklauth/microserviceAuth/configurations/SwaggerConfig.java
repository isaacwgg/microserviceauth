/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eklauth.microserviceAuth.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

/**
 *
 * @author wynda
 */
@Configuration
@EnableSwagger2
@EnableWebMvc
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfo("My concept approval application",
                "swagger ui version 0.0.1",
                "1.0-SNAPSHOT",
                "Terms of service",
                new Contact("test", "www.org.com", "isaacwgakambi@gmail.com.com"),
                "License of API",
                "API license URL",
                Collections.emptyList());
    }


    // Describe your apis
//    private ApiInfo apiInfo() {
//        return new ApiInfoBuilder()
//                .title("KEKE")
//                .description("version 0.0.1")
//                .version("1.0-SNAPSHOT")
//                .build();
//    }


    // Only select apis that matches the given Predicates.
//    private Predicate<String> paths() {
//        // Match all paths except /error
////        return Predicates.and(
////                PathSelectors.regex("/.*"),
////                Predicates.not(PathSelectors.regex("/error.*"))
////        );
//        return com.google.common.base.Predicates.or(regex("/.*"));
//    }

}