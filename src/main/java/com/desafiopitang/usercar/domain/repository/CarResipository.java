package com.desafiopitang.usercar.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.desafiopitang.usercar.domain.model.Car;

@Repository
public interface CarResipository extends JpaRepository<Car, Long> {
	
	boolean existsByLicensePlate(String licensePlate);

}
