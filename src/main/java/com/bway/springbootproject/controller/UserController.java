package com.bway.springbootproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.bway.springbootproject.model.User;
import com.bway.springbootproject.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping({"/", "/login"})
	String loginPage() {
		return "login";
	}
	
	@GetMapping("/signup")
	public String signUpPage() {
		return "signup";
	}
	
	@GetMapping("/home")
	String homePage() {
		return "home";
	}
	
	@PostMapping("/signup")
	public String registerUser(@ModelAttribute User u, Model model) {
		if(!u.getPassword().equals(u.getConfirmPassword())) {
			model.addAttribute("error", "password and confirm password should be same!");
			return "signup";
		}
		u.setPassword(DigestUtils.md5DigestAsHex(u.getPassword().getBytes()));
		userService.registerUser(u);
		model.addAttribute("msg", "User registered successfully! Please login!");
		return "login";
	}
	@PostMapping("/login")
	public String loginUser(@ModelAttribute User u, Model model, HttpSession session) {
		u.setPassword(DigestUtils.md5DigestAsHex(u.getPassword().getBytes()));
		User usr = userService.loginUser(u.getUsername(), u.getPassword());
		if(usr!=null) {
			session.setAttribute("activeUser", usr);
			session.setMaxInactiveInterval(400);
			return "redirect:/home";
		}
		
		model.addAttribute("error", "Invalid username or password!");
		
	return "login";
	}
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate(); // kill session
		return "login";
	}
	
	@GetMapping("/profile")
	public String profile() {
		return "profile";
	}
}
