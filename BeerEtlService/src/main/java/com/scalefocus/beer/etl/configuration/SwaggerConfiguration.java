package com.scalefocus.beer.etl.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {
    /**
     * Configure bean which setups Swagger instance
     *
     * @return
     */
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .useDefaultResponseMessages(false)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.scalefocus.beer.etl.rest.controller"))
                .paths(PathSelectors.any()).build();
    }

    @Bean
    ApiInfo apiInfo() {
        final var builder = new ApiInfoBuilder();

        builder.title("ETL service which migrates data from MSSQL to MongoDB")
                .version("1.0")
                .description("List of all endpoints used in API");

        return builder.build();
    }
}
