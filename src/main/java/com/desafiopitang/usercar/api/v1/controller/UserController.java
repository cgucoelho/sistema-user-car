package com.desafiopitang.usercar.api.v1.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.desafiopitang.usercar.domain.dto.UserDTO;
import com.desafiopitang.usercar.domain.model.Car;
import com.desafiopitang.usercar.domain.model.User;
import com.desafiopitang.usercar.domain.service.impl.UserServiceImpl;

@RestController
@RequestMapping("api/users")
public class UserController {
	
	@Autowired
	private UserServiceImpl userServiceImpl;
	
	@PostMapping(produces="application/json")
	public ResponseEntity<Object> newUser(@Valid @RequestBody UserDTO dto){
		if(userServiceImpl.existsByLogin(dto.getLogin())) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Login already exists");
		}else if(userServiceImpl.existsByEmail(dto.getEmail())) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Email already exists");
		}
		var user = new User();
		user.setFirstName(dto.getFirstName());
		user.setLastName(dto.getLastName());
		user.setEmail(dto.getEmail());
		user.setLogin(dto.getLogin());
		user.setPhone(dto.getPhone());
		user.setBirthday(dto.getBirthday());
		String encryptedPassword = new BCryptPasswordEncoder().encode(dto.getPassword());
		user.setPassword(encryptedPassword);	
		for (int i = 0; i < dto.getCars().size(); i++) {
			var car = new Car();
			car.setColor(dto.getCars().get(i).getColor());
			car.setLicensePlate(dto.getCars().get(i).getLicensePlate());
			car.setModel(dto.getCars().get(i).getModel());
			car.setYearManufature(dto.getCars().get(i).getYearManufature());
			user.addCar(car);			
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(userServiceImpl.save(user));		
	}
	
	@GetMapping(produces="application/json")
	public ResponseEntity<List<User>> listAllUsers(){
		
		return ResponseEntity.status(HttpStatus.OK).body(userServiceImpl.findUsers());
		
	}
	@GetMapping("/{id}")
	public ResponseEntity<Object> findUser(@PathVariable Long id){
		Optional<User> user = userServiceImpl.findUserByID(id);
		if(!user.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado");
		}		
		return ResponseEntity.status(HttpStatus.OK).body(user.get());		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Object> updateUser(@PathVariable Long id, @RequestBody UserDTO dto){
		Optional<User> user = userServiceImpl.findUserByID(id);
		if(!user.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado");
		}	else {
			BeanUtils.copyProperties(dto, user.get());			
			return ResponseEntity.status(HttpStatus.CREATED).body(userServiceImpl.save(user.get()));			
		}			
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteUser(@PathVariable Long id){
		Optional<User> user = userServiceImpl.findUserByID(id);
		if(!user.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado");
		}
		userServiceImpl.delete(id);
		return ResponseEntity.status(HttpStatus.OK).body("Usuario Excluído com sucesso");
		
		
	}

}
