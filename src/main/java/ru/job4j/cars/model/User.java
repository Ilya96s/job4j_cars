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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int id;

    private String login;

    private String password;
}