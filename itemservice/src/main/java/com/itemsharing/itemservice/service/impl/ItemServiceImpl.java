package com.itemsharing.itemservice.service.impl;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Service;

import com.itemsharing.itemservice.client.UserserviceFeignClient;
import com.itemsharing.itemservice.model.Item;
import com.itemsharing.itemservice.model.User;
import com.itemsharing.itemservice.repository.ItemRepository;
import com.itemsharing.itemservice.repository.UserRepository;
import com.itemsharing.itemservice.service.ItemService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.utils.FallbackMethod;

/**
 * @author <a href="mailto:czhang@lexon-corp.com">Chengyi Zhang</a>
 */
@Service
@EnableAutoConfiguration
public class ItemServiceImpl implements ItemService{

	private static final Logger LOG = LoggerFactory.getLogger(ItemServiceImpl.class);

	@Autowired
	private ItemRepository itemRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserserviceFeignClient userserviceFeignClient;


	@Override
	public Item addItemByUser(Item item, String username) {
		Item localItem = itemRepository.findByName(item.getName());
		if(localItem != null){
			LOG.info("Already Exist");
			return null;
		}

		Date date = new Date();
		item.setAddDate(date);
		User user = userserviceFeignClient.findUserByUsername(username);
		if(user==null){
			user = new User();
			user.setUsername(username);
			user = userRepository.save(user);
		}
		item.setUser(user);
		Item newItem = itemRepository.save(item);
		return newItem;
	}

	@Override
	public List<Item> getAllItems() {
		return (List<Item>)itemRepository.findAll();
	}

	@Override
	public List<Item> getItemsByUsername(String username) {
		User user = userserviceFeignClient.findUserByUsername(username);
		return itemRepository.findByUser(user);
	}

	@Override
	public Item getItemById(Long id) {
		return itemRepository.findOne(id);
	}

	@Override
	public Item updateItem(Item item) throws IOException {
		Item localItem = itemRepository.findByName(item.getName());
		if(localItem==null){
			throw new IOException("Not Exist!");
		}
		localItem.setDescription(item.getDescription());
		localItem.setStatus(item.getStatus());
		localItem.setName(item.getName());
		return itemRepository.save(localItem);
	}

	@Override
	public void deleteItemById(Long id) {
		itemRepository.delete(id);
	}

	@Override
	@HystrixCommand(fallbackMethod="buildFallbackUser")
	public User getUserByUsername(String username) {
		randomlyRunlong();
		return userserviceFeignClient.findUserByUsername(username);
	}

	private void randomlyRunlong(){
		Random random = new Random();
		int randomNum = random.nextInt((3-1) + 1) + 1;
		if(randomNum == 3){
			sleep();
		}
	}
	private void sleep(){
		try{
			Thread.sleep(10000);
		}catch (InterruptedException e){
			e.printStackTrace();
		}
	}

	private User buildFallbackUser(String username){
		User user = new User();
		user.setId(999999L);
		user.setUsername("Failed");
		return user;
	}
}
