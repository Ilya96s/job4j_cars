package ru.job4j.cars.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.persistence.*;

/**
 * User - модель данных
 *
 * @author Ilya Kaltygin
 */
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "Auto_user")
public class User {

    /**
     * id пользователя.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int id;

    /**
     * логин пользователя.
     */
    private String login;

    /**
     * пароль пользователя.
     */
    private String password;
}
