package ru.job4j.tracker;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

import java.util.List;

/**
 * @author Svistunov Mikhail
 * @version 1.0
 */
public class HQLUsage {
    public static void main(String[] args) {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try (SessionFactory sf = new MetadataSources(registry)
                .buildMetadata().buildSessionFactory()) {
            Session session = sf.openSession();
            Query<Item> query = session.createQuery("from Item as i where i.id = 12", Item.class);
            for (Object st : query.list()) {
                System.out.println(st);
            }
            System.out.println(query.uniqueResult());
            update(session, 12);
            findById(session, 12);
            delete(session, 13);
            findById(session, 13);
            Item item = new Item("created item");
            insert(session, item);
            List<Item> list = session.createQuery("from Item", Item.class).list();
            list.forEach(System.out::println);
            session.close();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }

    public static void findById(Session session, int id) {
        Query<Item> query = session.createQuery("from Item as i where i.id = :fId", Item.class);
        query.setParameter("fId", id);
        System.out.println(query.uniqueResult());
    }

    public static void update(Session session, int id) {
        try {
            session.beginTransaction();
            session.createQuery(
                            "UPDATE Item SET name = :fName WHERE id = :fId")
                    .setParameter("fName", "new name")
                    .setParameter("fId", id)
                    .executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        }
    }

    public static void delete(Session session, int id) {
        try {
            session.beginTransaction();
            session.createQuery(
                            "DELETE Item WHERE id = :fId")
                    .setParameter("fId", id)
                    .executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        }
    }

    public static void insert(Session session, Item item) {
        try {
            session.beginTransaction();
            session.save(item);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        }
    }
}
