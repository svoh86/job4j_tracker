package ru.job4j.toone;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

/**
 * @author Svistunov Mikhail
 * @version 1.0
 */
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "j_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int id;
    private String name;
    /**
     * В модели "Пользователь" есть поле Role. Это поле содержит целый объект.
     * Hibernate загрузит этот объект самостоятельно.
     * Аннотации указывают, что связь между таблицами - many-to-one
     * и указывают по какому полю идет связь.
     */
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;
}
