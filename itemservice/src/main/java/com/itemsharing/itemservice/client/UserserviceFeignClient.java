package com.itemsharing.itemservice.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.itemsharing.itemservice.model.User;

/**
 * @author <a href="mailto:czhang@lexon-corp.com">Chengyi Zhang</a>
 */
@FeignClient(name = "USERSERVICE")
@Service
public interface UserserviceFeignClient {

		@RequestMapping(value = "/v1/user/{username}", consumes = "application/json")
	User findUserByUsername(@PathVariable("username") String username);
}
