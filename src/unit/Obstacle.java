package unit;

import main.GamePanel;
import main.Utility;

import java.awt.*;

public class Obstacle extends Unit{

    private final int defaultY;
    public int id;

    public Obstacle(int size, int defaultY){
        image = new Utility().loadImage("obstacle", size, size);
        this.defaultY = defaultY;

        positionY = defaultY;
    }
    public void setObstacle(int screenX, int speed){
        positionX = screenX;
        this.speed = speed;
        alive = true;
    }
    public void update(GamePanel gp){
        if (alive){
            if(positionY >= gp.height){
                alive = false;
                positionY = defaultY;
            } else {
                positionY += speed;
            }
            updateHitBox();

            if(gp.ch.checkObstacle(this)){
                alive = false;
                positionY = defaultY;
            }
        }
    }
    public void reset(){
        alive = false;
        positionY = defaultY;
    }
    public void setUpHitBox(){}
    public void draw(Graphics2D g2) {}
}
