package ru.job4j.cars.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.Post;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * HibernatePostRepository - реализация хранилища объявлений
 *
 * @author Ilya Kaltygin
 */
@Repository
@AllArgsConstructor
public class HibernatePostRepository implements PostRepository {

    private static final String FIND_POST_FROM_LAST_DAY = """
            From Post as post
            WHERE post.created >= :time
            """;

    private static final String FIND_POST_WITH_PHOTO = """
            From Post as post
            WHERE post.photo IS NOT NULL
            """;

    private static final String FIND_POST_BY_BRAND_CAR = """
            From Post as post
            JOIN FETCH post.carId
            WHERE post.carId.name = :brandCar
            """;

    /**
     * Объект типа CrudRepository
     */
    private final CrudRepository crudRepository;

    /**
     * Получить объявления за последний день.
     * @return список объявлений
     */
    @Override
    public List<Post> getPostsFromLastDay() {
        return crudRepository.queryAndGetList(FIND_POST_FROM_LAST_DAY, Post.class,
                Map.of("time", LocalDateTime.now().minusDays(1)));
    }

    /**
     * Получить объявления с фото.
     * @return список объявлений
     */
    @Override
    public List<Post> getPostsWithPhoto() {
        return crudRepository.queryAndGetList(FIND_POST_WITH_PHOTO, Post.class);
    }

    /**
     * Получить объявления определенной марки.
     * @param brandCar марка машины.
     * @return список объявлений.
     */
    @Override
    public List<Post> getPostsOfSpecificBrand(String brandCar) {
        return crudRepository.queryAndGetList(FIND_POST_BY_BRAND_CAR, Post.class,
                Map.of("brandCar", brandCar));
    }
}
