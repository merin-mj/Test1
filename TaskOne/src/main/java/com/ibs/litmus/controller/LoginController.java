package com.ibs.litmus.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ibs.litmus.model.Person;
import com.ibs.litmus.repository.PersonRepo;

@RestController
public class LoginController {
	
	@Autowired
	PersonRepo personRepo;

	@RequestMapping("/login")
	public ModelAndView getDetails(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("login");
		return mv;
	}
	
	@PostMapping("/login")
	public ModelAndView login(@RequestParam String username,@RequestParam String password) {
		ModelAndView mv = new ModelAndView();
		Person person = personRepo.findById(username).orElse(null);
		if(person!=null) {
			if(person.getPassword().equals(password)) {
				mv.addObject(person);
				mv.setViewName("success");
			}
			else {
				mv.addObject("msg","invalid credentials,login failed");
				mv.setViewName("login");
			}
		}
		return mv;
	}

}
