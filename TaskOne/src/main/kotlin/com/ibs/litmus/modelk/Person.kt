package com.ibs.litmus.modelk

import java.util.*
import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class Person(
    @Id
    var username : String="",
    var name : String="",
    var password : String="",
    var age : Int=0,
    var gender : String="",
    var dob : String="")

//var dob : Date = Date(0)