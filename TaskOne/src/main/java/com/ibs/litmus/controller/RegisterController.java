package com.ibs.litmus.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ibs.litmus.model.Person;
import com.ibs.litmus.repository.PersonRepo;


@RestController
public class RegisterController {
	@Autowired
	PersonRepo personRepo;

	@RequestMapping("/")
	public ModelAndView home(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("index");
		return mv;
	}	

	@RequestMapping("/reg")
	public ModelAndView register() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("register");
		return mv;
	}

	
	@RequestMapping("/regSubmit")
	public ModelAndView details(Person person){
		personRepo.save(person);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("index");
		return mv;
	}
	
	
	@RequestMapping("/getdetails")
	public ModelAndView getDetails(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("viewDetails");
		return mv;
	}
	

	@PostMapping("/getdetails")
	public ModelAndView getDetails(@RequestParam String uname){
		ModelAndView mv = new ModelAndView("getDetails");
		Person person = personRepo.findById(uname).orElse(null);
		mv.addObject(person);
		return mv;
	}
	
	/*
	@PostMapping("/regSubmit")
	public ModelAndView viewDetails(@RequestParam("name") String name,@RequestParam("age") int age,@RequestParam("username") String username,@RequestParam("password") String password,@RequestParam("dob") String dob,@RequestParam("gender") String gender,ModelMap modelMap){
		modelMap.put("name",name);
		modelMap.put("age",age);
		modelMap.put("username",username);
		modelMap.put("password",password);
		modelMap.put("dob",dob);
		modelMap.put("gender",gender);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("viewDetails");
		return mv;
	}
	*/
}
