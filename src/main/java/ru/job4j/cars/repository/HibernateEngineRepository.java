package ru.job4j.cars.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.Engine;

import java.util.Optional;

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

    /**
     * Сохранить двигатель в базе данных.
     * @param engine двигатель.
     * @return Optional.of(engine) если успешно, иначе Optional.empty().
     */
    public Optional<Engine> addEngine(Engine engine) {
        crudRepository.run(session -> session.save(engine));
        return Optional.of(engine);
    }

}
