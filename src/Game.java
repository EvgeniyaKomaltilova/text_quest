import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Game {
    static boolean isStarted;
    static Location currentLocation;
    static Map<String,Location> locationMap = new HashMap<>();
    static List<String> inventory = new ArrayList<>();
    static Location bedRoom = new Location("в спальне.");
    static Location hallway = new Location("в коридоре.");
    static Location livingRoom = new Location("в гостиной.");
    static Location kitchen = new Location("на кухне.");
    static Location bathRoom = new Location("в ванной комнате.");
    static Location exit = new Location("");

    public static void main(String[] args) {
        locationInit();
        startGame();
        String s;
        while(isStarted) {
            s = getString();
            currentLocation.doAction(s);
            goToLocation(s);
        }
    }

    public static void startGame() {
        isStarted = true;
        System.out.println("Вы проснулись в чьей-то квартире.");
        goToLocation("Идти в спальню");
    }

    public static String getString() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String s = "Введите \"1\"";
        try {
            s =  reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return s;
    }

    public static void goToLocation(String key) {
        for (Map.Entry<String, Location> entry : locationMap.entrySet()) {
            if (key.equals("Выйти наружу!")) {
                System.out.println("Поздравляю! Вы выбрались!");
                isStarted = false;
                break;
            }
            if (key.equals(entry.getKey())) {
                currentLocation = entry.getValue();
                Location current = entry.getValue();
                System.out.println("Вы находитесь " + current.name);
                System.out.println("Вы можете:");
                for (String s : current.actions) {
                    System.out.println("* " + s);
                }
                for (Map.Entry<String, Boolean> currentEntry : current.ways.entrySet()) {
                    if (currentEntry.getValue()) {
                        System.out.println("-> " + currentEntry.getKey());
                    }
                }
            }
        }
    }

    public static void locationInit() {
        locationMap.put("Идти в спальню", bedRoom);
        locationMap.put("Идти в коридор", hallway);
        locationMap.put("Идти в гостиную", livingRoom);
        locationMap.put("Идти на кухню", kitchen);
        locationMap.put("Идти в ванную", bathRoom);
        locationMap.put("Выйти наружу!", exit);

        bedRoom.ways.put("Идти в коридор", true);
        bedRoom.items.add("ключ");
        bedRoom.actions.add("Обыскать комнату");

        hallway.ways.put("Идти в гостиную", true);
        hallway.ways.put("Идти в спальню", true);
        hallway.ways.put("Выйти наружу!", false);
        hallway.actions.add("Открыть дверь");

        livingRoom.ways.put("Идти на кухню", true);
        livingRoom.ways.put("Идти в коридор", true);

        kitchen.ways.put("Идти в ванную", true);
        kitchen.ways.put("Идти в гостиную", true);

        bathRoom.ways.put("Идти на кухню", true);
    }
}
