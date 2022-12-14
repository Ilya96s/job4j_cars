package ru.job4j.cars.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.User;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * UserRepository - реализация хранилища пользователей.
 *
 * @author Ilya Kaltygin
 */

@Repository
@AllArgsConstructor
public class HibernateUserRepository implements UserRepository {

    private static final String DELETE = "DELETE From User WHERE id = :id";

    private static final String FIND_ALL_ORDER_BY = "From User as user ORDER BY user.id";

    private static final String FIND_BY_ID = "From User WHERE id = :id";

    private static final String FIND_BY_LIKE_LOGIN = "From User WHERE login LIKE :key";

    private static final String FIND_BY_LOGIN = "From User WHERE login = :login";

    /**
     * Объект типа CrudRepository
     */
    private final CrudRepository crudRepository;

    /**
     * Сохранить пользователя в базе данных.
     * @param user пользователь.
     * @return пользователь с id.
     */
    @Override
    public Optional<User> addUser(User user) {
        crudRepository.run(session -> session.save(user));
        return Optional.of(user);
    }

    /**
     * обновить в базе пользователя.
     * @param user пользователь.
     */
    @Override
    public void update(User user) {
        crudRepository.run(session -> session.merge(user));
    }

    /**
     * удалить пользователя по id.
     * @param userId id.
     */
    @Override
    public void delete(int userId) {
        crudRepository.run(DELETE, Map.of("id", userId));
    }

    /**
     * список пользователей отсортированных по id.
     * @return список пользователй.
     */
    @Override
    public List<User> findAllOrderById() {
        return crudRepository.queryAndGetList(FIND_ALL_ORDER_BY, User.class);
    }

    /**
     * найти пользователя по id.
     * @param id id.
     * @return Optional или пользователь.
     */
    @Override
    public Optional<User> findById(int id) {
        return crudRepository.queryAndGetOptional(
                FIND_BY_ID, User.class,
                Map.of("id", id));
    }

    /**
     * список пользователей по login LIKE %key%.
     * @param key key.
     * @return список пользователй.
     */
    @Override
    public List<User> findByLikeLogin(String key) {
        return crudRepository.queryAndGetList(
                FIND_BY_LIKE_LOGIN, User.class,
                Map.of("key", "%" + key + "%"));
    }

    /**
     * найти пользователя по login.
     * @param login login.
     * @return Optional или пользователь
     */
    @Override
    public Optional<User> findByLogin(String login) {
        return crudRepository.queryAndGetOptional(
                FIND_BY_LOGIN, User.class,
                Map.of("login", login));
    }
}
