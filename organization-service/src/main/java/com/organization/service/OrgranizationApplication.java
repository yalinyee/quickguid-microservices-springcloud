package com.organization.service;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

import com.organization.service.model.OrganizationDto;
import com.organization.service.repository.OrganizationRepository;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 组织服务启动类
 *
 */
@SpringBootApplication
@EnableEurekaClient
@EnableSwagger2
public class OrgranizationApplication 
{
    public static void main( String[] args )
    {
        new SpringApplicationBuilder(OrgranizationApplication.class).run(args);
    }
    
    @Bean
    public Docket swaggerApi() {
     return new Docket(DocumentationType.SWAGGER_2)
      .select()
      .apis(RequestHandlerSelectors.basePackage("com.organization.service.controller"))
      .paths(PathSelectors.any())
      .build()
      .apiInfo(new ApiInfoBuilder().version("1.0").title("Organization API").description("Documentation Organization API v1.0").build());
    }
    @Bean
	OrganizationRepository repository() {
		OrganizationRepository repository = new OrganizationRepository();
		repository.add(new OrganizationDto("Microsoft", "Redmond, Washington, USA"));
		repository.add(new OrganizationDto("Oracle", "Redwood City, California, USA"));	
		return repository;
	}
	
}
