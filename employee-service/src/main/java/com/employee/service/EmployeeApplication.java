package com.employee.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

import com.employee.service.model.EmployeeDto;
import com.employee.service.repository.EmployeeRepository;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * EmployeeDto
 *
 */
@SpringBootApplication
@EnableEurekaClient
@EnableSwagger2
public class EmployeeApplication {
	public static void main(String[] args) {
		SpringApplication.run(EmployeeApplication.class, args);
	}

	@Bean
	public Docket swaggerApi() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.employee.service.controller"))
				.paths(PathSelectors.any()).build().apiInfo(new ApiInfoBuilder().version("1.0").title("EmployeeDto API")
						.description("Documentation EmployeeDto API v1.0").build());
	}
	@Bean
	EmployeeRepository repository() {
		EmployeeRepository repository = new EmployeeRepository();
		repository.add(new EmployeeDto(1L, 1L, "John Smith", 34, "Analyst"));
		repository.add(new EmployeeDto(1L, 1L, "Darren Hamilton", 37, "Manager"));
		repository.add(new EmployeeDto(1L, 1L, "Tom Scott", 26, "Developer"));
		repository.add(new EmployeeDto(1L, 2L, "Anna London", 39, "Analyst"));		
		repository.add(new EmployeeDto(1L, 2L, "Patrick Dempsey", 27, "Developer"));
		repository.add(new EmployeeDto(2L, 3L, "Kevin Price", 38, "Developer"));		
		return repository;
	}
}
