package com.tkzc00.kongapibackend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

/**
 * Knife4j配置类，用于生成接口文档
 *
 * @author tkzc00
 */
@Configuration
@EnableSwagger2WebMvc
@Profile({"dev", "test"})
public class Knife4jConfig {
    @Bean("defaultApi2")
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.tkzc00.kongapibackend.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * 配置接口文档信息
     *
     * @return 接口文档信息
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("API 开放平台")
                .description("API 开放平台接口文档")
                .version("1.0")
                .build();
    }
}
