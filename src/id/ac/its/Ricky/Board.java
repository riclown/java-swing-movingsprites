package id.ac.its.Ricky;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Board extends JPanel implements ActionListener {

    private final int ICRAFT_X = 40;
    private final int ICRAFT_Y = 60;
    private final int DELAY = 10;
    private Timer timer;
    private SpaceShip spaceShip;
    private Asteroid asteroid, as1, as2, as3, as4;

    public Board() {

        initBoard();
    }

    private void initBoard() {

        addKeyListener(new TAdapter());
        setBackground(Color.BLACK);
        setFocusable(true);

        spaceShip = new SpaceShip(ICRAFT_X, ICRAFT_Y);
        asteroid = new Asteroid(250, 50);
        as1 = new Asteroid (300, 25);
        as2 = new Asteroid (275, 10);
        as3 = new Asteroid (325, 75);
        as4 = new Asteroid (350, 125);

        timer = new Timer(DELAY, this);
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        doDrawing(g);

        Toolkit.getDefaultToolkit().sync();
    }

    private void doDrawing(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;
        
        g2d.drawImage(spaceShip.getImage(), spaceShip.getX(),
                spaceShip.getY(), this);
        
        g2d.drawImage(asteroid.getImage(),asteroid.getX(),asteroid.getY(),this);
        g2d.drawImage(as1.getImage(),as1.getX(),as1.getY(),this);
        g2d.drawImage(as2.getImage(),as2.getX(),as2.getY(),this);
        g2d.drawImage(as3.getImage(),as3.getX(),as3.getY(),this);
        g2d.drawImage(as4.getImage(),as4.getX(),as4.getY(),this);

        List<Missile> missiles = spaceShip.getMissiles();

        for (Missile missile : missiles) {
            
            g2d.drawImage(missile.getImage(), missile.getX(),
                    missile.getY(), this);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        updateMissiles();
        updateSpaceShip();

        repaint();
    }

    private void updateMissiles() {

        List<Missile> missiles = spaceShip.getMissiles();

        for (int i = 0; i < missiles.size(); i++) {

            Missile missile = missiles.get(i);

            if (missile.isVisible()) {

                missile.move();
            } else {

                missiles.remove(i);
            }
        }
    }

    private void updateSpaceShip() {

        spaceShip.move();
    }

    private class TAdapter extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {
            spaceShip.keyReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {
            spaceShip.keyPressed(e);
        }
    }
}
