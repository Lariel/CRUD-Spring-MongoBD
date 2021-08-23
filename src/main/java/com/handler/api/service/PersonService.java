package com.handler.api.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

import com.handler.api.model.Person;
import com.handler.api.model.PersonDto;
import com.handler.api.repository.PersonRepository;

@Slf4j
@Service
public class PersonService {
	
	@Autowired
	private PersonRepository personRepository;
	
	public PersonDto create(PersonDto person) {
		log.info("Criando pessoa.");
		person.setId(null);
		person.setCreated(LocalDateTime.now());
		person.setLastUpdate(LocalDateTime.now());
		return PersonDto.buildFromDocument(
			personRepository.save(Person.buildFromDto(person)));
	}
	
	public List<PersonDto> searchPersonBy(
		String firstName,
		String lastName) {
			log.debug("teste log debug");
			log.info("teste log info");
			log.warn("teste log warn");
			log.error("teste log error");
			if (Objects.isNull(firstName) && Objects.isNull(lastName)) {
				log.info("Pesquisando pessoas.");
				return personRepository
					.findAll()
					.stream()
					.map(PersonDto::buildFromDocument)
					.collect(Collectors.toList());
			}
			if (Objects.isNull(firstName)) {
				log.info("Pesquisando pessoas por sobrenome.");
				return personRepository
					.findByLastName(lastName)
					.stream()
					.map(PersonDto::buildFromDocument)
					.collect(Collectors.toList());
			}
			if (Objects.isNull(lastName)) {
				log.info("Pesquisando pessoas por nome.");
				return personRepository
					.findByFirstName(firstName)
					.stream()
					.map(PersonDto::buildFromDocument)
					.collect(Collectors.toList());
			}
			log.info("Pesquisando pessoas por nome e sobrenome.");
			return personRepository
				.findByFirstNameAndLastName(firstName, lastName)
				.stream()
				.map(PersonDto::buildFromDocument)
				.collect(Collectors.toList());
	}

	public PersonDto getBy(String id) {
		log.info("Buscando pessoa por id.");
		return Stream.of(personRepository.findById(id))
			.filter(Objects::nonNull)
			.filter(Optional::isPresent)
			.map(Optional::get)
			.findFirst()
			.map(PersonDto::buildFromDocument)
			.orElse(null);
	}
	
	public void update(PersonDto person, PersonDto personDocument) {
		log.info("Atualizando pessoa.");
		person.setCreated(personDocument.getCreated());
		person.setLastUpdate(LocalDateTime.now());
		PersonDto.buildFromDocument(
			personRepository.save(Person.buildFromDto(person)));
	}
	
	public void deleteById(String id) {
		log.info("Excluindo pessoa por id.");
		Person p = personRepository.findById(id).get();
		personRepository.delete(p);
	}
	
	public void deleteAll() {
		log.warn("Limpando a base de pessoas.");
		personRepository.deleteAll();
	}

}
