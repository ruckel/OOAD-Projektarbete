package unit;

import main.GamePanel;

import java.awt.*;

public class Obstacle extends Unit{

    private final int defaultY;

    public Obstacle(GamePanel gp){
        super(gp);
        image = loadImage("obstacle");
        defaultY = - gp.size;

        positionY = defaultY;
        setUpHitBox();

    }
    private void setUpHitBox(){
        hitBox.x = 10;
        hitBox.y = 10;
        hitBox.width = 40;
        hitBox.height = 40;
        defaultHitBoxX = hitBox.x;
        defaultHitBoxY = hitBox.y;
    }
    public void setObstacle(int screenX, int speed){
        positionX = screenX;
        this.speed = speed;
        alive = true;
    }

    public void update(){
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
        positionY = defaultY;
    }
    public void draw(Graphics2D g2){
        if(alive){
            g2.drawImage(image, positionX, positionY, null);
            g2.drawRect(hitBox.x, hitBox.y, hitBox.width, hitBox.height);
        }
    }
}
