package com.psy.springmyworkspace.user;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UserController {
	private UserRepository repo;
	
	@Autowired
	public UserController(UserRepository repo) {
		this.repo = repo;
	}
	
	@GetMapping(value = "/users")
	public List<User> getUserList() {
		return repo.findAll();
	}
	
	@PostMapping(value ="/users")
	public User addUser(@RequestBody User user, HttpServletResponse res) {
		if(user.getId() == null || user.getId().equals("")) {
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}
		if(user.getPassword() == null || user.getPassword().equals("")) {
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}
		user.setCreatedTime(new Date().getTime() );
		return repo.save(user);
	}}
