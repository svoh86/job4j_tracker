package ru.job4j.tracker;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Важно!!! Метод atZone() добавляет только метку к дате о том, что у даты есть часовой пояс.
 * Однако значение самой даты с учетом этого часового пояса он не изменяет.
 * Для того, чтобы выполнить изменение даты необходимо использовать метод withZoneSameInstant(ZoneId zone)
 *
 * @author Svistunov Mikhail
 * @version 1.0
 */
public class DateRun {
    public static void main(String[] args) {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try (SessionFactory sf = new MetadataSources(registry)
                .buildMetadata().buildSessionFactory()) {
            Session session = sf.openSession();
            session.beginTransaction();
            Item item = new Item("check timezone");
            session.persist(item);

            List<Item> stored = session.createQuery(
                    "from Item", Item.class
            ).list();
            for (Item it : stored) {
                String time = it.getCreated()
                        .atZone(ZoneId.of("UTC"))
                        .withZoneSameInstant(ZoneId.of("Asia/Irkutsk"))
                        .format(DateTimeFormatter.ofPattern("HH:mm yyyy-MM-dd"));
                System.out.println(time);
            }

            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}
