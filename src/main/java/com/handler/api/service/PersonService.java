package com.handler.api.service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.handler.api.model.Person;
import com.handler.api.repository.PersonRepository;

/*
 * Provide CRUD operations
 */

@Service
public class PersonService {
	
	@Autowired
	private PersonRepository personRepository;
	
	public Person create(String firstName, String lastName, int age) {
		return personRepository.save(Person
			.builder()
			.firstName(firstName)
			.lastName(lastName)
			.age(age)
			.lastUpdate(LocalDateTime.now())
			.build());
		
	}
	
	public List<Person> getAll(){
		return personRepository.findAll();
	}
	
	public Person getByFirstName(String firstName) {
		return personRepository.findByFirstName(firstName);
	}
	
	public Person update(String firstName, int age) {
		Person p = personRepository.findByFirstName(firstName);
		p.setAge(age);
		p.setLastUpdate(LocalDateTime.now());
		return personRepository.save(p);
	}
	
	public void deleteAll() {
		personRepository.deleteAll();
	}
	
	public void deleteByFirstName(String firstName) {
		Person p = personRepository.findByFirstName(firstName);
		personRepository.delete(p);
	}

}
