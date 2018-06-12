package com.config.service;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.config.server.EnableConfigServer;

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
