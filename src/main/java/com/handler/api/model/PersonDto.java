package com.handler.api.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PersonDto {
	private String id;
	@NotNull(message = "Primeiro nome é um campo obrigatório")
	private String firstName;
	@NotNull(message = "Sobrenome é um campo obrigatório")
	private String lastName;
	@NotNull(message = "Data de nascimento é obrigatória")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate birthDay;
	private String age;
	private String gender;
	private String cpf;
	private LocalDateTime created;
	private LocalDateTime lastUpdate;	

	public static PersonDto buildFromDocument(Person person) {
		return PersonDto
			.builder()
			.id(person.getId())
			.firstName(person.getFirstName())
			.lastName(person.getLastName())
			.age(person.getAge())
			.birthDay(person.getBirthday())
			.gender(person.getGender())
			.cpf(person.getCpf())
			.lastUpdate(person.getLastUpdate())
			.created(person.getCreated())
			.build();
	}
}
