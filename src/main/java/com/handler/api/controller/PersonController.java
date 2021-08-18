package com.handler.api.controller;

import java.net.URI;
import java.util.List;
import java.util.Objects;

import javax.validation.Valid;

import com.handler.api.model.PersonDto;
import com.handler.api.service.PersonService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;

@RestController
public class PersonController implements IPersonController {
    	
	@Autowired
	private PersonService personService;

    @Override
    public ResponseEntity<List<PersonDto>> searchPerson(String firstName, String lastName) {
		return ResponseEntity.ok(personService.searchPersonBy(firstName, lastName));
    }

    @Override
    public ResponseEntity<PersonDto> getPerson(String id) {
        var p = personService.getBy(id);

        return Objects.nonNull(p) ? ResponseEntity.ok(p) : new ResponseEntity<>(NO_CONTENT);
    }

    @Override
    public ResponseEntity<?> updatePerson(@Valid PersonDto person) {
        var personDocument = personService.getBy(person.getId());

		if (Objects.nonNull(personDocument)) {
			personService.update(person, personDocument);
            return new ResponseEntity<>(OK);
		} else {
            return ResponseEntity.created(URI.create("/people/" + personService.create(person).getId())).build();
		}
    }

    @Override
    public ResponseEntity<?> deletePerson() {
        personService.deleteAll();
        return new ResponseEntity<>(OK);
    }

    @Override
    public ResponseEntity<?> deletePerson(String id) {
        personService.deleteById(id);
        return new ResponseEntity<>(OK);
    }

    @Override
    public ResponseEntity<?> createPerson(@Valid PersonDto person) {
        return ResponseEntity.created(URI.create("/people/" + personService.create(person).getId())).build();
    }
    
}
