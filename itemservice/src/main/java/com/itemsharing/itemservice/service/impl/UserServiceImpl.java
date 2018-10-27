package com.itemsharing.itemservice.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itemsharing.itemservice.model.User;
import com.itemsharing.itemservice.repository.UserRepository;
import com.itemsharing.itemservice.service.RoleService;
import com.itemsharing.itemservice.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger LOG = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleService roleService;

    @Override
    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}