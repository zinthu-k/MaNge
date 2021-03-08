package com.demo.attendance.controller.employee;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/employee/home")
public class EmployeeHomeController {
	
	@GetMapping
	public String index() {
		return "/employee/home";
	}
}
