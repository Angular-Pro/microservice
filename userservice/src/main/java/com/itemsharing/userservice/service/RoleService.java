package com.itemsharing.userservice.service;

import com.itemsharing.userservice.model.Role;

public interface RoleService {

    Role createRoleByName(String name);
    Role getRoleByName(String name);
}
