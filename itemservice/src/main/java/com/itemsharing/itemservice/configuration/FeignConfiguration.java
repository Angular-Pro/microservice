package com.itemsharing.itemservice.configuration;

import feign.Logger;
import feign.Request;

import org.springframework.cloud.netflix.feign.FeignAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author <a href="mailto:czhang@lexon-corp.com">Chengyi Zhang</a>
 */
@Configuration
public class FeignConfiguration extends FeignAutoConfiguration{

	private static final int FIVE_SECONDS = 5000;

	@Bean
	public Logger.Level feignLogger() {
		return Logger.Level.FULL;
	}

	@Bean
	public Request.Options options() {
		return new Request.Options(FIVE_SECONDS, FIVE_SECONDS);
	}
}
