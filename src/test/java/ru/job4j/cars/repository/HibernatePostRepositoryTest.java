package ru.job4j.cars.repository;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.jupiter.api.Test;
import ru.job4j.cars.model.Car;
import ru.job4j.cars.model.Engine;
import ru.job4j.cars.model.Post;
import ru.job4j.cars.model.User;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.*;

/**
 * Тесты для хранилища объявлений - HibernatePostRepository
 *
 * @author Ilya Kaltygin
 */
class HibernatePostRepositoryTest {

    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();

    private final SessionFactory sf = new MetadataSources(registry)
            .buildMetadata().buildSessionFactory();

    /**
     * Сохранить объявление в базе данных.
     */
    @Test
    void whenAddNewPostThenDBHasSamePost() {
        var crudRepository = new HibernateCrudRepository(sf);
        var postRepository = new HibernatePostRepository(crudRepository);
        var userRepository = new HibernateUserRepository(crudRepository);
        var engineRepository = new HibernateEngineRepository(crudRepository);
        var carRepository = new HibernateCarRepository(crudRepository);

        var user = new User();
        user.setLogin("user login");
        user.setPassword("password");

        userRepository.addUser(user);

        var engine = new Engine();
        engine.setName("engine");

        engineRepository.addEngine(engine);

        var car = new Car();
        car.setName("car");
        car.setEngineId(engine);

        carRepository.addCar(car);

        var post = new Post();
        post.setCreated(LocalDateTime.now());
        post.setDescription("post description");
        post.setUser(user);
        post.setCarId(car);

        Optional<Post> postFromDB = postRepository.addPost(post);
        assertThat(postFromDB.isPresent()).isTrue();
    }

    /**
     * Получить объявления за последний день.
     */
    @Test
    void whenAddPostsThenGetPostsFromLastDay() {
        var crudRepository = new HibernateCrudRepository(sf);
        var postRepository = new HibernatePostRepository(crudRepository);
        var userRepository = new HibernateUserRepository(crudRepository);
        var engineRepository = new HibernateEngineRepository(crudRepository);
        var carRepository = new HibernateCarRepository(crudRepository);

        var user = new User();
        user.setLogin("user login");
        user.setPassword("password");

        userRepository.addUser(user);

        var engine = new Engine();
        engine.setName("engine");

        engineRepository.addEngine(engine);

        var car = new Car();
        car.setName("car");
        car.setEngineId(engine);

        carRepository.addCar(car);

        var post = new Post();
        post.setCreated(LocalDateTime.now());
        post.setDescription("post description");
        post.setUser(user);
        post.setCarId(car);

        postRepository.addPost(post);

        List<Post> postsFromDB = postRepository.getPostsFromLastDay();
        assertThat(postsFromDB.size()).isEqualTo(1);
    }

    /**
     * Получить объявления с фото.
     */
    @Test
    void whenAddPostsThenGetPostsWithPhoto() {
        var crudRepository = new HibernateCrudRepository(sf);
        var postRepository = new HibernatePostRepository(crudRepository);
        var userRepository = new HibernateUserRepository(crudRepository);
        var engineRepository = new HibernateEngineRepository(crudRepository);
        var carRepository = new HibernateCarRepository(crudRepository);

        var user = new User();
        user.setLogin("user login");
        user.setPassword("password");

        var user2 = new User();
        user2.setLogin("user login2");
        user2.setPassword("password2");

        userRepository.addUser(user);
        userRepository.addUser(user2);

        var engine = new Engine();
        engine.setName("engine");

        var engine2 = new Engine();
        engine2.setName("engine2");

        engineRepository.addEngine(engine);
        engineRepository.addEngine(engine2);

        var car = new Car();
        car.setName("car");
        car.setEngineId(engine);

        var car2 = new Car();
        car2.setName("car2");
        car2.setEngineId(engine2);

        carRepository.addCar(car);
        carRepository.addCar(car2);

        var post = new Post();
        post.setCreated(LocalDateTime.now());
        post.setDescription("post description");
        post.setUser(user);
        post.setCarId(car);

        var post2 = new Post();
        post2.setCreated(LocalDateTime.now());
        post2.setDescription("post description2");
        post2.setUser(user2);
        post2.setCarId(car2);
        post2.setPhoto(new byte[]{1, 2, 3});

        postRepository.addPost(post);
        postRepository.addPost(post2);

        List<Post> postsFromDB = postRepository.getPostsWithPhoto();
        assertThat(postsFromDB.size()).isEqualTo(1);
    }

    /**
     * Получить объявления по марке автомобиля.
     */
    @Test
    void whenAddPostsThenGetPostsOfSpecificBrand() {
        var crudRepository = new HibernateCrudRepository(sf);
        var postRepository = new HibernatePostRepository(crudRepository);
        var userRepository = new HibernateUserRepository(crudRepository);
        var engineRepository = new HibernateEngineRepository(crudRepository);
        var carRepository = new HibernateCarRepository(crudRepository);

        var user = new User();
        user.setLogin("user login");
        user.setPassword("password");

        var user2 = new User();
        user2.setLogin("user login2");
        user2.setPassword("password2");

        userRepository.addUser(user);
        userRepository.addUser(user2);

        var engine = new Engine();
        engine.setName("engine");

        var engine2 = new Engine();
        engine2.setName("engine2");

        engineRepository.addEngine(engine);
        engineRepository.addEngine(engine2);

        var car = new Car();
        car.setName("car");
        car.setEngineId(engine);

        var car2 = new Car();
        car2.setName("car2");
        car2.setEngineId(engine2);

        carRepository.addCar(car);
        carRepository.addCar(car2);

        var post = new Post();
        post.setCreated(LocalDateTime.now());
        post.setDescription("post description");
        post.setUser(user);
        post.setCarId(car);

        var post2 = new Post();
        post2.setCreated(LocalDateTime.now());
        post2.setDescription("post description2");
        post2.setUser(user2);
        post2.setCarId(car2);
        post2.setPhoto(new byte[]{1, 2, 3});

        postRepository.addPost(post);
        postRepository.addPost(post2);

        List<Post> postsFromDB = postRepository.getPostsOfSpecificBrand("car2");
        assertThat(postsFromDB.size()).isEqualTo(1);
        assertThat(postsFromDB.get(0).getCarId().getName()).isEqualTo(car2.getName());
    }
}