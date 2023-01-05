package ru.job4j.cars.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

/**
 * HibernateCarRepository - реализация хранилища автомобилей
 *
 * @author Ilya Kaltygin
 */

@Repository
@AllArgsConstructor
public class HibernateCarRepository implements CarRepository {

    /**
     * Объект типа CrudRepository
     */
    private final CrudRepository crudRepository;

}
