package ru.job4j.tracker;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class StartUITest {

    /*@Test
    public void whenCreateItem() {
        Tracker tracker = new Tracker();
        Input in = new StubInput(new String[]{"0", "New item", "1"});
        UserAction[] actions = {new CreateAction(), new ExitAction()};
        new StartUI().init(in, tracker, actions);
        assertThat(tracker.findAll()[0].getName(), is("New item"));
    }*/

    @Test
    public void whenReplaceItemTestOutputIsSuccessfully() {
        Output output = new StubOutput();
        Store memTracker = new MemTracker();
        Item item = memTracker.add(new Item("Replaced item"));
        String replacedName = "New item name";
        Input in = new StubInput(new String[]{"0", String.valueOf(item.getId()), replacedName, "1"});
        List<UserAction> actions = List.of(new EditAction(output), new ExitAction(output));
        String ln = System.lineSeparator();
        new StartUI(output).init(in, memTracker, actions);
        assertThat(output.toString(), is(
                "Menu." + ln
                        + "0. Edit item" + ln
                        + "1. Exit Program" + ln
                        + "=== Edit item ===" + ln
                        + "Заявка изменена успешно." + ln
                        + "Menu." + ln
                        + "0. Edit item" + ln
                        + "1. Exit Program" + ln
                        + "===Exit Program===" + ln
        ));
    }

    @Test
    public void whenShowAllItemsTestOutputIsSuccessfully() {
        Output output = new StubOutput();
        Store memTracker = new MemTracker();
        Item item = memTracker.add(new Item("New item"));
        Input in = new StubInput(new String[]{"0", "1"});
        List<UserAction> actions = List .of(new ShowAllAction(output), new ExitAction(output));
        String ln = System.lineSeparator();
        new StartUI(output).init(in, memTracker, actions);
        assertThat(output.toString(), is(
                "Menu." + ln
                        + "0. Show all items" + ln
                        + "1. Exit Program" + ln
                        + "=== Show all items ===" + ln
                        + item + ln
                        + "Menu." + ln
                        + "0. Show all items" + ln
                        + "1. Exit Program" + ln
                        + "===Exit Program===" + ln
        ));
    }

    @Test
    public void whenFindByNameTestOutputIsSuccessfully() {
        Output output = new StubOutput();
        Store memTracker = new MemTracker();
        Item item = memTracker.add(new Item("New"));
        Input in = new StubInput(new String[]{"0", item.getName(), "1"});
        List<UserAction> actions = List.of(new FindByNameAction(output), new ExitAction(output));
        String ln = System.lineSeparator();
        new StartUI(output).init(in, memTracker, actions);
        assertThat(output.toString(), is(
                "Menu." + ln
                        + "0. Find items by name" + ln
                        + "1. Exit Program" + ln
                        + "=== Find items by name ===" + ln
                        + item + ln
                        + "Menu." + ln
                        + "0. Find items by name" + ln
                        + "1. Exit Program" + ln
                        + "===Exit Program===" + ln
        ));
    }

    @Test
    public void whenFindByIdTestOutputIsSuccessfully() {
        Output output = new StubOutput();
        Store memTracker = new MemTracker();
        Item item = memTracker.add(new Item("New"));
        Input in = new StubInput(new String[]{"0", String.valueOf(item.getId()), "1"});
        List<UserAction> actions = List.of(new FindByIdAction(output), new ExitAction(output));
        String ln = System.lineSeparator();
        new StartUI(output).init(in, memTracker, actions);
        assertThat(output.toString(), is(
                "Menu." + ln
                        + "0. Find item by id" + ln
                        + "1. Exit Program" + ln
                        + "=== Find item by id ===" + ln
                        + item + ln
                        + "Menu." + ln
                        + "0. Find item by id" + ln
                        + "1. Exit Program" + ln
                        + "===Exit Program===" + ln
        ));
    }

    @Test
    public void whenDeleteItemOutputIsSuccessfully() {
        Output output = new StubOutput();
        Store memTracker = new MemTracker();
        Item item = memTracker.add(new Item("Deleted item"));
        Input in = new StubInput(new String[]{"0", String.valueOf(item.getId()), "1"});
        List<UserAction> actions = List.of(new DeleteAction(output), new ExitAction(output));
        String ln = System.lineSeparator();
        new StartUI(output).init(in, memTracker, actions);
        assertThat(output.toString(), is(
                "Menu." + ln
                        + "0. Delete item" + ln
                        + "1. Exit Program" + ln
                        + "=== Delete item ===" + ln
                        + "Заявка удалена успешно." + ln
                        + "Menu." + ln
                        + "0. Delete item" + ln
                        + "1. Exit Program" + ln
                        + "===Exit Program===" + ln
        ));
    }

    @Test
    public void whenExit() {
        Output out = new StubOutput();
        Input in = new StubInput(new String[]{"0"});
        Store memTracker = new MemTracker();
        List<UserAction> actions = List.of(new ExitAction(out));
        new StartUI(out).init(in, memTracker, actions);
        assertThat(out.toString(), is("Menu." + System.lineSeparator()
                + "0. Exit Program" + System.lineSeparator()
                + "===Exit Program===" + System.lineSeparator()));
    }

    @Test
    public void whenInvalidExit() {
        Output out = new StubOutput();
        Input in = new StubInput(new String[]{"8", "0"});
        Store memTracker = new MemTracker();
        List<UserAction> actions = List.of(new ExitAction(out));
        String ln = System.lineSeparator();
        new StartUI(out).init(in, memTracker, actions);
        assertThat(out.toString(), is(
                "Menu." + ln
                        + "0. Exit Program" + ln
                        + "Wrong input, you can select: 0 .. 0" + ln
                        + "Menu." + ln
                        + "0. Exit Program" + ln
                        + "===Exit Program===" + ln));
    }
}