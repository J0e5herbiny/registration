package com.joe.project.repository;

import com.joe.project.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {


    public User findByUserName(String username);

    public Optional<User> findById(Long id);



}
