package com.desafiopitang.usercar.domain.dto;

import java.util.Date;
import java.util.List;

import javax.transaction.TransactionScoped;

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
	
	private String firstName;  
	private String lastName;
	private String email;
	private Date birthday;
	private String login;
	private String password;
	private String phone;
	@TransactionScoped
	private List<Car> cars;

}
