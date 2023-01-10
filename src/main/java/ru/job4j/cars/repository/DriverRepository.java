package ru.job4j.cars.repository;

import ru.job4j.cars.model.Driver;

import java.util.Optional;

/**
 * DriverRepository - хранилище водителей
 *
 * @author Ilya Kaltygin
 */

public interface DriverRepository {

    /**
     * Сохранить водителя в базе данных.
     * @param driver водитель.
     * @return Optional.of(driver) если успешно, иначе Optional.empty().
     */
    Optional<Driver> addDriver(Driver driver);
}
