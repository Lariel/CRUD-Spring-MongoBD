package com.handler.api.controller;

import java.util.List;

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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "people-api")
@RequestMapping(value="/people")
public interface IPersonController {

    @ApiOperation("Pesquisar pessoas por nome ou sobrenome.")
    @GetMapping()
    public ResponseEntity<List<PersonDto>> searchPerson(
      @RequestParam(value = "firstName", required = false) String firstName,
		  @RequestParam(value = "lastName", required = false) String lastName);
    
    @ApiOperation("Obter pessoa por id.")
    @GetMapping("/{id}")
    public ResponseEntity<PersonDto> getPerson(@PathVariable("id") String id);

    @ApiOperation("Editar um cadastro de pessoa. Cria uma nova pessoa se o id informado no objeto não for encontrado.")
    @PutMapping()
    public ResponseEntity<?> updatePerson(@RequestBody PersonDto person);

    @ApiOperation("Deletar todas as pessoas cadastradas.")
    @DeleteMapping()
    public ResponseEntity<?> deletePerson();

    @ApiOperation("Deletar pessoa por id.")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePerson(@PathVariable("id") String id);

    @ApiOperation("Criar uma nova pessoa.")
    @PostMapping()
    public ResponseEntity<?> createPerson(@RequestBody PersonDto person);
    
}
