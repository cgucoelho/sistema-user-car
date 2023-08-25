package com.desafiopitang.usercar.domain.dto;

import javax.validation.constraints.NotNull;

import com.desafiopitang.usercar.domain.model.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CarDTO {
	
	@NotNull(message = "Campo obrigatório")
	private int yearManufature;
	@NotNull(message = "Campo obrigatório")
	private String licensePlate;
	@NotNull(message = "Campo obrigatório")
	private String model;
	@NotNull(message = "Campo obrigatório")
	private String color;
	
	private User user;

}
