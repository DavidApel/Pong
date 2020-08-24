import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Line2D;

public class Pong extends JPanel implements Runnable, KeyListener {
    JFrame myFrame;
    Ball spielball;

    Bumper b1;
    Bumper b2;
    Bonus bonus1;
    int counterL, counterR;
    Image img = Toolkit.getDefaultToolkit().getImage("Bilder/pong_background.png"); // Einfügen des Hintergrundbildes
    Image ball = Toolkit.getDefaultToolkit().getImage("Bilder/ball_background.png"); // Einfügen der Balltextur

    public Pong(int w, int h) {
        initGame();

        this.setPreferredSize(new Dimension(w, h));
        this.setBackground(Color.DARK_GRAY);
        myFrame = new JFrame("Pong");
        myFrame.setLocation(100, 100);
        myFrame.setResizable(false);
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.add(this);
        myFrame.addKeyListener(this);
        myFrame.pack();
        myFrame.setVisible(true);
        counterL = 0;
        counterR = 0;
        Thread th = new Thread(this);
        th.start();
    }

    private void initGame() {
        //Hier werden alle Objekte des Spiels initialisiert
        spielball = new Ball(400, 300);
        b1 = new Bumper(40, 225); //
        b2 = new Bumper(730, 225); //
        bonus1 = new Bonus((int) ((Math.random() * 400) + 160), (int) ((Math.random() * 300) + 120));
    }

    @Override
    public void run() {
        while (myFrame.isVisible()) {

            moveObjects();
            repaint();
            if (spielball.getRect().intersects(b1.getRect()) || spielball.getRect().intersects(b2.getRect())) {
                spielball.aendereXRichtung(); // Wenn der Spielball auf einen der Bumper trifft, wird die Methode "aendereXRichtung" ausgeführt
            }

            if (spielball.getXKoord() < 0){ // Wenn der Spielball an die Seiten des Fensters kommt, wird er neu gespawnt, die xRichtung wird geändert und der Counter der anderen Seite wird hochgesetzt
                spielball.newSpawn();
                spielball.aendereXRichtung();
                counterR++;
            }

            if (spielball.getXKoord() > 800 - spielball.getSize()){
                spielball.newSpawn();
                spielball.aendereXRichtung();
                counterL++;
            }

            if (spielball.getYKoord() < 30 || spielball.getYKoord() > 577 - spielball.getSize()){ // Wenn der Spielball an die Seitenlinien kommt, wird die y Richtung verändert
                spielball.aendereYRichtung();
            }

            if (bonus1.getRect().intersects(spielball.getRect())){
                bonus1.newSpawn(); // Wenn der Spielball auf das Bonus-Objekt trifft, spawnt das Objekt an anderer Stelle
            }

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                System.out.println(e);
            }

        }
    }


    public void moveObjects() {
        //Hier werden die Objekte bewegt
        spielball.move();
        b1.move();
        b2.move();
    }

    @Override
    public void paintComponent(Graphics g) {

            /*try {
                GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
                Font myFont = Font.createFont(Font.TRUETYPE_FONT, new File("E:\\Anton-Regular")).deriveFont(12f);
                //register the font
                ge.registerFont(myFont);
                System.out.println("s");
            }
            catch (FontFormatException | IOException ex){

            }*/

        g.drawImage(img, 0, 0, null); // Zeichnen des Hintergrundbildes

        g.setColor(Color.RED);
        g.fillRect(b1.getXKoord(), b1.getYKoord(), b1.getWidth(), b1.getHeight()); // Ausfüllen des Rectangles mit den Koordinaten der Bumper
        g.setColor(Color.BLUE);
        g.fillRect(b2.getXKoord(), b2.getYKoord(), b2.getWidth(), b2.getHeight());


        g.setColor(Color.YELLOW); // Zeichnen des Bonus-Objektes
        g.drawRoundRect(bonus1.getXKoord(), bonus1.getYKoord(), bonus1.getSize(), bonus1.getSize(), bonus1.getArcw(), bonus1.getArch());
        g.fillRoundRect(bonus1.getXKoord(), bonus1.getYKoord(), bonus1.getSize(), bonus1.getSize(), bonus1.getArcw(), bonus1.getArch());
        g.setColor(Color.BLACK);


        g.setColor(Color.RED);
        g.setFont(new Font("Arial", Font.PLAIN, 50));
        g.drawString("" + counterL, 80, 100);
        g.setColor(Color.BLUE);
        g.drawString("" + counterR, 680, 100);


        Graphics2D g2 = (Graphics2D) g; //Import der Graphics2D Klasse, um ein Objekt zu zeichnen
        g2.setStroke(new BasicStroke(1f)); // Breite des Objektes wird bestimmt
        g2.setColor(Color.WHITE);
        g2.draw(new Line2D.Double(25, 23, 25, 777)); // Zeichnen der weißen Linie als Spielfeldrand
        g2.draw(new Line2D.Double(773, 23, 773, 777));
        g2.draw(new Line2D.Double(25, 23, 773, 23));
        g2.draw(new Line2D.Double(25, 577, 773, 577));

        Color myColor1 = new Color(0, 51, 204); // Erstellen einer Farbe um den blauen Spielfeldrand auf die Farbe des Hintergrundbildes anzupassen
        g2.setStroke(new BasicStroke(45f)); // Breite des Objektes wird auf 45 gesetzt
        g2.setColor(Color.RED);
        g2.draw(new Line2D.Double(0, 0, 380, 0)); // Zeichnen der farbigen Spielfeldränder
        g2.draw(new Line2D.Double(0, 600, 380, 600));
        g2.setColor(myColor1);
        g2.draw(new Line2D.Double(424, 0, 800, 0));
        g2.draw(new Line2D.Double(424, 600, 800, 600));

        g.setColor(Color.WHITE); // Bestimmen der Farbe, falls der Texturpfad nicht gefunden wird
        g.fillOval(spielball.getXKoord(), spielball.getYKoord(), spielball.getSize(), spielball.getSize()); // Ausfüllen des Kreises, falls die Balltextur nicht gefunden wird
        g.drawImage(ball, spielball.getXKoord(), spielball.getYKoord(), null); // Zeichnen der Balltextur, während diese sich mit den Koordinaten des  Spielball mitbewegt
    }


    @Override
    public void keyPressed(KeyEvent e) {

        if ((e.getKeyCode() == KeyEvent.VK_UP)) {
            b2.setDirection(1); // Auslösen der Funktion "setDirection" mit dem Parameter 1
        }

        if ((e.getKeyCode() == KeyEvent.VK_DOWN)) {
            b2.setDirection(2); // Auslösen der Funktion "setDirection" mit dem Parameter 2
        }

        if ((e.getKeyChar() == 'w')) {
            b1.setDirection(1);
        }

        if ((e.getKeyChar() == 's')) {
            b1.setDirection(2);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if ((e.getKeyCode() == KeyEvent.VK_UP)) {
            b2.setDirection(0); /// Auslösen der Funktion "setDirection" mit dem Parameter 0
        }

        if ((e.getKeyCode() == KeyEvent.VK_DOWN)) {
            b2.setDirection(0);
        }

        if ((e.getKeyChar() == 'w')) {
            b1.setDirection(0);

        }

        if ((e.getKeyChar() == 's')) {
            b1.setDirection(0);

        }

    }

    @Override
    public void keyTyped(KeyEvent e) {
        if (e.getKeyChar() == 27) {
            System.exit(0);
        } // end of if
    }

    public static void main(String[] args) {
        new Pong(800, 600);
    }
}