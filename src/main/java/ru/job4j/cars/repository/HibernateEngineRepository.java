package ru.job4j.cars.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

/**
 * HibernateEngineRepository - реализация хранилища двигателей
 *
 * @author Ilya Kaltygin
 */

@Repository
@AllArgsConstructor
public class HibernateEngineRepository implements EngineRepository {
    /**
     * Объект типа CrudRepository
     */
    private final CrudRepository crudRepository;

}
