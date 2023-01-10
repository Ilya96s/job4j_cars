package ru.job4j.cars.repository;

import ru.job4j.cars.model.Car;
import java.util.Optional;

/**
 * CarRepository - хранилище автомобилей
 *
 * @author Ilya Kaltygin
 */

public interface CarRepository {

    /**
     * Сохранить автомобиль в базе данных.
     * @param car автомобиль.
     * @return Optional.of(car) если успешно, иначе Optional.empty().
     */
    Optional<Car> addCar(Car car);

}
