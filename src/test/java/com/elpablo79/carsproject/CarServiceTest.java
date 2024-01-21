package com.elpablo79.carsproject;

import com.elpablo79.carsproject.model.Car;
import com.elpablo79.carsproject.repository.CarRepository;
import com.elpablo79.carsproject.service.CarService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CarServiceTest {

    @Mock
    private CarRepository carRepository;

    @InjectMocks
    private CarService carService;

    List<Car> mockCars = Arrays.asList(
            new Car(1L, false, "5K to 10K", 1, true, "petrol"),
            new Car(2L, true, "5K to 10K", 3, true, "electric"),
            new Car(3L, false, "0 to 5K", 5, true, "diesel"),
            new Car(4L, true, "0 to 5K", 1, false, "diesel")
    );

    @Test
    public void testGetOneYearAutomaticCars() {
        when(carRepository.findAll()).thenReturn(mockCars);

        List<Car> result = carService.getOneYearOldAutomaticCars(null);

        assertNotNull(result);
        assertEquals(1, result.size());

        for (Car car : result) {
            assertEquals(1, car.getSeniority());
            assertTrue(car.isAutomatic());
        }
    }

    @Test
    public void testGetOlderDieselCars() {
        when(carRepository.findAll()).thenReturn(mockCars);

        List<Car> result = carService.getOlderDieselCars(null);

        assertNotNull(result);
        assertEquals(1, result.size());

        for (Car car : result) {
            assertEquals("diesel", car.getFuelType());
            assertEquals(5, car.getSeniority());
        }
    }

    @Test
    public void testGetThreeYearOld5kTo10kAutomaticCars() {
        when(carRepository.findAll()).thenReturn(mockCars);

        List<Car> result = carService.getThreeYearOld5kTo10kAutomaticCars(null);

        assertNotNull(result);
        assertEquals(1, result.size());

        Car car = result.get(0);
        assertEquals(3, car.getSeniority());
        assertEquals("5K to 10K", car.getPriceRange());
        assertTrue(car.isAutomatic());
    }
}