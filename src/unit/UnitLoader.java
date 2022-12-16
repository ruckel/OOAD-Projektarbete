package unit;

import main.GamePanel;

import java.util.Random;

public class UnitLoader {

    GamePanel gp;

    public Obstacle[] obstacles = new Obstacle[20];

    private int obstacleCount = 0;
    private int loopCount = 0;

    private int obstacleInterval = 50;


    public UnitLoader(GamePanel gp){
        this.gp = gp;
        for (int i = 0; i < obstacles.length; i++) {
            obstacles[i] = new Obstacle(gp);
        }
    }

    public void update(){
        loopCount++;
        if (loopCount == obstacleInterval){
            loopCount = 0;
            obstacles[obstacleCount].setObstacle(randomSpawnX(), 1);
            obstacleCount++;
            if (obstacleCount == 20){
                obstacleCount = 0;
            }
        }
    }
    public void setObstacleInterval(int interval){
        obstacleInterval = interval;
    }

    private int randomSpeed() {
        int speed;
        Random rng = new Random();
        int randomNr = rng.nextInt(1, 101);
        if (randomNr < 20){
            speed = 4;
        } else if (randomNr < 50){
            speed = 5;
        } else if (randomNr < 80){
            speed = 6;
        } else {
            speed = 7;
        }
        return speed;
    }

    private int randomSpawnX() {
        Random rng = new Random();
        return rng.nextInt(5, gp.width - gp.size);
    }
}
