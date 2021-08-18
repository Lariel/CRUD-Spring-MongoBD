package com.handler.api.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PersonDto {
	@ApiModelProperty(hidden = true)
	private String id;

	@ApiModelProperty(required = true, position = 0)
	@NotNull(message = "Primeiro nome é um campo obrigatório")
	private String firstName;

	@ApiModelProperty(required = true, position = 1)
	@NotNull(message = "Sobrenome é um campo obrigatório")
	private String lastName;
	
	@ApiModelProperty(example = "dd/MM/yyyy", required = true, position = 2)
	@NotNull(message = "Data de nascimento é obrigatória")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate birthDay;


	@ApiModelProperty(hidden = true)
	private String age;

	@ApiModelProperty(required = false, position = 3)
	private String cpf;

	@ApiModelProperty(required = false, position = 4)
	private String gender;

	@ApiModelProperty(hidden = true)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
	private LocalDateTime created;

	@ApiModelProperty(hidden = true)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
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
