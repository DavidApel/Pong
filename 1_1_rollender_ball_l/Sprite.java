import java.awt.Rectangle;

public abstract class Sprite {
    protected int xKoord;
    protected int yKoord;
    protected int width;
    protected int height;
    protected Rectangle rect;

    protected int getXKoord() {
        return xKoord;
    }

    protected int getYKoord() {
        return yKoord;
    }

    protected int getHeight() {
        return height;
    }

    protected int getWidth() {
        return width;
    }

    protected Rectangle getRect() {
        return rect;
    }

    protected abstract void move(); // abstrakte Methode, die in Subklassen überschrieben wird
}

