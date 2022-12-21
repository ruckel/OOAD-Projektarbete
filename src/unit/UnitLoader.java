package unit;

import main.GamePanel;
import main.Property;

import java.util.Random;

public class UnitLoader {

    private final GamePanel gp;
    Property property = Property.getInstance();
    //Difficulty
    int difficultyMultiplier = Integer.parseInt(property.getProperty("difficulty"));

    private int obstacleCount = 0;
    private int starCount = 0;
    private int loopCount = 0;
    private int obstacleInterval = 50 / difficultyMultiplier;
    private int initiation = 2;

    public UnitLoader(GamePanel gp){
        this.gp = gp;
    }

    public void update(){
        difficultyMultiplier = gp.difficulty;
        loopCount++;
        if (loopCount == obstacleInterval && loopCount > initiation){
            loopCount = 0;
            gp.obstacles[obstacleCount].setObstacle(randomSpawnX(), randomSpeed());
            obstacleCount++;

            for (int i = 0; i<1; i++) {
                gp.stars[starCount].setStar(randomSpawnX(), 4);
                starCount++;
                gp.stars[starCount].setStar(randomSpawnX(), 6);
                starCount++;
            }

            if (obstacleCount == 20){
                obstacleCount = 0;
            }
            if (starCount == 1000){
                starCount = 0;
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
        if (randomNr < 5){
            speed = 1;
        } else if (randomNr < 40){
            speed = 2;
        } else if (randomNr < 90){
            speed = 3;
        } else {
            speed = 6;
        }
        return speed * difficultyMultiplier;
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
        for (Star star : gp.stars){
            star.reset();
        }
        for (Obstacle ob :
                gp.obstacles) {
            ob.reset();
        }
    }
}
