package com.itemsharing.userservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itemsharing.userservice.model.Role;
import com.itemsharing.userservice.repository.RoleRepository;
import com.itemsharing.userservice.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService{

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role createRoleByName(String name) {
        Role localRole = new Role();
        localRole.setName(name);
        return roleRepository.save(localRole);
    }

    @Override
    public Role getRoleByName(String name) {
        Iterable<Role> roles = roleRepository.findAll();
        for(Role role : roles){
            if(role.getName().equals(name)){
                return role;
            }
        }
        return createRoleByName(name);
    }
}
