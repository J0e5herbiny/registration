package com.joe.project.repository;

import com.joe.project.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {


    public Role findRoleByName(String name);

}
