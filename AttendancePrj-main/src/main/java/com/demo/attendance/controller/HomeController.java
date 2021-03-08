package com.demo.attendance.controller;


import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.demo.attendance.entity.EmployeeInfo.Role;
import com.demo.attendance.repo.EmployeeInfoRepo;

@Controller
@RequestMapping("/home")
public class HomeController {
	
	@Autowired
	private EmployeeInfoRepo repo;

	@GetMapping
	String index(HttpServletRequest req) {
		return repo.findOneByMail(req.getRemoteUser()).map(e -> {
			req.getSession(true).setAttribute("LoginUser", e);
			return e.getRole() == Role.Admin? "redirect:/admin/home" : "redirect:/employee/home";
		}).orElseThrow(()->new UsernameNotFoundException("Illegal User"));
	}
}
