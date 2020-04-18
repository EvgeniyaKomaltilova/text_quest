import java.util.*;

public class Location {
    String name;
    List<String> items;
    Map<String, Boolean> actions;
    Map<String, Boolean> ways;
    boolean isOpen;
    String firstMessage;
    List<String> messages;

    public Location(String name) {
        this.name = name;
        this.ways = new HashMap<>();
        this.actions = new HashMap<>();
        this.items = new ArrayList<>();
        this.messages = new ArrayList<>();
    }

    public void searchItem() {
        if (this.items.size() != 0) {
            String item = this.items.get(0);
            Game.inventory.add(item);
            System.out.println("Вы нашли " + item + "!");
            switch (item) {
                case "ключ" :
                    Game.hallway.actions.replace("Открыть дверь", false, true);
                    break;
                case "зубную щетку" :
                    Game.bathRoom.actions.replace("Почистить зубы", false, true);
                    break;
            }
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
