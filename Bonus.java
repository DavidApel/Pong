import java.awt.geom.RoundRectangle2D;

public class Bonus {
    private int xKoord;
    private int yKoord;
    private int arcw;
    private int arch;
    private final int size;
    private RoundRectangle2D rect; //Deklarieren eines Rectangles mit runden Kanten


    public Bonus(int pXKoord, int pYKoord) {
        xKoord = pXKoord;
        yKoord = pYKoord;
        arcw = 10;
        arch = 10;
        size = 25;
        rect = new RoundRectangle2D.Float(xKoord, yKoord, size, size, arcw, arch); // arcw = Bestimmt den horizontalen Durchmesser des Bogens an den Ecken / arch = Bestimmt den vertikalen Durchmesser des Bogens an den Ecken
    }

    public int getXKoord() {
        return xKoord;
    }

    public int getYKoord() {
        return yKoord;
    }

    public int getArcw() {
        return arcw;
    }

    public int getArch() {
        return arch;
    }

    public int getSize() {
        return size;
    }

    public RoundRectangle2D getRect() {
        return rect;
    }

    public void newSpawn() {
        xKoord = (int) ((Math.random() * 400) + 160);
        yKoord = (int) ((Math.random() * 300) + 120);
        rect.setRoundRect(xKoord, yKoord, size, size, arcw, arch);
        }
   }


