package ru.job4j.tracker;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ru.job4j.toone.User;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Entity
@Table(name = "items")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @EqualsAndHashCode.Exclude
    private LocalDateTime created = LocalDateTime.now();
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MMMM-EEEE-yyyy HH:mm:ss");
    /**
     * Разберем аннотацию @JoinTable:
     * name - указывает на таблицу, где идет связь вторичных ключей.
     * joinColumns - определяет ключ родительского объекта. В данном примере Item.id
     * inverseJoinColumns - определяет ключ объекта, который мы загружаем в родительский объект.
     */
    @ManyToMany
    @JoinTable(
            name = "participates",
            joinColumns = {@JoinColumn(name = "item_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id")}
    )
    private List<User> participates;

    public Item(String name) {
        this.name = name;
    }

    public Item(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Item(int id, String name, LocalDateTime created) {
        this.id = id;
        this.name = name;
        this.created = created;
    }

    @Override
    public String toString() {
        return "Item{"
               + "id=" + id
               + ", name='" + name + '\''
               + ", created=" + created.format(FORMATTER)
               + '}';
    }
}