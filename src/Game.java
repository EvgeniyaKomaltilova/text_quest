public class Game {
    public static void main(String[] args) {
        Field field = Field.getInstance(4,2);
        //создание первой локации
        Location livingRoom = new Location(field, 0, 0, "Вы находитесь в гостиной.");
        livingRoom.addItems("пульт");
        livingRoom.addActions("Вернуться назад", "Включить телевизор", "Выключить телевизор");
    }
}
