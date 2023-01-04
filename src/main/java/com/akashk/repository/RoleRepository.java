package com.akashk.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.akashk.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Serializable>{

}
