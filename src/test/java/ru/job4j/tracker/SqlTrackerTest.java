package ru.job4j.tracker;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.nullValue;

public class SqlTrackerTest {
    private static Connection connection;

    /**
     * В методе выполняется инициализация подключения.
     * Данный метод обозначен аннотацией @BeforeClass,
     * т.е. метод выполняется один раз до начала тестов.
     */
    @BeforeClass
    public static void initConnection() {
        try (InputStream in = SqlTrackerTest.class.getClassLoader().getResourceAsStream("test.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            connection = DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")
            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    /**
     * В методе выполняется закрытие подключения.
     * Данный метод обозначен аннотацией @AfterClass,
     * т.е. метод выполняется один раз после тестов.
     *
     * @throws SQLException исключение
     */
    @AfterClass
    public static void closeConnection() throws SQLException {
        connection.close();
    }

    /**
     * В методе мы чистим таблицу items после внесенных изменений.
     * Иначе изменения, внесенные один тестом, будут видны другому.
     * Данный метод обозначен аннотацией @After,
     * т.е. метод выполняется после каждого теста.
     *
     * @throws SQLException исключение
     */
    @After
    public void wipeTable() throws SQLException {
        try (PreparedStatement pr = connection.prepareStatement("delete from items")) {
            pr.execute();
        }
    }

    @Test
    public void whenSaveItemAndFindByGeneratedIdThenMustBeTheSame() {
        SqlTracker tracker = new SqlTracker(connection);
        Item item = new Item("item");
        tracker.add(item);
        assertThat(tracker.findById(item.getId()), is(item));
    }

    @Test
    public void whenSaveItemAndReplaceAndFindByGeneratedIdThenMustBeTheSame() {
        SqlTracker tracker = new SqlTracker(connection);
        Item item = new Item("item");
        tracker.add(item);
        Item newItem = new Item(item.getId(), "newItem");
        tracker.replace(item.getId(), newItem);
        assertThat(tracker.findById(item.getId()), is(newItem));
    }

    @Test
    public void whenSaveItemAndDeleteAndFindByGeneratedIdThenMustBeNull() {
        SqlTracker tracker = new SqlTracker(connection);
        Item item = new Item("item");
        tracker.add(item);
        tracker.delete(item.getId());
        assertThat(tracker.findById(item.getId()), is(nullValue()));
    }

    @Test
    public void whenSaveItemsAndFindAllFirstAndSecondMustBeTheSame() {
        SqlTracker tracker = new SqlTracker(connection);
        Item first = new Item("first");
        Item second = new Item("second");
        tracker.add(first);
        tracker.add(second);
        assertThat(tracker.findAll().get(0), is(first));
        assertThat(tracker.findAll().get(1), is(second));
    }

    @Test
    public void whenSaveItemAndFindByNameAndFindByGeneratedIdThenMustBeTheSame() {
        SqlTracker tracker = new SqlTracker(connection);
        Item item = new Item("item");
        tracker.add(item);
        List<Item> find = tracker.findByName("item");
        assertThat(tracker.findById(item.getId()), is(find.get(0)));
    }

    @Test
    public void whenSaveItemAndFindByNameAndCheckSizeList() {
        SqlTracker tracker = new SqlTracker(connection);
        Item first = new Item("first");
        Item second = new Item("second");
        Item third = new Item("second");
        tracker.add(first);
        tracker.add(second);
        tracker.add(third);
        List<Item> find = tracker.findByName("second");
        assertThat(find.size(), is(2));
    }

    @Test
    public void whenSaveItemAndFindByGeneratedIdThenNameMustBeTheSame() {
        SqlTracker tracker = new SqlTracker(connection);
        Item item = new Item("item");
        tracker.add(item);
        assertThat(tracker.findById(item.getId()).getName(), is("item"));
    }
}