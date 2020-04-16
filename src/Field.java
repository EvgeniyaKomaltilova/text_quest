public class Field {
    private int width;
    private int height;
    public Location[][] gameField;

    public static Field instance = null;

    private Field(int width, int height) {
        gameField = new Location[width][height];
    }

    public static Field getInstance(int width, int height) {
        if (instance == null) {
            instance = new Field(width, height);
        }
        return instance;
    }
}
