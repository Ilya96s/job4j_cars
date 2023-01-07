package ru.job4j.cars.repository;

import ru.job4j.cars.model.Post;

import java.util.List;

/**
 * PostRepository - хранилище объявлений
 *
 * @author Ilya Kaltygin
 */

public interface PostRepository {

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
