package ru.job4j.cars.repository;

import ru.job4j.cars.model.Post;

import java.util.List;
import java.util.Optional;

/**
 * PostRepository - хранилище объявлений
 *
 * @author Ilya Kaltygin
 */

public interface PostRepository {

    /**
     * Сохранить объявление в базе данных.
     * @param post объявление.
     * @return Optional.of(post) если успешно, иначе Optional.empty().
     */
    Optional<Post> addPost(Post post);

    /**
     * Получить объявления за последний день.
     * @return список объявлений
     */
    List<Post> getPostsFromLastDay();

    /**
     * Получить объявления с фото.
     * @return список объявлений
     */
    List<Post> getPostsWithPhoto();

    /**
     * Получить объявления определенной марки.
     * @param brandCar марка машины.
     * @return список объявлений.
     */
    List<Post> getPostsOfSpecificBrand(String brandCar);
}
