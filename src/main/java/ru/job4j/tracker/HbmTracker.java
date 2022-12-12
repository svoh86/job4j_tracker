package ru.job4j.tracker;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Svistunov Mikhail
 * @version 1.0
 */
public class HbmTracker implements Store, AutoCloseable {
    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();
    private final SessionFactory sf = new MetadataSources(registry)
            .buildMetadata().buildSessionFactory();
    private static final String UPDATE = "UPDATE Item SET name = :fName, created = :fCreated WHERE id = :fId";
    private static final String DELETE = "DELETE Item WHERE id = :fId";
    private static final String FIND_ALL = "from Item";
    private static final String FIND_BY_NAME = "from Item WHERE name = :fName";
    private static final String FIND_BY_ID = "from Item WHERE id = :fId";

    @Override
    public Item add(Item item) {
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            session.save(item);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return item;
    }

    @Override
    public boolean replace(int id, Item item) {
        boolean rsl = false;
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            session.createQuery(UPDATE)
                    .setParameter("fName", item.getName())
                    .setParameter("fCreated", LocalDateTime.now())
                    .setParameter("fId", id)
                    .executeUpdate();
            session.getTransaction().commit();
            rsl = true;
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return rsl;
    }

    @Override
    public boolean delete(int id) {
        boolean rsl = false;
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            session.createQuery(DELETE)
                    .setParameter("fId", id)
                    .executeUpdate();
            session.getTransaction().commit();
            rsl = true;
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return rsl;
    }

    @Override
    public List<Item> findAll() {
        Session session = sf.openSession();
        Query<Item> query = session.createQuery(FIND_ALL, Item.class);
        List<Item> itemList = query.list();
        session.close();
        return itemList;
    }

    @Override
    public List<Item> findByName(String key) {
        Session session = sf.openSession();
        Query<Item> query = session.createQuery(FIND_BY_NAME, Item.class)
                .setParameter("fName", key);
        List<Item> itemList = query.list();
        session.close();
        return itemList;
    }

    @Override
    public Item findById(int id) {
        Session session = sf.openSession();
        Query<Item> query = session.createQuery(FIND_BY_ID, Item.class)
                .setParameter("fId", id);
        Item item = query.uniqueResult();
        session.close();
        return item;
    }

    @Override
    public void close() throws Exception {
        StandardServiceRegistryBuilder.destroy(registry);
    }
}
