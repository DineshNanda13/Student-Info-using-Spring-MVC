package com.studentInfo.mvc;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class EnterDetails {
	
	@RequestMapping("/showStudentForm")
	public String showStudentForm() {
		
		return "enter-details";
	}
	
	@RequestMapping("/processStudentForm")
	public String processStudentForm(HttpServletRequest request, Model model) {
		
		String theName = request.getParameter("studentName");
		theName = theName.toLowerCase();
		String result = "Hi "+theName;
		
		model.addAttribute("message",result);
		
		return "get-details";
	}

}
