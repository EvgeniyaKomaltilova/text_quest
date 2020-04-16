import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLOutput;
import java.util.*;

public class Game {
    static Map<String,Location> locationMap = new HashMap<>();
    static Location bedRoom = new Location(0, 0, "Вы находитесь в спальне.");
    static Location hallway = new Location(1, 0, "Вы находитесь в коридоре.");
    static Location livingRoom = new Location(1, 1, "Вы находитесь в гостиной.");
    static Location kitchen = new Location(2, 1, "Вы находитесь в кухне.");
    static Location bathRoom = new Location(2,0, "Вы находитесь в ванной комнате.");

    public static void main(String[] args) {
        locationInit();
        startGame();
        String s1 = getString();
        if (s1.equals("1")) {
            goToLocation("Идти в спальню");
        }
    }

    public static void startGame() {
        System.out.println("Доброе утро! Какое счастье, что вы наконец-то проснулись! \nЯ Гарви, ваш умный помощник. Кажется, вчера вы немного перебрали...\nПопробуйте для начала найти ванную комнату, чтобы умыться.");
        System.out.println("1. Найти свои очки");
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
                System.out.println(entry.getValue().name);
                for (String s : entry.getValue().actions) {
                    System.out.println(s);
                }
            }
        }
    }

    public static void locationInit() {
        bedRoom.addActions("Идти в коридор");
        locationMap.put("Идти в спальню", bedRoom);
        hallway.addActions("Идти в спальню", "Идти в гостиную");
        locationMap.put("Идти в коридор", hallway);
        livingRoom.addActions("Идти в коридор", "Идти в кухню");
        locationMap.put("Идти в гостиную", livingRoom);
        kitchen.addActions("Идти в ванную", "Идти в гостиную");
        locationMap.put("Идти на кухню", kitchen);
        bathRoom.addActions("Вернуться на кухню");
        locationMap.put("Идти в ванную", bathRoom);
    }
}
