package com.desafiopitang.usercar.api.v1.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.desafiopitang.usercar.domain.dto.CarDTO;
import com.desafiopitang.usercar.domain.model.Car;
import com.desafiopitang.usercar.domain.model.User;
import com.desafiopitang.usercar.domain.service.impl.CarServiceImpl;
import com.desafiopitang.usercar.domain.service.impl.UserServiceImpl;

@RestController
@RequestMapping("api/cars")
public class CarController {
	
	@Autowired
	private CarServiceImpl carServiceImpl;
	@Autowired
	private UserServiceImpl userServiceImpl;
	
	@PostMapping
	public ResponseEntity<Object> newCar(@RequestBody CarDTO dto, HttpServletRequest requisicao){
		if(carServiceImpl.existsByLicensePlate(dto.getLicensePlate())) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("License plate already exists");
		}
		var usr = userServiceImpl.loadUserByUsername(requisicao.getRemoteUser());
		var car = new Car();
		car.setUser((User) usr);
		
		BeanUtils.copyProperties(dto, car);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(carServiceImpl.save(car));		
	}
	
	@GetMapping
	public ResponseEntity<List<Car>> listAllCars(){
		
		return ResponseEntity.status(HttpStatus.OK).body(carServiceImpl.findCars());
		
	}
	@GetMapping("/{id}")
	public ResponseEntity<Object> findCar(Long id){
		Optional<Car> car = carServiceImpl.findCarByID(id);
		if(!car.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Carro não encontrado");
		}		
		return ResponseEntity.status(HttpStatus.OK).body(car.get());		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Object> updateCar(@PathVariable Long id, @RequestBody CarDTO dto){
		Optional<Car> car = carServiceImpl.findCarByID(id);
		if(!car.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Carro não encontrado");
		}else {
			BeanUtils.copyProperties(dto, car.get());			
			return ResponseEntity.status(HttpStatus.CREATED).body(carServiceImpl.save(car.get()));			
		}			
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteCar(@PathVariable Long id){
		Optional<Car> car = carServiceImpl.findCarByID(id);
		if(!car.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Carro não encontrado");
		}
		carServiceImpl.delete(id);
		return ResponseEntity.status(HttpStatus.OK).body("Cliente Excluído com sucesso");
		
		
	}
	

}
