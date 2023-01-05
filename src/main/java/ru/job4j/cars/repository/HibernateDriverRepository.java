package ru.job4j.cars.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

/**
 * HibernateDriverRepository - реализация хранилища водителей
 *
 * @author Ilya Kaltygin
 */

@Repository
@AllArgsConstructor
public class HibernateDriverRepository implements DriverRepository {

    /**
     * Объект типа CrudRepository
     */
    private final CrudRepository crudRepository;
}
