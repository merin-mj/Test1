package com.ibs.litmus.repositoryk

import com.ibs.litmus.modelk.Person
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Component
import java.util.*

interface PersonRepo : CrudRepository<Person, String>
    //abstract fun findById(username: String): Optional<Person>
