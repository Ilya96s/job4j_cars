package ru.job4j.cars.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * PriceHistory - модель данных, описывающая историю цен
 *
 * @author Ilya Kaltygin
 */
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "price_history")
public class PriceHistory {

    /**
     * id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int id;

    /**
     * цена до.
     */
    private long before;

    /**
     * цена после.
     */
    private long after;

    /**
     * дата создания.
     */
    private LocalDateTime created;

    /**
     * пост, к которому относится история с ценами
     */
    @ManyToOne()
    @JoinColumn(name = "post_id")
    private Post post;
}
