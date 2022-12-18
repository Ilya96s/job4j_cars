package ru.job4j.cars.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

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

    /**
     * список пользователей.
     */
    @ManyToMany
    @JoinTable(
            name = "participates",
            joinColumns = { @JoinColumn(name = "post_id")},
            inverseJoinColumns = { @JoinColumn(name = "user_id")})
    private List<User> participates;
}
