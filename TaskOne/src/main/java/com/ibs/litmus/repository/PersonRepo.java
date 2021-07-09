package com.ibs.litmus.repository;

import org.springframework.data.repository.CrudRepository;

import com.ibs.litmus.model.Person;

public interface PersonRepo extends CrudRepository<Person, String>{

}
