package com.itemsharing.itemservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itemsharing.itemservice.model.Role;
import com.itemsharing.itemservice.repository.RoleRepository;
import com.itemsharing.itemservice.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

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
