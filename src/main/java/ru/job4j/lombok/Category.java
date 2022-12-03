package ru.job4j.lombok;

import lombok.*;

/**
 * - для поля id сделать getter метод.
 * - для поля name сделать и getter и setter.
 * - метод hashCode и equals должны вычисляться по полю id
 * - в классе должен быть конструктор с полем id.
 *
 * @author Svistunov Mikhail
 * @version 1.0
 */
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@RequiredArgsConstructor
public class Category {
    @Getter
    @EqualsAndHashCode.Include
    @NonNull
    private int id;
    @Getter
    @Setter
    private String name;
}
