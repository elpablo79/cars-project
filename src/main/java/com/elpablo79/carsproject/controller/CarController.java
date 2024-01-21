package com.elpablo79.carsproject.controller;

import com.elpablo79.carsproject.model.Car;
import com.elpablo79.carsproject.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class CarController {

    @Autowired
    private CarService carService;

    // Call first filter
    @GetMapping("/one-year-automatic")
    public List<Car> getOneYearOldAutomaticCars(@RequestParam(name = "isFourWheelDrive", required = false)
                                                    Boolean isFourWheelDrive) {
        return carService.getOneYearOldAutomaticCars(isFourWheelDrive);
    }

    // Call second filter
    @GetMapping("/older-diesel")
    public List<Car> getOldestDieselCars(@RequestParam(name = "isFourWheelDrive", required = false)
                                             Boolean isFourWheelDrive) {
        return carService.getOlderDieselCars(isFourWheelDrive);
    }

    // Call third filter
    @GetMapping("/three-year-5k-to-10k-automatic")
    public List<Car> getThreeYearOld5kTo10kAutomaticCars(@RequestParam(name = "isFourWheelDrive", required = false)
                                                             Boolean isFourWheelDrive) {
        return carService.getThreeYearOld5kTo10kAutomaticCars(isFourWheelDrive);
    }
}
