package com.itemsharing.userservice.repository;

import org.springframework.data.repository.CrudRepository;

import com.itemsharing.userservice.model.Role;

public interface RoleRepository extends CrudRepository<Role, Long>{

}
