package com.apap.tutorial5.service;

import java.util.List;
import com.apap.tutorial5.model.CarModel;
import com.apap.tutorial5.repository.CarDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CarServiceImpl implements CarService {
	@Autowired
	private CarDb carDb;
	
	@Override
	public void addCar(CarModel car) {
		// TODO Auto-generated method stub
		carDb.save(car);
		
	}
	
	public void deleteCar(Long car) {
		carDb.deleteById(car);
	}
	
	@Override
	public CarModel findCarById(long id) {
		return carDb.findById(id).get();
		
	}
	
	@Override
	public void carUpdate(CarModel updateCar, Long carId) {
		CarModel dataLama = carDb.findById(carId).get();
		dataLama.setBrand(updateCar.getBrand());
		dataLama.setType(updateCar.getType());
		dataLama.setAmount(updateCar.getAmount());
		dataLama.setPrice(updateCar.getPrice());
		carDb.save(dataLama);
	}
	
	@Override
	public List<CarModel> sortDrHarga(Long dealerId){
		return carDb.findByDealerIdOrderByPriceDesc(dealerId);
	}
}
