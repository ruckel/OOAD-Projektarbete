package unit;

import main.GamePanel;

import java.awt.*;

public class Laser extends Unit {

    public final int defaultY;

    public Laser(GamePanel gp){
        super(gp);
        image = loadImage("laser");

        defaultY = gp.height - (gp.size + gp.size/2);
        positionY = defaultY;

        setUpHitBox();
    }
    public void setUpLaser(int positionX, int speed){
        super.positionX = positionX;
        super.speed = speed;
        alive = true;
    }
    private void setUpHitBox(){
        hitBox.x = 29;
        hitBox.y = 15;
        hitBox.width = 7;
        hitBox.height = 30;
        defaultHitBoxX = hitBox.x;
        defaultHitBoxY = hitBox.y;
    }
    public void update(){
        if(alive){
            if(positionY <= 0){
                alive = false;
                positionY = defaultY;
            } else {
                positionY -= speed;
            }
            updateHitBox();

            if(gp.ch.checkCollisionWithObstacle(this)){
                alive = false;
                positionY = defaultY;
            }
        }
    }

    public void draw(Graphics2D g2){
        if(alive){
            g2.drawImage(image, positionX, positionY, null);
            g2.drawRect(hitBox.x, hitBox.y, hitBox.width, hitBox.height);
        }
    }
}
