import java.util.*;

public class Location {
    public int x;
    public int y;
    String name;
    Set<String> items;
    List<String> actions;

    public Location(int x, int y, String name) {
        this.x = x;
        this.y = y;
        this.name = name;
    }

    public void addActions(String...strings) {
        List<String> result = new ArrayList<>();
        for (String s : strings) {
            this.actions = result;
        }
    }

    public void addItems (String...strings) {
        Set<String> result = new HashSet<>();
        for (String s : strings) {
            this.items = result;
        }
    }
}
