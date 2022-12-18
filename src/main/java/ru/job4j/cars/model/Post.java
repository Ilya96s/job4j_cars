package ru.job4j.cars.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Post - модель данных
 *
 * @author Ilya Kaltygin
 */
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "auto_post")
public class Post {

    /**
     * id поста.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int id;

    /**
     * описание поста.
     */
    private String description;

    /**
     * дата создания.
     */
    private LocalDateTime created;

    /**
     * автор поста.
     */
    @ManyToOne()
    @JoinColumn(name = "auto_user_id")
    private User user;
}
