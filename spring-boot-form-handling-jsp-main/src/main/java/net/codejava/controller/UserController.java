package net.codejava.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import net.codejava.HisUser;
import net.codejava.repository.UserRepository;

@Controller
public class UserController {
	
	@Autowired
	private UserRepository userrepository;
	
	@GetMapping("/viewalluser")
	public String showForm(Model model) {
		List<HisUser> userlist = userrepository.findAll();
		model.addAttribute("msg", userlist);  
		return "userlist";
	}

}
