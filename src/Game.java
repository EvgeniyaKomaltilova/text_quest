import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Game {
    static boolean isStarted;
    static Location currentLocation;
    static Person person = Person.getInstance();
    static Map<String,Location> locationMap = new HashMap<>();
    static Location bedRoom = new Location("Идти в спальню", "в спальне. ");
    static Location hallway = new Location("Идти в коридор", "в коридоре. ");
    static Location livingRoom = new Location("Идти в гостиную", "в гостиной. ");
    static Location kitchen = new Location("Идти на кухню", "на кухне. ");
    static Location bathRoom = new Location("Идти в ванную", "в ванной комнате. ");

    public static void main(String[] args) {
        initLocations();
        startGame();
        gameProcess();
    }

    public static void initLocations() {
        hallway.addWays(bathRoom, livingRoom, kitchen);
        hallway.addInaccessibleAction("Открыть дверь");

        bedRoom.addWays(livingRoom);
        bedRoom.addAction("Обыскать комнату");
        bedRoom.addItems("ключ");

        livingRoom.addAction("Обыскать комнату");

        kitchen.addAction("Обыскать комнату");
        kitchen.addItems("какую-то еду");
        kitchen.firstMessage = " Испуганные тараканы бросились врассыпную.";
        kitchen.addMessage(" С потолка свисает длинная нить паутины.",
                " Сквозь пыльное окно едва пробивается лучик света.",
                " Цветы на подоконнике, очевидно, давно никто не поливал.",
                " За окном слышно пение птиц.",
                " Через щели в полу дует ветер.");

        bathRoom.addAction("Обыскать комнату");
        bathRoom.addInaccessibleAction("Почистить зубы");
        bathRoom.addItems("зубную щетку");
    }

    public static void startGame() {
        isStarted = true;
        System.out.println("Вы проснулись в своей квартире.");
        goToLocation("Идти в спальню");
    }

    public static void gameProcess() {
        String s;
        while(isStarted) {
            s = getString();
            person.doPersonAction(s);
            currentLocation.doAction(s);
            goToLocation(s);
            checkGameOver();
            checkNullParameters();
            youCan();
        }
    }

    public static String getString() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String s = "";
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
                currentLocation = entry.getValue();
                System.out.print("Вы находитесь " + currentLocation.yourLocationIs);
                if (!currentLocation.isOpen) {
                    if (currentLocation.firstMessage != null) {
                        System.out.println(currentLocation.firstMessage);
                        currentLocation.isOpen = true;
                    }
                } else {
                    if (currentLocation.messages.size() != 0) {
                        double x = Math.random() * currentLocation.messages.size();
                        System.out.println(currentLocation.messages.get((int) x));
                    }
                }
            }
        }
    }

    public static void youCan() {
        System.out.println("Вы можете:");
        for (Map.Entry<String, Boolean> locEntry : currentLocation.actions.entrySet()) {
            if (locEntry.getValue())
                System.out.println("* " + locEntry.getKey());
        }
        for (Map.Entry<String, Boolean> currentEntry : currentLocation.ways.entrySet()) {
            if (currentEntry.getValue()) {
                System.out.println("-> " + currentEntry.getKey());
            }
        }
    }

    public static void checkGameOver() {
        if (person.time >= 44640) {
            System.out.println("Поздравляю, вы продержались целый месяц!");
            isStarted = false;
        }
        if (person.health <= 0) {
            System.out.println("Вы умерли.");
            isStarted = false;
        }
        if (person.mentalHealth <= 0) {
            System.out.println("Вы сошли с ума.");
            isStarted = false;
        }
    }
        public static void checkNullParameters() {
        if (person.hunger <= 0) {
            person.health-=1;
        }
        if (person.thirst <= 0) {
            person.health-=2;
        }
        if (person.mood <= 0) {
            person.mentalHealth--;
        }
        if (person.toilet <= 0) {
            System.out.println("Кажется, вам нужно сменить штаны...");
            person.mood-=10;
        }
    }

}