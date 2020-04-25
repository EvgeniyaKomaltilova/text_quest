import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Person {
    private static Person instance = null;
    List<String> inventory;
    List<String> personActions;
    int hunger; //голод
    int thirst; //жажда
    int toilet; //нужда
    int mood; //настроение
    int health; //здоровье
    int mentalHealth; //психическое здоровье
    int strength; //сила
    int agility; //ловкость
    int intelligence; //интеллект

    private Person() {
        this.inventory = new ArrayList<>();
        this.personActions = new ArrayList<>();
        this.hunger = 40;
        this.thirst = 40;
        this.toilet = 40;
        this.mood = 50;
        this.health = 50;
        this.mentalHealth = 50;
        this.strength = 5;
        this.agility = 5;
        this.intelligence = 5;
    }

    public static Person getInstance() {
        if (instance == null) {
            instance = new Person();
        }
        return instance;
    }

    public void checkParameters() {
        if (health < 10 && health >0) {
            System.out.println("Вам хреново.");
        } else if (health < 20) {
            System.out.println("Вы неважно себя чувствуете.");
        } else if (health < 30) {
            System.out.println("Вы в порядке.");
        } else if (health < 40) {
            System.out.println("Вы хорошо себя чувствуете.");
        } else if (health < 50) {
            System.out.println("Вы отлично себя чувствуете.");
        } else if (health >= 50) {
            System.out.println("Вы здоровы как бык!");
        }

        if (mentalHealth < 10 && mentalHealth >0) {
            System.out.println("Вы на грани сумасшествия.");
        } else if (mentalHealth < 20) {
            System.out.println("Вы разговариваете с окружающими предметами.");
        } else if (mentalHealth < 30) {
            System.out.println("Иногда вам кажется, что за вами наблюдают.");
        } else if (mentalHealth < 40) {
            System.out.println("Вы просто немного устали.");
        } else if (mentalHealth < 50) {
            System.out.println("Вы вполне адекватны.");
        } else if (mentalHealth >= 50) {
            System.out.println("Вы мыслите абсолютно трезво.");
        }

        if (mood < 10 && mood >0) {
            System.out.println("Вы готовы кого-нибудь убить.");
        } else if (mood < 20) {
            System.out.println("Ваше настроение ниже плинтуса.");
        } else if (mood < 30) {
            System.out.println("Вы раздражены.");
        } else if (mood < 40) {
            System.out.println("Ваше настроение выше среднего.");
        } else if (mood < 50) {
            System.out.println("Вы в хорошем настроении.");
        } else if (mood >= 50) {
            System.out.println("Вы совершенно счастливы!");
        }

        if (hunger < 10 && hunger > 0) {
            System.out.println("Вы готовы съесть кота.");
        } else if (hunger < 20) {
            System.out.println("Вы голодны.");
        } else if (hunger < 30) {
            System.out.println("Вы слегка голодны.");
        }

        if (thirst < 10 && thirst > 0) {
            System.out.println("Вы безумно хотите пить.");
        } else if (thirst < 20) {
            System.out.println("Вы ощущаете жажду.");
        } else if (thirst < 30) {
            System.out.println("Вы чувствуете легкую жажду.");
        }

        if (toilet < 10 && toilet > 0) {
            System.out.println("Вы ооочень хотите в туалет!");
        } else if (thirst < 20) {
            System.out.println("Вы хотите в туалет.");
        } else if (thirst < 30) {
            System.out.println("Вы ощущаете зов природы.");
        }
    }

    public void doPersonAction(String key) {
        switch (key) {
            case "Самоанализ":
                this.checkParameters();
                break;
            case "Облегчиться":
                this.useToilet();
                break;
            case "Попить":
                this.drink();
                break;
            case "Поесть":
                this.ateFood();
                break;
        }
    }

    public void ateFood() {
        System.out.println("Кажется, вы наелись. Можно жить!");
        hunger = 40;
    }

    public void drink() {
        System.out.println("Вы утолили жажду.");
        hunger = 40;
    }

    public void useToilet() {
        System.out.println("Вы справили нужду.");
        toilet = 40;
    }
}
