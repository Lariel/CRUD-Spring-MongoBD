package com.handler.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.handler.api.model.Person;
import com.handler.api.service.PersonService;

@RestController
@RequestMapping(value="/person")
public class PersonController {
	
	@Autowired
	private PersonService personService;
	
	/*
	 * cadastra uma nova pessoa
	 * http://localhost:8085/person/new?firstName=Fulano&lastName=Silvaa&age=28
	 */
	@PostMapping("/new")
	public String create(@RequestParam String firstName, @RequestParam String lastName, @RequestParam int age) {
		Person p = personService.create(firstName, lastName, age);
		return p.toString();
	}
	
	/*
	 * retorna a pessoa pelo nome
	 * http://localhost:8085/person
	 */
	@GetMapping("/{firstName}")
	public Person getPerson(@PathVariable String firstName) {
		return personService.getByFirstName(firstName);
	}
	
	/*
	 * retorna todas as pessoas
	 * http://localhost:8085/person
	 */
	@GetMapping("")
	public List<Person> getAll(){
		return personService.getAll();
	}
	
	/*
	 * atualiza a idade
	 * hhttp://localhost:8085/person/update?firstName=Fulane&age=50
	 */
	@PatchMapping("/update")
	public String updateIdade(@RequestParam String firstName, @RequestParam int age) {
		Person p = personService.update(firstName, age);
		return p.toString();
	}
	
	@DeleteMapping("/delete")
	public String delete(@RequestParam String firstName) {
		personService.deleteByFirstName(firstName);
		return "Deletado "+firstName;
	}
	
	@DeleteMapping ("/deleteAll")
	public String deleteAll() {
		personService.deleteAll();
		return "Dropei todo mundo";
	}
}
