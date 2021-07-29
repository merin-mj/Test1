package com.ibs.litmus.controllerk

import org.mockito.Mockito.doReturn
import com.ibs.litmus.modelk.Person
import com.ibs.litmus.myexceptions.PasswordException
import com.ibs.litmus.repositoryk.PersonRepo
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Assertions.assertThrows
import org.mockito.ArgumentMatchers.any
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder.on

internal class RegisterControllerTest{
    @InjectMocks
    lateinit var rc : RegisterController
    @Mock
    lateinit var repo : PersonRepo
    lateinit var p : Person

    @BeforeEach
    fun setup(){
        MockitoAnnotations.openMocks(this)
        p = Person("testUserName", "testName", "password", 50, "male", "1971-01-01")
        println("inside setup")
    }

    @AfterEach
    fun cleanup(){println("inside cleanup")}

    @Test
    @DisplayName("save to db:pw len>6")
    @Throws(PasswordException::class)
    fun passwordTest1(){
        p.password = "password"
        //doReturn(p).`when`(repo.save(any(Person::class.java)))
        `when`(repo.save(any(Person::class.java))).thenReturn(p)
        rc.details(p)
        assertEquals(8,p.password.length)
    }

    @Test
    @DisplayName("save to db-pw len=6")
    @Throws(PasswordException::class)
    fun passwordTest2(){
        p.password = "passwo"
        `when`(repo.save(any(Person::class.java))).thenReturn(p)
        rc.details(p)
        assertEquals(6,p.password.length)
    }

    @Test
    @DisplayName("dnt save to db,throw exceptn-pw len<6")
    fun passwordTest3(){
        p.password = "pass"
        assertThrows(Exception::class.java, { -> rc.details(p)},
            "password length is less than 6::criteria violation,throws exception")
    }

}