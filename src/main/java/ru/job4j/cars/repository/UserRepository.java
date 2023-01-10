package ru.job4j.cars.repository;

import ru.job4j.cars.model.User;
import java.util.List;
import java.util.Optional;

/**
 * UserRepository - хранилище пользователей
 *
 * @author Ilya Kaltygin
 */

public interface UserRepository {

    /**
     * Сохранить пользователя в базе данных.
     * @param user пользователь.
     * @return пользователь с id.
     */
    Optional<User> addUser(User user);

    /**
     * обновить в базе пользователя.
     * @param user пользователь.
     */
    void update(User user);

    /**
     * удалить пользователя по id.
     * @param userId id.
     */
    void delete(int userId);

    /**
     * список пользователей отсортированных по id.
     * @return список пользователй.
     */
    List<User> findAllOrderById();

    /**
     * найти пользователя по id.
     * @param id id.
     * @return Optional или пользователь.
     */
    Optional<User> findById(int id);

    /**
     * список пользователей по login LIKE %key%.
     * @param key key.
     * @return список пользователй.
     */
    List<User> findByLikeLogin(String key);

    /**
     * найти пользователя по login.
     * @param login login.
     * @return Optional или пользователь
     */
    Optional<User> findByLogin(String login);
}
