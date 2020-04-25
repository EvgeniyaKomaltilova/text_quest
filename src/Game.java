import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Game {
    static boolean isStarted;
    static Location currentLocation;
    static Map<String,Location> locationMap = new HashMap<>();
    static Location bedRoom = new Location("Идти в спальню", "в спальне. ");
    static Location hallway = new Location("Идти в коридор", "в коридоре. ");
    static Location livingRoom = new Location("Идти в гостиную", "в гостиной. ");
    static Location kitchen = new Location("Идти на кухню", "на кухне. ");
    static Location bathRoom = new Location("Идти в ванную", "в ванной комнате. ");
    static Location exit = new Location("Выйти наружу!", "");
    static Person person = Person.getInstance();


    public static void main(String[] args) {
        initLocations();
        initPerson();
        startGame();
        gameProcess();
    }

    public static void initLocations() {
        hallway.addWays(bathRoom, livingRoom, kitchen);
        hallway.addClosedWay(exit);
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

    public static void initPerson() {
        person.addVariableParameter("hunger", 50);
        //System.out.println(person.variableParameters.entrySet());
    }

    public static void startGame() {
        isStarted = true;
        System.out.println("Вы проснулись в чьей-то квартире.");
        goToLocation("Идти в спальню");
    }

    public static void gameProcess() {
        String s;
        while(isStarted) {
            s = getString();
            checkParameters();
            currentLocation.doAction(s);
            goToLocation(s);
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
            if (key.equals("Выйти наружу!")) {
                System.out.println("Поздравляю! Вы выбрались!");
                isStarted = false;
                break;
            }
            if (key.equals(entry.getKey())) {
                currentLocation = entry.getValue();
                System.out.print("Вы находитесь " + currentLocation.yourLocationIs);
                if (!currentLocation.isOpen) {
                    if (currentLocation.firstMessage!=null) {
                        System.out.println(currentLocation.firstMessage);
                        currentLocation.isOpen = true;
                    }
                } else {
                    if (currentLocation.messages.size() != 0) {
                        double x = Math.random()*currentLocation.messages.size();
                        System.out.println(currentLocation.messages.get((int) x));
                    }
                }
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
        }
    }

    public static void checkParameters() {
        for (Map.Entry<String, Integer> entry : person.variableParameters.entrySet()) {
            if (entry.getValue() == 49) {
                switch (entry.getKey()) {
                    case "hunger":
                        System.out.println("В животе забурчало. Похоже, пора подкрепиться.");
                }
            }
            entry.setValue(entry.getValue()-1);
        }
    }
}
