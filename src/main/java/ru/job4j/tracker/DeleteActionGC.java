package ru.job4j.tracker;

import java.util.List;

public class DeleteActionGC implements UserAction {
    private final Output out;

    public DeleteActionGC(Output out) {
        this.out = out;
    }

    @Override
    public String name() {
        return "Delete item for test GC";
    }

    @Override
    public boolean execute(Input input, Store tracker) {
        out.println("=== Delete item ===");
        List<Item> items = tracker.findAll();
        items.forEach(i -> tracker.delete(i.getId()));
        out.println("Заявки удалены успешно.");
        return true;
    }
}
