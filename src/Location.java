import java.util.*;

public class Location {
    String name;
    List<String> items;
    List<String> actions;
    Map<String, Boolean> ways;

    public Location(String name) {
        this.name = name;
        this.ways = new HashMap<>();
        this.actions = new ArrayList<>();
        this.items = new ArrayList<>();
    }

    public void searchItem() {
        if (this.items.size() != 0) {
            Game.inventory.add(this.items.get(0));
            System.out.println("Вы нашли " + this.items.get(0) + "!");
            this.items.remove(0);
        } else
            System.out.println("Вы ничего не нашли...");
    }

    public void openDoor() {
        if (Game.inventory.contains("ключ")) {
            System.out.println("Вы смогли открыть дверь!");
            Game.hallway.ways.replace("Выйти наружу!", false, true);
            Game.goToLocation("Идти в коридор");
        }
    }

    public void doAction(String key) {
        switch (key) {
            case "Обыскать комнату":
                this.searchItem();
                break;
            case "Открыть дверь":
                this.openDoor();
                break;
        }
    }
}
