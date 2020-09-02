import java.awt.Rectangle;

public class Ball extends Sprite {
    ;
    private int dx;
    private int dy;

    public Ball(int pXKoord, int pYKoord, int pWidth, int pHeight) {
        xKoord = pXKoord;
        yKoord = pYKoord;
        dx = 5;
        dy = 5;
        width = pWidth;
        height = pHeight;
        width = 20;
        height = 20;
        rect = new Rectangle(xKoord, yKoord, width, height); // Initialisieren des Rectangles mit Koordinaten und Größe
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

    @Override
    public void move() {
        xKoord += dx;
        yKoord += dy;
        rect.setLocation(xKoord, yKoord);
    }
}

