package com.learn.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learn.user.entity.User;

public interface UserRepository extends JpaRepository<User,String>{

}
