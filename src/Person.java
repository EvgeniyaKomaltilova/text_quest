import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Person {
    private static Person instance = null;
    List<String> inventory = new ArrayList<>();
    Map<String, Integer> parameters;

    private Person() {
        this.parameters = new HashMap<>();
    }

    public static Person getInstance() {
        if (instance == null) {
            instance = new Person();
        }
        return instance;
    }

    public void addParameter(String s, int i) {
        this.parameters.put(s,i);
    }
}
