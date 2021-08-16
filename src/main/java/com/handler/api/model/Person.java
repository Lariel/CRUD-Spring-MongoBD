package com.handler.api.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Person {
	@Id
	private String id;
	private String firstName;
	private String lastName;
	private LocalDate birthday;
	private String gender;
	private String cpf;
	private LocalDateTime created;
	private LocalDateTime lastUpdate;
	
	public String getAge() {
		final var hoje = LocalDate.now();
		Period periodo = Period.between(birthday, hoje);
		StringBuilder idade = new StringBuilder();
		idade.append(periodo.getYears());
		idade.append(" anos, ");
		idade.append(periodo.getMonths());
		idade.append(" meses, ");
		idade.append(periodo.getDays());
		idade.append(" dias.");
		return idade.toString();
	}

	public static Person buildFromDto(PersonDto person) {
		return Person
			.builder()
			.id(person.getId())
			.firstName(person.getFirstName())
			.lastName(person.getLastName())
			.birthday(person.getBirthDay())
			.gender(person.getGender())
			.cpf(person.getCpf())
			.lastUpdate(person.getLastUpdate())
			.created(person.getCreated())
			.build();
	}	
}
