package com.example.SPEECHEMOTIONRECOGNITIONSYSTEM.Controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.SPEECHEMOTIONRECOGNITIONSYSTEM.Model.User;
import com.example.SPEECHEMOTIONRECOGNITIONSYSTEM.Repository.UserRepo;
import com.example.SPEECHEMOTIONRECOGNITIONSYSTEM.Service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
@CrossOrigin("*")
public class FrontPageController {
	
	@Autowired
	private UserService userService;

	@Autowired
	private UserRepo userRepo;
	
	
	
	@GetMapping("/")
	public String Index(Model model, Principal principal) {
		boolean isLoggedIn = principal != null;

		model.addAttribute("isLoggedIn", isLoggedIn);

		if (isLoggedIn) {
			String email = principal.getName();
			User user = userRepo.findByEmail(email);
			model.addAttribute("user", user);
		}
		return "index";
	}
	

	
	@GetMapping("/signin")
	public String singIn(Model model) {
		return "login";
	}

	@GetMapping("/signup")
	public String singUp(Model model, Principal principal) {
		return "register";
	}
	
	@PostMapping("/saveUser")
	public String saveUser(@ModelAttribute User user, HttpSession session) {
		try {
			User existingUser = userRepo.findByEmail(user.getEmail());

			if (existingUser != null) {
				session.setAttribute("msgError", "Email address already exists. Please use a different email.");
				session.removeAttribute("msg");
				return "redirect:/signup";
			} else {
				User savedUser = userService.saveUser(user);

				if (savedUser != null) {
					session.setAttribute("msg", "Registration successful. Please sign in.");
					session.removeAttribute("msgError");
					return "redirect:/signin";
				} else {
					session.setAttribute("msgError", "Something went wrong on the server.");
					session.removeAttribute("msg");
					return "redirect:/signup";
				}
			}
		} catch (Exception e) {
			return "redirect:/errorPage";
		}
	}



}
