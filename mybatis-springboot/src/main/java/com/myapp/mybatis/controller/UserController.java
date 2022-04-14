package com.myapp.mybatis.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.myapp.mybatis.mapper.UserMapper;
import com.myapp.mybatis.model.User;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserMapper userMapper;

	@GetMapping("/{id}")
	public User getUser(@PathVariable("id") String id) {
		User user = userMapper.getUser(id);
		return user;
	}
	
	@GetMapping
	public List<User> getUserList(){
		List<User> userList = userMapper.getUserList();
		return userList;
	}
	
	@PostMapping
	public void creatUser(@RequestParam("id") String id,
						@RequestParam("name") String name,
						@RequestParam("phone") String phone,
						@RequestParam("address") String address ) {
		
		userMapper.insertUser(id, name, phone, address);
	}
	
	@PutMapping("/{id}")
	public void editUser(@PathVariable("id") String id,
						@RequestParam("name") String name,
						@RequestParam("phone") String phone,
						@RequestParam("address") String address ) {
		
		userMapper.updateUser(id, name, phone, address);
	}
	
	@DeleteMapping("/{id}")
	public void deleteUser(@PathVariable("id") String id) {
		userMapper.deleteUser(id);
	}
}





