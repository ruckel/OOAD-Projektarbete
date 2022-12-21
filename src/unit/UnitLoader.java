package unit;

import main.GamePanel;

import java.util.Random;

public class UnitLoader {

    private final GamePanel gp;

    private int obstacleCount = 0;
    private int loopCount = 0;
    private int obstacleInterval = 50;

    public UnitLoader(GamePanel gp){
        this.gp = gp;
    }

    public void update(){
        loopCount++;
        if (loopCount == obstacleInterval){
            loopCount = 0;

            if (gp.obstacles[obstacleCount].id == 1) {
                gp.obstacles[obstacleCount].setObstacle(randomSpawnX(), randomSpeed());
            } else {
                gp.obstacles[obstacleCount].setObstacle(randomSpawnX(), 2);
            }
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
            speed = 1;
        } else if (randomNr < 50){
            speed = 2;
        } else if (randomNr < 80){
            speed = 3;
        } else {
            speed = 4;
        }
        return speed;
    }

    private int randomSpawnX() {
        Random rng = new Random();
        return rng.nextInt(5, gp.width - gp.size);
    }
    public void resetUnits(){
        gp.player.update(gp);
        for (Laser laser :
                gp.lasers) {
            laser.reset();
        }

        gp.unitLoader.update();
        for (Obstacle ob :
                gp.obstacles) {
            ob.reset();
        }
    }
}
