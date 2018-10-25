package com.itemsharing.userservice.service.impl;

import com.itemsharing.userservice.Utility.SecurityUtility;
import com.itemsharing.userservice.model.Role;
import com.itemsharing.userservice.model.User;
import com.itemsharing.userservice.model.UserRole;
import com.itemsharing.userservice.repository.UserRepository;
import com.itemsharing.userservice.service.RoleService;
import com.itemsharing.userservice.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger LOG = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleService roleService;

    @Override
    public User createUser(User user) {
        User localUser = userRepository.findByUsername(user.getUsername());
        if(localUser!=null){
            LOG.info("User is already existing");
        }else{
            Set<UserRole> userRoles = new HashSet<>();
            Role localRole = roleService.getRoleByName("ROLE_USER");
            userRoles.add(new UserRole(user, localRole));
            user.getUserRoles().addAll(userRoles);
            user.setJoinDate(new Date());

            String encryptedPassword = SecurityUtility.passwordEncoder().encode(user.getPassword());
            user.setPassword(encryptedPassword);
            localUser=userRepository.save(user);
        }
        return localUser;
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}