package com.desafiopitang.usercar.api.v1.controller;

import java.util.List;
import java.util.Optional;

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
import com.desafiopitang.usercar.domain.model.User;
import com.desafiopitang.usercar.domain.service.impl.UserServiceImpl;

@RestController
@RequestMapping("api/users")
public class UserController {
	
	@Autowired
	private UserServiceImpl userServiceImpl;
	
	@PostMapping
	public ResponseEntity<Object> newUser(@RequestBody UserDTO dto){
		if(userServiceImpl.existsByLogin(dto.getLogin())) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Login already exists");
		}else if(userServiceImpl.existsByEmail(dto.getEmail())) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Email already exists");
		}
		var user = new User();
		BeanUtils.copyProperties(dto, user);
		String encryptedPassword = new BCryptPasswordEncoder().encode(dto.getPassword());
		user.setPassword(encryptedPassword);		
		return ResponseEntity.status(HttpStatus.CREATED).body(userServiceImpl.save(user));		
	}
	
	@GetMapping
	public ResponseEntity<List<User>> listAllUsers(){
		
		return ResponseEntity.status(HttpStatus.OK).body(userServiceImpl.findUsers());
		
	}
	@GetMapping("/{id}")
	public ResponseEntity<Object> findUser(Long id){
		Optional<User> user = userServiceImpl.findUserByID(id);
		if(!user.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado");
		}		
		return ResponseEntity.status(HttpStatus.OK).body(user.get());		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Object> updateCar(@PathVariable Long id, @RequestBody UserDTO dto){
		Optional<User> user = userServiceImpl.findUserByID(id);
		if(!user.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado");
		}	else {
			BeanUtils.copyProperties(dto, user.get());			
			return ResponseEntity.status(HttpStatus.CREATED).body(userServiceImpl.save(user.get()));			
		}			
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteCar(@PathVariable Long id){
		Optional<User> user = userServiceImpl.findUserByID(id);
		if(!user.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado");
		}
		userServiceImpl.delete(id);
		return ResponseEntity.status(HttpStatus.OK).body("Usuario Excluído com sucesso");
		
		
	}

}
