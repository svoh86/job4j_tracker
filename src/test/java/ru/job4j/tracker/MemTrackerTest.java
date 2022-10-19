package ru.job4j.tracker;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MemTrackerTest {
    @Test
    public void whenAddNewItemThenTrackerHasSameItem() {
        Store memTracker = new MemTracker();
        Item item = new Item();
        item.setName("test1");
        memTracker.add(item);
        Item result = memTracker.findById(item.getId());
        assertThat(result.getName(), is(item.getName()));
    }

    @Test
    public void whenTestFindById() {
        Store memTracker = new MemTracker();
        Item bug = new Item("Bug");
        Item item = memTracker.add(bug);
        Item result = memTracker.findById(item.getId());
        assertThat(result.getName(), is(item.getName()));
    }

    @Test
    public void whenTestFindAll() {
        Store memTracker = new MemTracker();
        Item first = new Item("First");
        Item second = new Item("Second");
        memTracker.add(first);
        memTracker.add(second);
        Item result = memTracker.findAll().get(0);
        assertThat(result.getName(), is(first.getName()));
    }

    @Test
    public void whenTestFindByNameCheckArrayLength() {
        Store memTracker = new MemTracker();
        Item first = new Item("First");
        Item second = new Item("Second");
        memTracker.add(first);
        memTracker.add(second);
        memTracker.add(new Item("First"));
        memTracker.add(new Item("Second"));
        memTracker.add(new Item("First"));
        List<Item> result = memTracker.findByName(first.getName());
        assertThat(result.size(), is(3));
    }

    @Test
    public void whenTestFindByNameCheckSecondItemName() {
        Store memTracker = new MemTracker();
        Item first = new Item("First");
        Item second = new Item("Second");
        memTracker.add(first);
        memTracker.add(second);
        memTracker.add(new Item("First"));
        memTracker.add(new Item("Second"));
        memTracker.add(new Item("First"));
        List<Item> result = memTracker.findByName(second.getName());
        assertThat(result.get(1).getName(), is(second.getName()));
    }

    @Test
    public void whenReplace() {
        Store memTracker = new MemTracker();
        Item bug = new Item();
        bug.setName("Bug");
        memTracker.add(bug);
        int id = bug.getId();
        Item bugWithDesc = new Item();
        bugWithDesc.setName("Bug with description");
        memTracker.replace(id, bugWithDesc);
        assertThat(memTracker.findById(id).getName(), is("Bug with description"));
    }

    @Test
    public void whenDelete() {
        Store memTracker = new MemTracker();
        Item bug = new Item();
        bug.setName("Bug");
        memTracker.add(bug);
        int id = bug.getId();
        memTracker.delete(id);
        assertThat(memTracker.findById(id), is(nullValue()));
    }

    @Test
    public void whenReplaceWithMock() {
        Output out = new StubOutput();
        MemTracker tracker = new MemTracker();
        tracker.add(new Item("Replaced item"));
        String replacedName = "New item name";
        EditAction rep = new EditAction(out);
        Input input = mock(Input.class);
        when(input.askInt(any(String.class))).thenReturn(1);
        when(input.askStr(any(String.class))).thenReturn(replacedName);
        rep.execute(input, tracker);
        String ln = System.lineSeparator();
        assertThat(out.toString(), is("=== Edit item ===" + ln + "Заявка изменена успешно." + ln));
        assertThat(tracker.findAll().get(0).getName(), is(replacedName));
    }

    @Test
    public void whenNotReplaceWithMock() {
        Output out = new StubOutput();
        MemTracker tracker = new MemTracker();
        tracker.add(new Item("Replaced item"));
        String replacedName = "New item name";
        EditAction rep = new EditAction(out);
        Input input = mock(Input.class);
        rep.execute(input, tracker);
        String ln = System.lineSeparator();
        assertThat(out.toString(), is("=== Edit item ===" + ln + "Ошибка замены заявки." + ln));
        assertThat(tracker.findAll().get(0).getName(), is("Replaced item"));
    }

    @Test
    public void whenDeleteWithMock() {
        Output out = new StubOutput();
        MemTracker tracker = new MemTracker();
        tracker.add(new Item("Deleted item"));
        DeleteAction del = new DeleteAction(out);
        Input input = mock(Input.class);
        when(input.askInt(any(String.class))).thenReturn(1);
        del.execute(input, tracker);
        String ln = System.lineSeparator();
        assertThat(out.toString(), is("=== Delete item ===" + ln + "Заявка удалена успешно." + ln));
        assertThat(tracker.findAll().size(), is(0));
    }

    @Test
    public void whenNotDeleteWithMock() {
        Output out = new StubOutput();
        MemTracker tracker = new MemTracker();
        tracker.add(new Item("Deleted item"));
        DeleteAction del = new DeleteAction(out);
        Input input = mock(Input.class);
        del.execute(input, tracker);
        String ln = System.lineSeparator();
        assertThat(out.toString(), is("=== Delete item ===" + ln + "Ошибка удаления заявки." + ln));
        assertThat(tracker.findAll().size(), is(1));
    }

    @Test
    public void whenFindByIdWithMock() {
        Output out = new StubOutput();
        MemTracker tracker = new MemTracker();
        Item item = new Item("item");
        tracker.add(item);
        FindByIdAction find = new FindByIdAction(out);
        Input input = mock(Input.class);
        when(input.askInt(any(String.class))).thenReturn(1);
        find.execute(input, tracker);
        String ln = System.lineSeparator();
        assertThat(out.toString(), is("=== Find item by id ===" + ln + item + ln));
        assertThat(tracker.findAll().get(0), is(item));
    }

    @Test
    public void whenNotFindByIdWithMock() {
        Output out = new StubOutput();
        MemTracker tracker = new MemTracker();
        Item item = new Item("item");
        tracker.add(item);
        FindByIdAction find = new FindByIdAction(out);
        Input input = mock(Input.class);
        find.execute(input, tracker);
        String ln = System.lineSeparator();
        assertThat(out.toString(), is("=== Find item by id ===" + ln
                                      + "Заявка с введенным id: 0 не найдена." + ln));
    }

    @Test
    public void whenFindByNameWithMock() {
        Output out = new StubOutput();
        MemTracker tracker = new MemTracker();
        Item item = new Item("Second item");
        tracker.add(new Item("First item"));
        tracker.add(item);
        FindByNameAction find = new FindByNameAction(out);
        Input input = mock(Input.class);
        when(input.askStr(any(String.class))).thenReturn("Second item");
        find.execute(input, tracker);
        String ln = System.lineSeparator();
        assertThat(out.toString(), is("=== Find items by name ===" + ln + item + ln));
        assertThat(tracker.findAll().get(1).getName(), is("Second item"));
    }

    @Test
    public void whenNotFindByNameWithMock() {
        Output out = new StubOutput();
        MemTracker tracker = new MemTracker();
        Item item = new Item("Second item");
        tracker.add(new Item("First item"));
        tracker.add(item);
        FindByNameAction find = new FindByNameAction(out);
        Input input = mock(Input.class);
        when(input.askStr(any(String.class))).thenReturn("items");
        find.execute(input, tracker);
        String ln = System.lineSeparator();
        assertThat(out.toString(), is("=== Find items by name ===" + ln
                                      + "Заявки с именем: items не найдены." + ln));
    }
}