package com.apap.tutorial5.controller;

import com.apap.tutorial5.model.*;
import com.apap.tutorial5.service.*;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class CarController {
	@Autowired
	private CarService carService;
	
	@Autowired
	private DealerService dealerService;
	
	 

	@RequestMapping(value = "/car/delete", method = RequestMethod.POST)
	private String deleteCar (@ModelAttribute DealerModel dealer, Model model) {
		for (CarModel car : dealer.getListCar()) {
			Long carId = car.getId();
			carService.deleteCar(carId);
		}
	   return "delete"; 
	 }
	
	
	@RequestMapping(value = "/car/update/{carId}", method = RequestMethod.GET) 
	private String update(@PathVariable(value = "carId") Long carId, Model model) {
		CarModel car = carService.findCarById(carId);
		
		model.addAttribute("car", car);
		return "update-car";
	}
		
		
	@RequestMapping(value = "/car/update/{carId}", method = RequestMethod.POST)
	private String updateCarSubmit(@PathVariable(value = "carId") Long carId, @ModelAttribute CarModel car) {
		carService.carUpdate(car, carId);
		return "update";
		
	}
	
	@RequestMapping(value = "/car/add/{dealerId}", method = RequestMethod.GET)
	private String add(@PathVariable(value = "dealerId") Long dealerId, Model model) {
		DealerModel dealer  = dealerService.getDealerDetailByID(dealerId).get();
		ArrayList<CarModel> carList = new ArrayList<CarModel>();
		carList.add(new CarModel());
		dealer.setListCar(carList);
		
		model.addAttribute("dealer", dealer);
		return "addCar";
	}
	
	@RequestMapping(value = "/car/add/{dealerId}", method = RequestMethod.POST, params={"save"})
	private String addCarSubmit(@ModelAttribute DealerModel dealer) {
		DealerModel dealerNow  = dealerService.getDealerDetailByID(dealer.getId()).get();
		for(CarModel car: dealer.getListCar()) {
			car.setDealer(dealerNow);
			carService.addCar(car);
		}
		return "add";
	}
	
	@RequestMapping(value="/car/add/{dealerId}", method = RequestMethod.POST, params= {"addRow"})
	public String addRow(@ModelAttribute DealerModel dealer, BindingResult bindingResult, Model model) {
		if (dealer.getListCar() == null) {
            dealer.setListCar(new ArrayList<CarModel>());
        }
		dealer.getListCar().add(new CarModel());
		
		model.addAttribute("dealer", dealer);
		return "addCar";
	}
	
	@RequestMapping(value="/car/add/{dealerId}", method = RequestMethod.POST, params={"removeRow"})
	public String deleteRow(@ModelAttribute DealerModel dealer, final BindingResult bindingResult, final HttpServletRequest req, Model model) {
	    final Integer rowId = Integer.valueOf(req.getParameter("removeRow"));
	    dealer.getListCar().remove(rowId.intValue());
	    
	    model.addAttribute("dealer", dealer);
	    return "addCar";
	}

}
 