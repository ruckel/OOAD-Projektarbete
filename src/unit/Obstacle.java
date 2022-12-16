package unit;

import main.GamePanel;

import java.awt.*;

public class Obstacle extends Unit{

    private final int defaultY;

    public Obstacle(GamePanel gp){
        super(gp);
        image = loadImage("obstacle");
        defaultY = - gp.size;

        alive = true;
        positionY = gp.height / 2;
        positionX = gp.width / 2;

        //setUpHitBox();

    }
    private void setUpHitBox(){
        hitBox.x = 13;
        hitBox.y = 20;
        hitBox.width = 66;
        hitBox.height = 54;
    }
    public void setObstacle(int screenX, int speed){
        positionX= screenX;
        this.speed = speed;
        alive = true;
    }

    public void update(){
//        if (alive){
//            if(positionY >= gp.height){
//                alive = false;
//                positionY = defaultY;
//            } else {
//                positionY += speed;
//            }
//        }
    }
    public void draw(Graphics2D g2){
        if(alive){
            g2.drawImage(image, positionX, positionY, null);
            //g2.drawRect(screenX + hitBox.x, screenY + hitBox.y, hitBox.width, hitBox.height);
        }
    }
}
