package ru.job4j.cars.repository;

import ru.job4j.cars.model.Engine;

import java.util.Optional;

/**
 * EngineRepository - хранилище двигателей
 *
 * @author Ilya Kaltygin
 */

public interface EngineRepository {

    /**
     * Сохранить двигатель в базе данных.
     * @param engine двигатель.
     * @return Optional.of(engine) если успешно, иначе Optional.empty().
     */
    Optional<Engine> addEngine(Engine engine);
}
