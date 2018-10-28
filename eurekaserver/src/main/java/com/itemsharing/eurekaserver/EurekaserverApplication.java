package com.itemsharing.eurekaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author <a href="mailto:czhang@lexon-corp.com">Chengyi Zhang</a>
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaserverApplication {
	public static void main(String[] args){
		SpringApplication.run(EurekaserverApplication.class, args);
	}
}
