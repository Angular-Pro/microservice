package com.itemsharing.itemservice.controller;

import java.io.IOError;
import java.io.IOException;
import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.itemsharing.itemservice.model.Item;
import com.itemsharing.itemservice.model.User;
import com.itemsharing.itemservice.service.ItemService;
import com.itemsharing.itemservice.service.UserService;

/**
 * @author <a href="mailto:czhang@lexon-corp.com">Chengyi Zhang</a>
 */

@RestController
@RequestMapping("/v1/item")
public class ItemController {

	@Autowired
	private ItemService itemService;

	@Autowired
	private UserService userService;

	@RequestMapping(method = RequestMethod.POST)
	public Item addItem(@RequestBody Item item){
		return itemService.addItemByUser(item, "jsmith");
	}

	@RequestMapping("/items/{username}")
	public List<Item> getItemsByUsername(@PathVariable String username){
		return itemService.getItemsByUsername(username);
	}

	@RequestMapping("/items")
	public List<Item> getAllItems(){
		return itemService.getAllItems();
	}

	@RequestMapping("/{id}")
	public Item getItemById(@PathVariable long id){
		return itemService.getItemById(id);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public Item updateItem(@PathVariable long id, @RequestBody Item item) throws IOException{
		item.setId(id);
		return itemService.updateItem(item);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void deleteItemById(@PathVariable long id) throws IOException{
		itemService.deleteItemById(id);
	}

	@RequestMapping("/user/{username}")
	public User getUserByUsername(@PathVariable String username){
		return itemService.getUserByUsername(username);
	}
}
