package unit;

import main.GamePanel;

import java.awt.*;

public class Laser extends Unit {

    private final int defaultY;

    public Laser(GamePanel gp){
        super(gp);
        image = loadImage("laser");

        defaultY = gp.height - (gp.size + gp.size/2);
        positionY = defaultY;

        //setUpHitBox();
    }
    public void setUpLaser(int positionX, int speed){
        super.positionX = positionX;
        super.speed = speed;
        alive = true;
    }
    private void setUpHitBox(){
        hitBox.x = 42;
        hitBox.y = 22;
        hitBox.width = 14;
        hitBox.height = 45;
    }
    public void update(){
        if(alive){
            if(positionY <= 0){
                alive = false;
                positionY = defaultY;
            } else {
                positionY -= speed;
            }
        }
    }

    public void draw(Graphics2D g2){
        if(alive){
            g2.drawImage(image, positionX, positionY, null);
            //g2.drawRect(screenX + hitBox.x, screenY + hitBox.y, hitBox.width, hitBox.height);
        }
        //g2.drawImage(image, positionX, positionY, null);
    }
}
