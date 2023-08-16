package com.desafiopitang.usercar.domain.service;

import java.util.List;
import java.util.Optional;

import com.desafiopitang.usercar.domain.dto.CarDTO;
import com.desafiopitang.usercar.domain.model.Car;

public interface CarService {
	
	Car save(Car car);
	
	Optional<Car> findCarByID(Long id);
	
	Car upDate(Long id, CarDTO carDto);
	
	List<Car> findCars();
	
	void delete(Long id);
	
	boolean existsByLicensePlate (String licensePlate);
}
