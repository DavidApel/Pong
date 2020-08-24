import java.awt.Rectangle;

public class Bumper {
    private int xKoord, yKoord, width, height, speed, b;
    private Rectangle rect;


    public Bumper(int pXKoord, int pYKoord) { // Parameter zum Festlegen des Startpunkts der Bumper in der Klasse Pong
        xKoord = pXKoord;
        yKoord = pYKoord;
        width = 25;
        height = 75;
        speed = 4;
        b = 0;
        rect = new Rectangle(xKoord, yKoord, width, height);
    }

    public int getXKoord() {
        return xKoord;
    }

    public int getYKoord() {
        return yKoord;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Rectangle getRect() {
        return rect;
    }


    public void move() {
        if (b == 0) {
            rect.setLocation(xKoord, yKoord); // Beim Loslassen der Taste bleiben die Koordinaten gleich
        }

        if (b == 1) {
            yKoord -= speed; // Der Wert "Speed" wird von den Y-Koordinaten subtrahiert, wenn das Parameter 1 übergeben wird
            rect.setLocation(xKoord, yKoord);
        }

        if (b == 2) {
            yKoord += speed; // Der Wert "Speed" wird zu den Y-Koordinaten addiert, wenn das Parameter 2 übergeben wird
            rect.setLocation(xKoord, yKoord);
        }

        if (yKoord > 565 - height) {
            yKoord -= speed; // Wenn die y-Koordinate größer als der abgefragte Wert ist, wird der Wert "speed" von der y-Koordinate abgezogen, während er immernoch hinzuaddiert wird, was dazu führt, dass das sich das Objekt nicht mehr bewegt
        }
        if (yKoord < 35) {
            yKoord += speed;
        }
    }

    public void setDirection(int pB) {
        b = pB;
    }
}
