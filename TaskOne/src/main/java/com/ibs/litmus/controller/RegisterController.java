package com.ibs.litmus.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ibs.litmus.model.Person;
import com.ibs.litmus.myexceptions.PasswordLengthException;
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
		ModelAndView mv = new ModelAndView();
		try {
			if(person.getPassword().length()<7) {
				throw new PasswordLengthException("Password must have atleast 6 characters");
			}
			personRepo.save(person);
			mv.setViewName("index");
		}catch(PasswordLengthException e) {
			System.out.println("An exception occured "+ e.getMessage());
			mv.setViewName("register");
			mv.addObject("errorMsg",e.getMessage());
		}
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
		if(person!=null) {
			//mv.addObject("msg","retreived succesfully");
			mv.addObject(person);
		}
		else {
			mv.addObject("msg","No details found");
			mv.setViewName("viewDetails");
		}
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
