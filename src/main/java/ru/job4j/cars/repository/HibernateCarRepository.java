package ru.job4j.cars.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.Car;

import java.util.Optional;

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

    /**
     * Сохранить автомобиль в базе данных.
     * @param car автомобиль.
     * @return Optional.of(car) если успешно, иначе Optional.empty().
     */
    @Override
    public Optional<Car> addCar(Car car) {
        crudRepository.run(session -> session.save(car));
        return Optional.of(car);
    }
}
