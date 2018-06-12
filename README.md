# quickguid-microservices-springcloud
快速部署SpringCloud微服务
# SpringCloud快速搭建微服务



## 一、环境说明

- **Spring Boot 2.0** 作为基础环境

- 微服务之间服务发现采用**Spring Cloud Netflix Eureka** 

- 配置中心采用**Spring Cloud Config**

- Api网关使用的是**Spring Cloud GateWay**

- 日志使用**Spring Cloud Sleuth** 

- 暴露接口UI使用**Springfox-swagger-ui**

## 二、架构

![](..\images\construction.png)



## 三、快速部署



微服务间调用图

![](..\images\springcloud-openfeign.png)

### 第一步：配置中心搭建--Spring Cloud Config

- SpringCloud版本是Finchley.RC1，添加配置服务依赖包spring-cloud-config-server 

```xml
<dependencyManagement>
		<dependencies>
			<dependency>
				<groupId>org.springframework.cloud</groupId>
				<artifactId>spring-cloud-dependencies</artifactId>
				<version>Finchley.RC1</version>
				<type>pom</type>
				<scope>import</scope>
			</dependency>
		</dependencies>
	</dependencyManagement>
	<dependencies>
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-config-server</artifactId>
		</dependency>
	</dependencies>
```

- 启动类上加上注解**@EnableConfigServer**

```java
/**
 * 配置服务器
 */
@SpringBootApplication
@EnableConfigServer
public class ConfigServerApplication 
{
    public static void main( String[] args )
    {
    	 new SpringApplicationBuilder(ConfigServerApplication.class).run(args);
    }
}
```

默认配置中心会加载git 仓库中的配置，所有微服务配置文件均放置在src/main/resources/config中，YAML文件名称即为微服务的名称

### 第二步：服务发现中心搭建--discovery-service

- 添加依赖

  服务发现中心需要读取配置中心中的配置文件，需要加上SpringCloud Config客户端，在pom文件中添加spring-cloud-starter-config 依赖；服务发现还需添加spring-cloud-starter-netflix-eureka-server。

```xml
<dependencies>
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-config</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-netflix-eureka-server</artifactId>
		</dependency>
</dependencies>
```

- 启动类加上注解

```java
/**
 * 服务发现中心
 */
@SpringBootApplication
@EnableEurekaServer
public class DiscoveryApplication 
{
    public static void main( String[] args )
    {
       new SpringApplicationBuilder(DiscoveryApplication.class).run(args);
    }
}
```

### 第四步：搭建微服务（organization-service、department-service、employee-service）

- 添加依赖

添加Spring Cloud Confif客户端依赖（spring-cloud-starter-config ）、eureka客户端（spring-cloud-starter-netflix-eureka-client ）、swagger-UI（springfox-swagger2 ）以及spring boot web依赖

```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-config</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
<dependency>
    <groupId>io.springfox</groupId>
    <artifactId>springfox-swagger2</artifactId>
    <version>2.8.0</version>
</dependency>
```



organization-service微服务通过Open Feign调用department-service、employee-service服务，需要在

organization-service微服务中增加openfeign的依赖:

```xml
<dependency>
		<groupId>org.springframework.cloud</groupId>
		<artifactId>spring-cloud-starter-openfeign</artifactId>
	</dependency>
	<dependency>
		<groupId>org.springframework.cloud</groupId>
		<artifactId>spring-cloud-openfeign-core</artifactId>
	</dependency>
```


- 启动类

  organization-service:

```java
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
      .apiInfo(new ApiInfoBuilder().version("1.0").title("Organization 			  API").description("Documentation Organization API v1.0").build());
    }
}
```



employee-service:

```java
/**
 * Employee
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
				.paths(PathSelectors.any()).build().apiInfo(new ApiInfoBuilder().version("1.0").title("Employee API")
						.description("Documentation Employee API v1.0").build());
	}
}
```



### 第五步：搭建网关

- 添加依赖

```xml
<dependency>
	<groupId>org.springframework.cloud</groupId>
	<artifactId>spring-cloud-starter-gateway</artifactId>
</dependency>	
```

- 启动类配置

```java

@SpringBootApplication
@EnableDiscoveryClient
@EnableSwagger2
public class GatewayApplication {
 public static void main(String[] args) {
  SpringApplication.run(GatewayApplication.class, args);
 }
}
```

### 第六步：网关上 启用Swagger2--搭建proxy

- 添加依赖

```xml
<dependencies>
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-netflix-zuul</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-config</artifactId>
		</dependency>
		<dependency>
		    <groupId>org.springframework.cloud</groupId>
		    <artifactId>spring-cloud-starter-sleuth</artifactId>
		</dependency>
		<dependency>
			<groupId>io.springfox</groupId>
			<artifactId>springfox-swagger-ui</artifactId>
			<version>2.8.0</version>
		</dependency>
		<dependency>
			<groupId>io.springfox</groupId>
			<artifactId>springfox-swagger2</artifactId>
			<version>2.8.0</version>
		</dependency>
	</dependencies>
```



-  api配置：

```java
@Configuration
public class ProxyApi {

	@Autowired
	ZuulProperties properties;

	@Primary
	@Bean
	public SwaggerResourcesProvider swaggerResourcesProvider() {
		return () -> {
			List<SwaggerResource> resources = new ArrayList<>();
			properties.getRoutes().values().stream()
					.forEach(route -> resources.add(createResource(route.getServiceId(), route.getId(), "2.0")));
			return resources;
		};
	}

	private SwaggerResource createResource(String name, String location, String version) {
		SwaggerResource swaggerResource = new SwaggerResource();
		swaggerResource.setName(name);
		swaggerResource.setLocation("/" + location + "/v2/api-docs");
		swaggerResource.setSwaggerVersion(version);
		return swaggerResource;
	}

}
```



### 第七步：启动各个应用

访问<http://localhost:8060/swagger-ui.html>.



### 第八步：收集微服务日志

- 在微服务和网关中添加依赖：

```xml
<dependency>
	<groupId>org.springframework.cloud</groupId>
	<artifactId>spring-cloud-starter-sleuth</artifactId>
</dependency>	
```

- 在微服务和网关中日志配置：

```yaml
logging:
  pattern: 
    console: "%d{yyyy-MM-dd HH:mm:ss} ${LOG_LEVEL_PATTERN:-%5p} %m%n"
```




