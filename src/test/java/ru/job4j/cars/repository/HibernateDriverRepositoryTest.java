package ru.job4j.cars.repository;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.jupiter.api.Test;
import ru.job4j.cars.model.Driver;
import ru.job4j.cars.model.User;
import static org.assertj.core.api.Assertions.*;

/**
 * Тесты для хранилища водителей - HibernateDriverRepository
 *
 * @author Ilya Kaltygin
 */
class HibernateDriverRepositoryTest {

    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();

    private final SessionFactory sf = new MetadataSources(registry)
            .buildMetadata().buildSessionFactory();

    /**
     * Сохранить водителя в базе данных.
     */
    @Test
    void whenAddNewUserThenDBHasSameUser() {
        var crudRepository = new HibernateCrudRepository(sf);
        var driverRepository = new HibernateDriverRepository(crudRepository);
        var userRepository = new HibernateUserRepository(crudRepository);

        var user = new User();
        user.setLogin("login");
        user.setPassword("password");

        var driver = new Driver();
        driver.setName("driver");
        driver.setUserId(user);

        userRepository.addUser(user);
        driverRepository.addDriver(driver);

        var result = driverRepository.addDriver(driver);

        assertThat(result.isPresent()).isTrue();
    }
}