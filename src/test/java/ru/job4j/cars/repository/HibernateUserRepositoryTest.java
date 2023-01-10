package ru.job4j.cars.repository;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.jupiter.api.Test;
import ru.job4j.cars.model.User;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Тесты для хранилища пользователей - HibernateUserRepository
 *
 * @author Ilya Kaltygin
 */
class HibernateUserRepositoryTest {

    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();

    private final SessionFactory sf = new MetadataSources(registry)
            .buildMetadata().buildSessionFactory();

    /**
     * Сохранить пользователя в базе данных.
     */
    @Test
    void whenAddNewUserThenDBHasSameUser() {
        var crudRepository = new HibernateCrudRepository(sf);
        var userRepository = new HibernateUserRepository(crudRepository);

        var user = new User();
        user.setLogin("login");
        user.setPassword("password");

        var result = userRepository.addUser(user);

        assertThat(result.isPresent()).isTrue();
    }

    /**
     * Обновить пользователя в базе данных.
     */
    @Test
    void whenAddNewUserThenUpdateIt() {
        var crudRepository = new HibernateCrudRepository(sf);
        var userRepository = new HibernateUserRepository(crudRepository);

        var user = new User();
        user.setPassword("password");
        user.setLogin("login");

        userRepository.addUser(user);

        user.setLogin("new login");
        user.setPassword("new password");

        userRepository.update(user);

        var result = userRepository.findById(user.getId());

        assertThat(result.get().getLogin()).isEqualTo(user.getLogin());
    }

    /**
     * Удалить пользователя из базы данных.
     */
    @Test
    void whenAddUsersThenDeleteOneOfThem() {
        var crudRepository = new HibernateCrudRepository(sf);
        var userRepository = new HibernateUserRepository(crudRepository);

        var user1 = new User();
        user1.setLogin("user1");
        user1.setPassword("password1");

        var user2 = new User();
        user2.setLogin("user2");
        user2.setPassword("password2");

        var user3 = new User();
        user3.setLogin("user3");
        user3.setPassword("password3");

        var user4 = new User();
        user4.setLogin("user4");
        user4.setPassword("password4");

        userRepository.addUser(user1);
        userRepository.addUser(user2);
        userRepository.addUser(user3);
        userRepository.addUser(user4);
        userRepository.delete(user2.getId());

        List<User> userList = userRepository.findAllOrderById();

        assertThat(userList).isEqualTo(List.of(user1, user3, user4));
    }

    /**
     * Получить всех пользователей из базы данных отсортированных по id.
     */
    @Test
    void whenAddUsersThenFindAllOrderById() {
        var crudRepository = new HibernateCrudRepository(sf);
        var userRepository = new HibernateUserRepository(crudRepository);

        var user1 = new User();
        user1.setLogin("user1");
        user1.setPassword("password1");

        var user2 = new User();
        user2.setLogin("user2");
        user2.setPassword("password2");

        var user3 = new User();
        user3.setLogin("user3");
        user3.setPassword("password3");

        var user4 = new User();
        user4.setLogin("user4");
        user4.setPassword("password4");

        userRepository.addUser(user1);
        userRepository.addUser(user2);
        userRepository.addUser(user3);
        userRepository.addUser(user4);

        List<User> userList = userRepository.findAllOrderById();

        assertThat(userList).isEqualTo(List.of(user1, user2, user3, user4));
    }

    /**
     * Получить пользователя из базы данных по id.
     */
    @Test
    void whenAddNewUsersThenFindById() {
        var crudRepository = new HibernateCrudRepository(sf);
        var userRepository = new HibernateUserRepository(crudRepository);

        var user1 = new User();
        user1.setLogin("user1");
        user1.setPassword("password1");

        var user2 = new User();
        user2.setLogin("user2");
        user2.setPassword("password2");

        userRepository.addUser(user1);
        userRepository.addUser(user2);

        Optional<User> userFromDB = userRepository.findById(user1.getId());

        assertThat(userFromDB.get().getLogin()).isEqualTo(user1.getLogin());
    }

    /**
     * Получить пользователя из базы данных по логину, который соответствует шаблону
     */
    @Test
    void whenAddUsersThenFindByLikeLogin() {
        var crudRepository = new HibernateCrudRepository(sf);
        var userRepository = new HibernateUserRepository(crudRepository);

        var user1 = new User();
        user1.setLogin("user1");
        user1.setPassword("password1");

        var user2 = new User();
        user2.setLogin("user2");
        user2.setPassword("password2");

        userRepository.addUser(user1);
        userRepository.addUser(user2);

        List<User> usersFromDB = userRepository.findByLikeLogin("user");

        assertThat(usersFromDB).isEqualTo(List.of(user1, user2));
    }

    /**
     * Получить пользователя из базы данных по логину, который соответствует шаблону
     */
    @Test
    void whenAddUsersThenFindByLogin() {
        var crudRepository = new HibernateCrudRepository(sf);
        var userRepository = new HibernateUserRepository(crudRepository);

        var user1 = new User();
        user1.setLogin("user1");
        user1.setPassword("password1");

        var user2 = new User();
        user2.setLogin("user2");
        user2.setPassword("password2");

        userRepository.addUser(user1);
        userRepository.addUser(user2);

        Optional<User> userFromDB = userRepository.findByLogin("user1");

        assertThat(userFromDB.get().getLogin()).isEqualTo(user1.getLogin());
    }
}