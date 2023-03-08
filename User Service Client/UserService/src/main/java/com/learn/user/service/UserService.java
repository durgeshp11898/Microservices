package com.learn.user.service;

import java.util.List;

import com.learn.user.entity.User;

public interface UserService {

	//Create
	User saveUser(User user);

	//Get All User

	List<User> getAllUsers();

	//get Single User

	User  getUser(String userId);

	void deleteUser(String userId);

	User updateUser(User user,String userId);
}
