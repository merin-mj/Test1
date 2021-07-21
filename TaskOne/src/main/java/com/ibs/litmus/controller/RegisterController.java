package com.ibs.litmus.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ibs.litmus.model.Person;
import com.ibs.litmus.myexceptions.AgeException;
import com.ibs.litmus.myexceptions.PasswordException;
import com.ibs.litmus.myexceptions.UsernameException;
import com.ibs.litmus.repository.PersonRepo;



@RestController
public class RegisterController {
	Logger log = LoggerFactory.getLogger( RegisterController.class);
	
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

	@RequestMapping(value="/regSubmit",produces={"application/*","text/html"},consumes={"application/*","text/html","*/*"})
	//@RequestMapping(value="/regSubmit",produces={"application/*","text/html;charset=ISO-8859-1"},consumes={"application/*","text/html;charset=ISO-8859-1"})
	//@RequestMapping(value="/regSubmit",consumes="*/*",produces="*/*")
	@ResponseBody
	public ModelAndView details(@RequestBody Person person) throws PasswordException{
		ModelAndView mv = new ModelAndView();
		log.debug("in rcontroller,password: {}",person.getPassword());
		try {
			if(person.getUsername().equals("")) {
				log.warn("user havn't entered a username-violates username cannot be empty criteria");
				throw new UsernameException("Username field cannot be left blank");
			}
			if(person.getPassword().length()<6) {
				log.warn("provided password is not upto stds-dont hv min length");
				throw new PasswordException("Password must have atleast 6 characters");
			}
			if(person.getAge()<18) {
				log.warn("user doesnt meets min age criteria, entered {}",person.getAge());
				throw new AgeException("The person must be atleast 18 years old");
			}
			if(person.getAge()>100) {
				log.warn("user age violates age criteria, entered {}",person.getAge());
				throw new AgeException("The person must be atmost 100 years old");
			}
			if(personRepo.findById(person.getUsername()).orElse(null)!=null) {
				log.warn("username entered violates unique username criteria,entered username {} olrdy exists",person.getUsername());
				throw new UsernameException("Username is already taken");
			}
			log.debug("saving details to db for {} with username {} ",person.getName(),person.getUsername());
			personRepo.save(person);
			log.info("details saved to db");
			mv.setViewName("index");
		}
//		catch(PasswordException e) {
//			System.out.println("An exception occured:: "+ e.getMessage());
//			mv.setViewName("register");
//			mv.addObject("passwordErrorMsg",e.getMessage());
//		} 
		catch (AgeException e) {
			//e.printStackTrace();
			System.out.println("An exception occured:: "+ e.getMessage());
			mv.setViewName("register");
			mv.addObject("ageErrorMsg",e.getMessage());
		} catch (UsernameException e) {
			System.out.println("An exception occured:: "+ e.getMessage());
			mv.setViewName("register");
			mv.addObject("usernameErrorMsg",e.getMessage());
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
		Person person;
		ModelAndView mv = new ModelAndView("getDetails");
		List <Person> personList = (List<Person>) personRepo.findAll();
		//personList.forEach(x->System.out.println(x.getName()+" "+x.getUsername()+" "+x.getAge()+" "+x.getGender()+"\n"));
		Optional<Person> matchingPerson = personList
				.stream()
				.filter(p -> p.getUsername().equals(uname))
			    .findFirst(); //findAny();
		log.debug("searching in list fetched from db with entered username {}",uname);
		if(matchingPerson.isPresent()){//true if value is present
			person = matchingPerson.get(); //returns the value if present else NoSuchElementException
			//mv.addObject("msg","retreived succesfully");
			log.info("person with username {} is found and details are returned",person.getUsername());
			mv.addObject(person);
		}
		else {
			person = matchingPerson.orElse(null);
			log.info("searched username {} is not found in db",uname);
			mv.addObject("msg","No details found");
			mv.setViewName("viewDetails");
		}
		return mv;
	}
	
//	    Person person = personRepo.findById(uname).orElse(null);
//		if(person!=null) {
//			//mv.addObject("msg","retreived succesfully");
//			log.info("person with username {} is found and details are returned",person.getUsername());
//			mv.addObject(person);
//		}
//		else {
//			log.info("searched username {} is not found in db",uname);
//			mv.addObject("msg","No details found");
//			mv.setViewName("viewDetails");
//		}
//		return mv;
//	}
//	
	
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
