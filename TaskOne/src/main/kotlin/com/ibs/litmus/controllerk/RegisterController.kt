package com.ibs.litmus.controllerk

import com.ibs.litmus.controller.RegisterController
import com.ibs.litmus.modelk.Person
import com.ibs.litmus.myexceptions.AgeException
import com.ibs.litmus.myexceptions.PasswordException
import com.ibs.litmus.myexceptions.UsernameException
import com.ibs.litmus.repositoryk.PersonRepo
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.ModelAndView

@RestController("kotlinRegController")
class RegisterController {
    var log : Logger = LoggerFactory.getLogger(RegisterController::class.java)

    @Autowired
    lateinit var personRepo : PersonRepo

    @RequestMapping("/")
    fun home() : ModelAndView {
        var mv = ModelAndView()
        mv.viewName = "index"
        return mv
    }

    @RequestMapping("/reg")
    fun register() : ModelAndView{
        var mv = ModelAndView()
        //mv.setViewName("register")
        mv.viewName = "register"
        return mv
    }

    @RequestMapping("/regSubmit")
    //@RequestMapping(value:"/regSubmit",consumes:"*/*",produces:"*/*")
    fun details(person : Person) : ModelAndView {
        var mv = ModelAndView()
        log.debug("RegisterController handler method for postmapping of url /regSubmit")
        try{
        if (person.username == "") {
            log.warn("user haven't entered aa username-violates username cannot be empty criteria")
            throw UsernameException("Username field cannot be left blank")
        }
        if (person.password.length < 6) {
            log.warn("provided password is not upto stds-dont hv min length")
            throw PasswordException("Password must have atleast 6 characters")
        }
        if (person.age < 18) {
            log.warn("user doesn't meets min age criteria, entered ${person.age}")
            throw AgeException("The person must be at least 18 years old")
        }
        if (person.age > 100) {
            throw AgeException("The person must be at most 100 years old")
        }
        if (personRepo.findById(person.username).orElse(null) != null) {
            log.warn("username entered violates unique username criteria,entered username ${person.username} already exists")
            throw UsernameException("Username is already taken")
        }
            log.debug("saving details to db for ${person.name} with username ${person.username} ")
        personRepo.save(person)
            log.info("details saved to db")
        mv.viewName = "index"
        }
        catch(e : AgeException ){
            println("exception occurred: ${e.message}")
        }
        catch(e : UsernameException ){
            println("exception occurred: ${e.message}")
            mv.viewName ="register"

        }
//        catch(e : PasswordException ){
//            println("exception occurred: ${e.message}")
//        }
        return mv
    }

    @RequestMapping("/getdetails")
    fun getDetails() : ModelAndView{
        var mv = ModelAndView()
        mv.viewName = "viewDetails"
        return mv
    }
    @PostMapping("/getdetails")
    fun getDetails(@RequestParam uname : String ) : ModelAndView{
        log.debug("RegisterController handler method for postmapping of url /getdetails")
        var person : Person
        var mv = ModelAndView()
        var personList : List<Person> = personRepo.findAll() as List<Person>
        var matchingPerson = personList
            .stream()
            .filter{p -> p.username == uname }
            .findFirst()
        log.debug("searching in list fetched from db with entered username $uname")
        if(matchingPerson.isPresent){
            person = matchingPerson.get()
            log.info("person with username ${person.username} is found and details are returned")
            mv.addObject(person)
            mv.viewName = "getDetails"
        }
        else{
            log.info("searched username $uname is not found in db")
            mv.addObject("msg","No details found")
            mv.viewName = "viewDetails"
        }
        return mv
    }
}