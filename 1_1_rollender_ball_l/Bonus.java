import java.awt.geom.RoundRectangle2D;

public class Bonus extends Sprite {
    private int arcw;
    private int arch;
    private RoundRectangle2D rect; //Deklarieren eines Rectangles mit runden Kanten


    public Bonus(int pXKoord, int pYKoord, int pWidth, int pHeight) {
        xKoord = pXKoord;
        yKoord = pYKoord;
        arcw = 10;
        arch = 10;
        width = pWidth;
        height = pHeight;
        rect = new RoundRectangle2D.Float(xKoord, yKoord, width, height, arcw, arch); // arcw = Bestimmt den horizontalen Durchmesser des Bogens an den Ecken / arch = Bestimmt den vertikalen Durchmesser des Bogens an den Ecken
    }

    public RoundRectangle2D getRoundRect() {
        return rect;
    }

    public int getArcw() {
        return arcw;
    }

    public int getArch() {
        return arch;
    }

    public void newSpawn() {
        xKoord = (int) ((Math.random() * 400) + 160);
        yKoord = (int) ((Math.random() * 300) + 120);
        rect.setRoundRect(xKoord, yKoord, width, height, arcw, arch);
    }

    @Override
    public void move() {
        System.out.println();
    }
}


