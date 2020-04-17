import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLOutput;
import java.util.*;

public class Game {
    static boolean isStarted;
    static Map<String,Location> locationMap = new HashMap<>();
    static Location bedRoom = new Location("в спальне.");
    static Location hallway = new Location("в коридоре.");
    static Location livingRoom = new Location("в гостиной.");
    static Location kitchen = new Location("на кухне.");
    static Location bathRoom = new Location("в ванной комнате.");

    public static void main(String[] args) {
        locationInit();
        startGame();
        String s;
        while(isStarted) {
            s = getString();
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
            if (key.equals(entry.getKey())) {
                System.out.println("Вы находитесь " + entry.getValue().name);
                System.out.println("Вы можете:");
                for (String s : entry.getValue().actions) {
                    System.out.println("* " + s);
                }
                for (String o : entry.getValue().ways.keySet()) {
                    System.out.println("-> " + o);
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
        bedRoom.ways.put("Идти в коридор", hallway);
        hallway.ways.put("Идти в гостиную", livingRoom);
        hallway.ways.put("Идти в спальню", bedRoom);
        livingRoom.ways.put("Идти на кухню", kitchen);
        livingRoom.ways.put("Идти в коридор", hallway);
        kitchen.ways.put("Идти в ванную", bathRoom);
        kitchen.ways.put("Идти в гостиную", livingRoom);
        bathRoom.ways.put("Идти на кухню", kitchen);
    }
}
