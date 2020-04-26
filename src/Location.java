import java.util.*;

public class Location {
    String yourLocationIs;
    String wayToThis;
    List<String> items;
    Map<String, Boolean> actions;
    Map<String, Boolean> ways;
    boolean isOpen;
    String firstMessage;
    List<String> messages;

    public Location(String wayToThis, String yourLocationIs) {
        this.wayToThis = wayToThis;
        this.yourLocationIs = yourLocationIs;
        this.ways = new HashMap<>();
        this.actions = new HashMap<>();
        this.items = new ArrayList<>();
        this.messages = new ArrayList<>();
        Game.locationMap.put(wayToThis, this);
    }

    private void searchItem() {
        if (this.items.size() > 0) {
            String item = this.items.get(0);
            Game.person.inventory.add(item);
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

    private void openDoor() {
        if (Game.person.inventory.contains("ключ")) {
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

    public void addWays(Location...locations) {
        for (Location loc : locations) {
            this.ways.put(loc.wayToThis, true);
            loc.ways.put(this.wayToThis, true);
        }
    }

    public void addClosedWay(Location...locations) {
        for (Location loc : locations) {
            this.ways.put(loc.wayToThis, false);
            loc.ways.put(this.wayToThis, false);
        }
    }

    public void addAction(String...strings) {
        for (String s : strings) {
            this.actions.put(s, true);
        }
    }

    public void addInaccessibleAction(String...strings) {
        for (String s : strings) {
            this.actions.put(s, false);
        }
    }

    public void addMessage(String...strings) {
        this.messages.addAll(Arrays.asList(strings));
    }

    public void addItems(String...strings) {
        this.items.addAll(Arrays.asList(strings));
    }
}
