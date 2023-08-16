package com.desafiopitang.usercar.domain.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desafiopitang.usercar.domain.dto.CarDTO;
import com.desafiopitang.usercar.domain.model.Car;
import com.desafiopitang.usercar.domain.repository.CarResipository;
import com.desafiopitang.usercar.domain.service.CarService;

@Service
public class CarServiceImpl  implements CarService{
	
	@Autowired
	private CarResipository carRepository;

	@Override
	public Car save(Car car) {
		
		return carRepository.save(car);
	}

	@Override
	public Optional<Car> findCarByID(Long id) {
		
		return carRepository.findById(id);
	}

	@Override
	public Car upDate(Long id, CarDTO carDto) {
		
		return null;
	}

	@Override
	public List<Car> findCars() {
		
		return carRepository.findAll();
	}

	@Override
	public void delete(Long id) {
		carRepository.deleteById(id);
	}

	@Override
	public boolean existsByLicensePlate(String licensePlate) {
		
		return carRepository.existsByLicensePlate(licensePlate);
	}

}
