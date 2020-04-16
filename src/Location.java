import java.util.*;

public class Location {
    public int x;
    public int y;
    String name;
    Set<String> items;
    Set<String> actions;

    public Location(Field field, int x, int y, String name) {
        this.x = x;
        this.y = y;
        this.name = name;
        field.gameField[y][x] = this;
    }

    public void addActions(String...strings) {
        for (String s : strings) {
            this.actions.add(s);
        }
    }

    public void addItems (String...strings) {
        for (String s : strings) {
            this.items.add(s);
        }
    }
}
