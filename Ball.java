import java.awt.Rectangle;

public class Ball {
    private int xKoord;
    private int yKoord;
    private int dx;
    private int dy;
    private int size;
    private int speed;
    private Rectangle rect;

    public Ball(int pXKoord, int pYKoord) {
        xKoord = pXKoord;
        yKoord = pYKoord;
        speed = 5;
        dx = speed;
        dy = speed;
        size = 20;
        rect = new Rectangle(xKoord, yKoord, size, size); // Initialisieren des Rectangles mit Koordinaten und Größe
    }


    public int getXKoord() {
        return xKoord;
    }

    public int getYKoord() {
        return yKoord;
    }

    public int getSize() {
        return size;
    }

    public Rectangle getRect() {
        return rect;
    }

    public void aendereXRichtung() {
        dx = -dx;
    }

    public void aendereYRichtung() {
        dy = -dy;
    }

    public void newSpawn() {
        xKoord = (int) ((Math.random() * 450) + 110); // Randomizen der Koordinaten, um nach einem Tor einen zufälligen Spawn hervorzurufen
        yKoord = (int) ((Math.random() * 300) + 120);
        rect.setLocation(xKoord, yKoord);
    }

    public void move() {
        xKoord += dx; //
        yKoord += dy;
        rect.setLocation(xKoord, yKoord);
    }
}

