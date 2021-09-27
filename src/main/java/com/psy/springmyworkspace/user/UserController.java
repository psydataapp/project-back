package com.psy.springmyworkspace.user;

import java.util.List;
import java.util.Optional;

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
	
	@PostMapping(value ="/users/register")
	public User signUpUser(@RequestBody User user, HttpServletResponse res) {
		if(user.getNickname() == null || user.getNickname().equals("")) {
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}
		if(user.getUserId() == null || user.getUserId().equals("")) {
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}
		if(user.getPassword() == null || user.getPassword().equals("")) {
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}
		return repo.save(user);
	}		
	@PostMapping(value="/users/login")
	public Optional<User> loginUser(@RequestBody User user, HttpServletResponse res) {
		
		return repo.findByUserId(user.getUserId());
	}
}
	