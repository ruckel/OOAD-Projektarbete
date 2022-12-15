package unit;

import java.awt.*;

public class Laser extends Unit {

    private final int defaultY;

    public Laser(int spawnY){
        //image = loadSprite("laser");

        screenY = spawnY;
        defaultY = spawnY;

        hitBox.x = 42;
        hitBox.y = 22;
        hitBox.width = 14;
        hitBox.height = 45;
    }
    public void setUpLaser(int screenX, int speed){
        super.screenX = screenX;
        super.speed = speed;
        alive = true;
    }
    public void update(){
        if (alive){
            if(screenY <= 0){
                alive = false;
                screenY = defaultY;
            } else {
                screenY -= speed;
            }
        }
    }

    public void draw(Graphics2D g2){
        if(alive){
            g2.drawImage(image, screenX, screenY, null);
            g2.drawRect(screenX + hitBox.x, screenY + hitBox.y, hitBox.width, hitBox.height);
        }
    }
}
