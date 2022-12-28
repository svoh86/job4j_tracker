package ru.job4j.toone;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.List;

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
    /**
     * Для связи one-to-many обязательно нужно указывать колонку для вторичного ключа.
     * Если это не сделать, то hibernate будет создавать отдельную таблицу,
     * а не использовать нашу схему.
     */
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "j_user_id")
    private List<UserMessenger> messengers;
}
