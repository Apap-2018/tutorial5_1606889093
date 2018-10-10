package com.apap.tutorial5.service;

import java.util.List;
import com.apap.tutorial5.model.CarModel;

public interface CarService {
	void addCar(CarModel car);
	void deleteCar(Long carId);
	CarModel findCarById(long id);
	void carUpdate(CarModel car, Long id);
	List<CarModel> sortDrHarga(Long dealerId);
}
