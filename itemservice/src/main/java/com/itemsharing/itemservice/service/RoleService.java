package com.itemsharing.itemservice.service;

import com.itemsharing.itemservice.model.Role;

public interface RoleService {

    Role createRoleByName(String name);
    Role getRoleByName(String name);
}
