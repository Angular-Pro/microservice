package com.itemsharing.itemservice.service.impl;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itemsharing.itemservice.model.Item;
import com.itemsharing.itemservice.model.User;
import com.itemsharing.itemservice.repository.ItemRepository;
import com.itemsharing.itemservice.repository.UserRepository;
import com.itemsharing.itemservice.service.ItemService;
import com.itemsharing.itemservice.service.UserService;

/**
 * @author <a href="mailto:czhang@lexon-corp.com">Chengyi Zhang</a>
 */
@Service
public class ItemServiceImpl implements ItemService{

	private static final Logger LOG = LoggerFactory.getLogger(ItemServiceImpl.class);

	@Autowired
	private UserService userService;

	@Autowired
	private ItemRepository itemRepository;

	@Autowired
	private UserRepository userRepository;


	@Override
	public Item addItemByUser(Item item, String username) {
		Item localItem = itemRepository.findByName(item.getName());
		if(localItem != null){
			LOG.info("Already Exist");
			return null;
		}

		Date date = new Date();
		item.setAddDate(date);
		User user = userService.findUserByUsername(username);
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
		User user = userService.findUserByUsername(username);
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
	public User getUserByUsername(String username) {
		return userService.findUserByUsername(username);
	}
}
