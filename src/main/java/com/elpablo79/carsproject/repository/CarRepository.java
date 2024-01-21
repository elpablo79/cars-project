package com.elpablo79.carsproject.repository;

import com.elpablo79.carsproject.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Long> {
}
