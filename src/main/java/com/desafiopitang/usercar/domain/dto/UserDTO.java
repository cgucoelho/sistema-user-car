package com.desafiopitang.usercar.domain.dto;

import java.util.Date;
import java.util.List;

import javax.transaction.TransactionScoped;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.desafiopitang.usercar.domain.model.Car;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class UserDTO {
	@NotNull(message = "Campo obrigatório")
	@NotBlank
	private String firstName;
	@NotNull(message = "Campo obrigatório")
	@NotBlank
	private String lastName;
	@NotBlank
	@Email(message = "Email inválido")
	@NotNull(message = "Campo obrigatório")
	private String email;
	@NotNull(message = "Campo obrigatório")
	private Date birthday;
	@NotNull(message = "Campo obrigatório")
	@NotBlank
	private String login;
	@NotNull(message = "Campo obrigatório")
	@NotBlank
	private String password;
	@NotNull(message = "Campo obrigatório")
	@NotBlank
	private String phone;
	@TransactionScoped
	private List<Car> cars;

}
