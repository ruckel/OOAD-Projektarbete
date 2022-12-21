package main;

import Sound.Sound;
import ui.GameState;
import ui.HomeState;
import ui.State;
import unit.*;

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

    private ObstacleFactory obstacleFactory = new ObstacleFactory(this);
    public Obstacle[] obstacles = new Obstacle[20];

    //OBJEKT
    private Thread gameThread;
    private final State state = new State();
    private final InputHandler inputHandler = new InputHandler(this);
    public Player player = new Player(inputHandler, size, height - (size + size / 10));
    public UnitLoader unitLoader = new UnitLoader(this);
    public CollisionHandler ch = new CollisionHandler(this);
    public Sound sound = new Sound(this);

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
        state.setCurrentGameState(new HomeState());
        for (int i = 0; i < lasers.length; i++) {
            lasers[i] = new Laser(size, height - (size + size/2));
        }
        for (int i = 0; i < obstacles.length; i++) {
            if (i == obstacles.length-1) {
                obstacles[i] = obstacleFactory.getObstacle(Obstacles.SUPER_METEORITE);
            }else {
                obstacles[i] = obstacleFactory.getObstacle(Obstacles.METEORITE);
            }
        }

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

    private void update() {
        state.update(this);
    }

    public void paintComponent(Graphics g) {
        super.paintComponents(g);

        Graphics2D g2 = (Graphics2D) g;

        state.draw(g2,this);
    }
    public void setState(GameState state){
        this.state.setCurrentGameState(state);
    }
    public GameState setState(){
        return state.getCurrentGameState();
    }
    public GameState getState(){
        return state.getCurrentGameState();
    }
    public void setNextState(){
        state.setNextState();
    }
    public void setLastState(){
        state.setLastState();
    }


}
