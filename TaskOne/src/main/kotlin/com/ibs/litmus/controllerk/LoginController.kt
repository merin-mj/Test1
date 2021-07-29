package com.ibs.litmus.controllerk

import com.ibs.litmus.controller.RegisterController
import com.ibs.litmus.modelk.Person
import com.ibs.litmus.repositoryk.PersonRepo
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.ModelAndView

@RestController("kotlinLoginController")
class LoginController {
    var log : Logger = LoggerFactory.getLogger(LoginController::class.java)
    @Autowired
    //@Qualifier("repo")
    lateinit var personRepo : PersonRepo

    @RequestMapping("/login")
    fun getDetails() : ModelAndView{
        var mv = ModelAndView()
        mv.viewName = "login"
        return mv
    }

    @PostMapping("login")
    fun login(@RequestParam username : String,@RequestParam password : String) : ModelAndView{
        var mv = ModelAndView()
        var person : Person = personRepo.findById(username).orElse(null)
        if(person!=null){
            if(person.password == password){
                log.info("${person.name} logged in successfully")
                mv.addObject(person)
                mv.viewName = "success"
            }
            else{
                log.warn("no registered data for the username that is entered")
                mv.addObject("msg","invalid credentials,login failed")
                mv.viewName = "login"
            }
        }
        else{
            log.warn("no registered data for the username that is entered")
            mv.addObject("msg","invalid credentials,login failed")
            mv.viewName ="login"
        }
        return mv
    }
}