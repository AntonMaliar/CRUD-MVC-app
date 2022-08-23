package app.controllers;


import javax.servlet.http.HttpServletRequest;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import app.dao.UserDAO;
import app.models.User;

@Controller
public class UserController {
	@GetMapping("/allUsers")
	public String allUser(Model model) {
		model.addAttribute("users", UserDAO.getAllUsers());
		return "AllUsers";
	}
	@GetMapping("/user/{id}")
	public String userProfile(@PathVariable("id") int id, Model model) {
		model.addAttribute("user", UserDAO.getUser(id));
		return "UserProfile";
	}
	@GetMapping("/formForNewUser")
	public String formForNewUser() {
		return "FormForNewUser";
	}
	@PostMapping("/createUser")
	public String createUser(Model model, HttpServletRequest request) {
		String name = request.getParameter("name");
		String surname = request.getParameter("surname");
		UserDAO.createUser(new User(name,surname));
		return "redirect:index.jsp";
	}
	@GetMapping("/formForUpdate/{id}")
	public String formForUpdate(@PathVariable("id") int id, Model model) {
		model.addAttribute("user", UserDAO.getUser(id));
		return "FormForUpdate";
	}
	
	@PostMapping("/updateUser")
	public String updateUser(HttpServletRequest request) {
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		String surnaem = request.getParameter("surname");
		UserDAO.updateUser(id, name, surnaem);
		return "redirect:index.jsp";
	}
	@GetMapping("/deleteUser")
	public String deleteUser(HttpServletRequest request) {
		int id = Integer.parseInt(request.getParameter("id"));
		User user = UserDAO.getUser(id);
		UserDAO.deleteUser(user);
		return "redirect:index.jsp";
	}
}







