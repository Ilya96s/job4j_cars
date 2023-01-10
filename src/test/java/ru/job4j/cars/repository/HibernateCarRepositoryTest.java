package ru.job4j.cars.repository;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.jupiter.api.Test;
import ru.job4j.cars.model.Car;
import ru.job4j.cars.model.Engine;
import static org.assertj.core.api.Assertions.*;

/**
 * Тесты для хранилища автомобилей - HibernateCarRepository
 *
 * @author Ilya Kaltygin
 */
class HibernateCarRepositoryTest {

    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();

    private final SessionFactory sf = new MetadataSources(registry)
            .buildMetadata().buildSessionFactory();

    /**
     * Сохранить автомобиль в базе данных.
     */
    @Test
    void whenAddNewCarThenDBHasSameCar() {
        var crudRepository = new HibernateCrudRepository(sf);
        var carRepository = new HibernateCarRepository(crudRepository);
        var engineRepository = new HibernateEngineRepository(crudRepository);

        var engine = new Engine();
        engine.setName("engine");

        var car = new Car();
        car.setName("Car");
        car.setEngineId(engine);

        engineRepository.addEngine(engine);
        carRepository.addCar(car);

        var result = carRepository.addCar(car);

        assertThat(result.isPresent()).isTrue();
    }
}