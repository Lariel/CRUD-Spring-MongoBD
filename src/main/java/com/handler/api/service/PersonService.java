package com.handler.api.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.handler.api.model.Person;
import com.handler.api.model.PersonDto;
import com.handler.api.repository.PersonRepository;

@Service
public class PersonService {
	
	@Autowired
	private PersonRepository personRepository;
	
	public PersonDto create(PersonDto person) {
		person.setId(null);
		person.setCreated(LocalDateTime.now());
		person.setLastUpdate(LocalDateTime.now());
		return PersonDto.buildFromDocument(
			personRepository.save(Person.buildFromDto(person)));
	}
	
	public List<PersonDto> searchPersonBy(
		String firstName,
		String lastName) {
			if (Objects.isNull(firstName) && Objects.isNull(lastName)) {
				return personRepository
					.findAll()
					.stream()
					.map(PersonDto::buildFromDocument)
					.collect(Collectors.toList());
			}
			if (Objects.isNull(firstName)) {
				return personRepository
					.findByLastName(lastName)
					.stream()
					.map(PersonDto::buildFromDocument)
					.collect(Collectors.toList());
			}
			if (Objects.isNull(lastName)) {
				return personRepository
					.findByFirstName(firstName)
					.stream()
					.map(PersonDto::buildFromDocument)
					.collect(Collectors.toList());
			}
			return personRepository
				.findByFirstNameAndLastName(firstName, lastName)
				.stream()
				.map(PersonDto::buildFromDocument)
				.collect(Collectors.toList());
	}

	public PersonDto getBy(String id) {
		return Stream.of(personRepository.findById(id))
			.filter(Objects::nonNull)
			.filter(Optional::isPresent)
			.map(Optional::get)
			.findFirst()
			.map(PersonDto::buildFromDocument)
			.orElse(null);
	}
	
	public void update(PersonDto person, PersonDto personDocument) {
		person.setCreated(personDocument.getCreated());
		person.setLastUpdate(LocalDateTime.now());
		PersonDto.buildFromDocument(
			personRepository.save(Person.buildFromDto(person)));
	}
	
	public void deleteById(String id) {
		Person p = personRepository.findById(id).get();
		personRepository.delete(p);
	}
	
	public void deleteAll() {
		personRepository.deleteAll();
	}

}
