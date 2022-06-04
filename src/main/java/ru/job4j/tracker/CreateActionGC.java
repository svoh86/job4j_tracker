package ru.job4j.tracker;

public class CreateActionGC implements UserAction {
    private final Output out;

    public CreateActionGC(Output out) {
        this.out = out;
    }

    @Override
    public String name() {
        return "Add new Item for test GC";
    }

    @Override
    public boolean execute(Input input, Store tracker) {
        out.println("=== Create a new Item ===");
        for (int i = 0; i < 500_000; i++) {
            tracker.add(new Item("item" + (i + 1)));
        }
        out.println("=== Заявки добавлены ===");
        return true;
    }
}
