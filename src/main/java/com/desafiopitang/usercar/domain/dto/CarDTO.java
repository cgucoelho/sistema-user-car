package com.desafiopitang.usercar.domain.dto;

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
	
	private int yearManufature;
	private String licensePlate;
	private String model;
	private String color;
	private User user;

}
