package ru.job4j.cars.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.Driver;

import java.util.Optional;

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

    /**
     * Сохранить водителя в базе данных.
     * @param driver водитель.
     * @return Optional.of(driver) если успешно, иначе Optional.empty().
     */
    public Optional<Driver> addDriver(Driver driver) {
        crudRepository.run(session -> session.save(driver));
        return Optional.of(driver);
    }
}
