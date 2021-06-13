package com.shabarecords.farmersmodule.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.xmlpull.v1.XmlPullParserException;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.io.IOException;

/**
 * @author : Odinga David
 * @since : 5/16/21, Sun
 */

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() throws IOException, XmlPullParserException {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.shabarecords.farmersmodule.controller"))
                .paths(PathSelectors.any())

                .build().apiInfo(
                        new ApiInfoBuilder().title("Farmer & Cooperatives ")
                                .description(
                                        "")
                                .termsOfServiceUrl("")
                                .version("1.00.00").contact(new Contact("Test", "https://example.com", "https://example.com")).build()
                ).tags(new Tag("Regions", "Regions Covered By Organisation", 1),
                        new Tag("Farmers","Farmers",2),
                        new Tag("Cooperatives","Cooperatives",3));

    }

}