package com.handler.api.controller;

import java.util.List;

import com.handler.api.model.Person;
import com.handler.api.model.PersonDto;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping(value="/people")
public interface IPersonController {

    @GetMapping()
    public ResponseEntity<List<PersonDto>> searchPerson(
      @RequestParam(value = "firstName", required = false) String firstName,
		  @RequestParam(value = "lastName", required = false) String lastName);
    
    @GetMapping("/{id}")
    public ResponseEntity<PersonDto> getPerson(@PathVariable("id") String id);

    @PutMapping()
    public ResponseEntity<?> updatePerson(@RequestBody PersonDto person);

    @DeleteMapping()
    public ResponseEntity<?> deletePerson();

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePerson(@PathVariable("id") String id);

    @PostMapping()
    public ResponseEntity<?> createPerson(@RequestBody PersonDto person);
    
}
