package ru.job4j.cars.repository;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.jupiter.api.Test;
import ru.job4j.cars.model.Engine;
import static org.assertj.core.api.Assertions.*;

/**
 * Тесты для хранилища двигателей - HibernateEngineRepository
 *
 * @author Ilya Kaltygin
 */
class HibernateEngineRepositoryTest {

    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();

    private final SessionFactory sf = new MetadataSources(registry)
            .buildMetadata().buildSessionFactory();

    /**
     * Сохранить двигатель в базе данных.
     */
    @Test
    void whenAddNewEngineThenDBHasSameEngine() {
        var crudRepository = new HibernateCrudRepository(sf);
        var engineRepository = new HibernateEngineRepository(crudRepository);

        var engine = new Engine();
        engine.setName("engine");

        var result = engineRepository.addEngine(engine);

        assertThat(result.isPresent()).isTrue();
    }
}