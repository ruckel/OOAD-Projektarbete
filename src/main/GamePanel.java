package main;

import ui.UI;
import unit.Laser;
import unit.Obstacle;
import unit.Player;
import unit.UnitLoader;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    //Värden att mäta med(mätinstrument)
    public int size = 64;
    public int width = size * 10;
    public int height = size * 10;
    final private double FPS = 60.0;
    //UNITS
    public Laser[] lasers = new Laser[10];
    public Obstacle[] obstacles = new Obstacle[20];

    //OBJEKT
    private State state;
    private Thread gameThread;
    private final UI ui = new UI(this);
    InputHandler inputHandler = new InputHandler();
    public Player player = new Player(this, inputHandler);
    public UnitLoader unitLoader = new UnitLoader(this);
    public CollisionHandler ch = new CollisionHandler(this);



    public GamePanel() {

        this.setPreferredSize(new Dimension(width, height));
        this.setBackground(Color.BLACK);
        this.addKeyListener(inputHandler);
        this.setDoubleBuffered(true); //förbuffrar grafik
        this.setFocusable(true);

        setupGame();
        startThread();
    }

    public void startThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    private void setupGame() {
        this.state = State.HOME_SCREEN;
    }

    //LOOP SOM UPPDATERAR 60 ggr / sekund
    @Override
    public void run() {
        double updateInterval = 1000000000 / FPS; //0.016666 sekund
        double delta = 0;
        long lastUpdate = System.nanoTime(); //nanosekunder för precision
        long currentTime;

        while (gameThread.isAlive()) {

            currentTime = System.nanoTime();

            delta += (currentTime - lastUpdate) / updateInterval;
            lastUpdate = currentTime;

            if (delta >= 1) {
                update();
                repaint();
                delta--;
            }
        }
    }

    public void update() {
        ui.update();
    }

    public void paintComponent(Graphics g) {
        super.paintComponents(g);

        Graphics2D g2 = (Graphics2D) g;

        ui.draw(g2);

    }

    public static void main(String[] args) {

        JFrame window = new JFrame();

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("SPACE WAR");

        GamePanel gp = new GamePanel();
        window.add(gp);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }
}
