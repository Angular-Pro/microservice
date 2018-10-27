package com.itemsharing.itemservice.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import com.itemsharing.itemservice.model.Item;
import com.itemsharing.itemservice.model.User;

/**
 * @author <a href="mailto:czhang@lexon-corp.com">Chengyi Zhang</a>
 */
@Transactional
public interface ItemRepository extends CrudRepository<Item, Long> {
	Item findByName(String name);
	List<Item> findByUser(User user);
}
