package unit;

import main.Utility;

import java.awt.*;

public class Laser extends Unit {

    public final int defaultY;

    public Laser(int size, int defaultY){
        image = new Utility().loadImage("laser",size,size);

        this.defaultY = defaultY;
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
        }
    }
    public void draw(Graphics2D g2){
        if(alive){
            g2.drawImage(image, positionX, positionY, null);
        }
    }
    public void reset(){
        positionY = defaultY;
        alive = false;
    }
}
