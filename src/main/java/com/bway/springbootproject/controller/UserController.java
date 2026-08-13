package com.bway.springbootproject.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bway.springbootproject.model.User;
import com.bway.springbootproject.service.UserService;
import com.bway.springbootproject.utils.VerifyRecaptcha;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping({ "/", "/login" })
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
		if (!u.getPassword().equals(u.getConfirmPassword())) {
			model.addAttribute("error", "password and confirm password should be same!");
			return "signup";
		}
		u.setPassword(DigestUtils.md5DigestAsHex(u.getPassword().getBytes()));
		userService.registerUser(u);
		model.addAttribute("msg", "User registered successfully! Please login!");
		return "login";
	}

	@PostMapping("/login")
	public String loginUser(@ModelAttribute User u, Model model, HttpSession session,
			@RequestParam("g-recaptcha-response") String gRcode) throws IOException {
		if (VerifyRecaptcha.verify(gRcode)) {

			u.setPassword(DigestUtils.md5DigestAsHex(u.getPassword().getBytes()));
			User usr = userService.loginUser(u.getUsername(), u.getPassword());
			if (usr != null) {
				session.setAttribute("activeUser", usr);
				session.setMaxInactiveInterval(400);
				if(usr.getRole().equals("customer")) {
					return "customer-home";
				}
				log.info("----login success---"); 
				return "redirect:/home";
			} else {
				model.addAttribute("error", "Invalid username or password!");
				log.info("----login failed!---");
				return "login";
			}
		}

		model.addAttribute("error", "You are a robort!");
		log.info("----login failed!---");
		return "login";
	}

	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate(); // kill session
		log.info("---user logout ---");
		return "login";
	}

	@GetMapping("/profile")
	public String profile() {
		return "profile";
	}
}
