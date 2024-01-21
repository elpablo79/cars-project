package com.elpablo79.carsproject.service;

import com.elpablo79.carsproject.model.Car;
import com.elpablo79.carsproject.repository.CarRepository;
import jakarta.annotation.PostConstruct;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;

    // First filter
    public List<Car> getOneYearOldAutomaticCars(Boolean isFourWheelDrive) {
        return carRepository.findAll().stream()
                .filter(car -> car.getSeniority() == 1 && car.isAutomatic() &&
                        (isFourWheelDrive == null || car.isFourWheelDrive() == isFourWheelDrive))
                .collect(Collectors.toList());
    }

    // Second filter
    public List<Car> getOlderDieselCars(Boolean isFourWheelDrive) {
        List<Car> dieselCars = carRepository.findAll().stream()
                .filter(car -> "diesel".equals(car.getFuelType()) &&
                        (isFourWheelDrive == null || car.isFourWheelDrive() == isFourWheelDrive))
                .toList();

        return dieselCars.stream()
                .max(Comparator.comparingInt(Car::getSeniority))
                .map(maxSeniorityCar -> dieselCars.stream()
                        .filter(car -> car.getSeniority() == maxSeniorityCar.getSeniority())
                        .collect(Collectors.toList()))
                .orElseGet(List::of);
    }

    // Third filter
    public List<Car> getThreeYearOld5kTo10kAutomaticCars(Boolean isFourWheelDrive) {
        return carRepository.findAll().stream()
                .filter(car -> car.getSeniority() == 3 && "5K to 10K".equals(car.getPriceRange()) &&
                        (isFourWheelDrive == null || car.isFourWheelDrive() == isFourWheelDrive))
                .filter(Car::isAutomatic)
                .collect(Collectors.toList());
    }

    @PostConstruct
    public void initCars() {
        try {
            Reader in = new FileReader(new ClassPathResource("cars.csv").getFile());
            Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(in);

            List<Car> cars = new ArrayList<>();
            for (CSVRecord record : records) {
                Car car = new Car();
                car.setFourWheelDrive(Boolean.parseBoolean(record.get("isFourWheelDrive")));
                car.setPriceRange(record.get("priceRange"));
                car.setSeniority(Integer.parseInt(record.get("seniority")));
                car.setAutomatic(Boolean.parseBoolean(record.get("isAutomatic")));
                car.setFuelType(record.get("fuelType"));
                cars.add(car);
            }

            carRepository.saveAll(cars);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
