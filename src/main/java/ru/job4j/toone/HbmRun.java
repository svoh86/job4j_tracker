package ru.job4j.toone;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;

/**
 * @author Svistunov Mikhail
 * @version 1.0
 */
public class HbmRun {
    public static void main(String[] args) {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try {
            SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Role role = new Role();
            role.setName("ADMIN");
            create(role, sf);
            User user = new User();
            user.setName("Admin Admin");
            user.setMessengers(List.of(
                    new UserMessenger(0, "tg", "@tg"),
                    new UserMessenger(0, "wu", "@wu")
            ));
            user.setRole(role);
            create(user, sf);

            User stored = sf.openSession()
                    .createQuery("from User where id = :fId", User.class)
                    .setParameter("fId", user.getId())
                    .getSingleResult();
            stored.getMessengers().forEach(System.out::println);
            /**
            findAll(User.class, sf)
                    .forEach(System.out::println);
             */
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }

    public static <T> void create(T model, SessionFactory sf) {
        Session session = sf.openSession();
        session.beginTransaction();
        session.persist(model);
        session.getTransaction().commit();
        session.close();
    }

    public static <T> List<T> findAll(Class<T> cl, SessionFactory sf) {
        Session session = sf.openSession();
        session.beginTransaction();
        List<T> list = session.createQuery("from " + cl.getName(), cl).list();
        session.getTransaction().commit();
        session.close();
        return list;
    }
}
