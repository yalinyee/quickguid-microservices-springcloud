package com.discovery.service;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

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
