package com.handler.api.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.handler.api.model.Person;

@Repository
public interface PersonRepository extends MongoRepository<Person,String> {

	public List<Person> findByAge(int age);
	public List<Person> findByFirstNameAndLastName(String firstName, String lastName);
	public List<Person> findByFirstName(String firstName);
	public List<Person> findByLastName(String lastName);
}
