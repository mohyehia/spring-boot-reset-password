package com.mohyehia.demo.dao;

import com.mohyehia.demo.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleDAO extends JpaRepository<Role, Long> {
}
