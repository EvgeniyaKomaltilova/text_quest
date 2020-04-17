import java.util.*;

public class Location {
    String name;
    Set<String> items;
    List<String> actions;
    Map<String, Boolean> ways;

    public Location(String name, String...strings) {
        this.name = name;
        this.actions = addActions(strings);
        this.ways = new HashMap<>();
        this.actions = new ArrayList<>();
    }

    public List<String> addActions(String...strings) {
        List<String> result = new ArrayList<>();
        for (String s : strings) {
            result.add(s);
        }
        return result;
    }

    public void addItems (String...strings) {
        Set<String> result = new HashSet<>();
        for (String s : strings) {
            this.items = result;
        }
    }
}
