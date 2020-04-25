import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Person {
    private static Person instance = null;
    List<String> inventory;
    List<String> personActions;
    Map<String, Integer> variableParameters;

    private Person() {
        this.inventory = new ArrayList<>();
        this.variableParameters = new HashMap<>();
        this.personActions = new ArrayList<>();
    }

    public static Person getInstance() {
        if (instance == null) {
            instance = new Person();
        }
        return instance;
    }

    public void addVariableParameter(String parameter, int startValue) {
        this.variableParameters.put(parameter,startValue);
    }

    public void useItem(String key) {
        switch (key) {
            case "Поесть":
                this.ateFood();
                break;
        }
    }

    public void ateFood() {
        System.out.println("Кажется, вы наелись. Можно жить!");
    }
}
