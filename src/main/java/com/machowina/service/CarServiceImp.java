package com.machowina.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.machowina.exception.EntityNotFoundException;
import com.machowina.model.Car;
import com.machowina.repository.CarRepository;

@Service
public class CarServiceImp implements CarService {
	
	private final CarRepository carRepository;

	@Autowired
	public CarServiceImp(CarRepository carRepository) {
		super();
		this.carRepository = carRepository;
	}

	@Override
	public Car findById(Long carId) {
		Car car = carRepository.findOne(carId);
		if (car == null) {
			throw new EntityNotFoundException("There is no car with this ID");
		} else {
			return car;
		}
	}
	
	
	

}
