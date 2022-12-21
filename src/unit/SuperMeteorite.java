package unit;

import main.Utility;

import java.awt.*;

public class SuperMeteorite extends Obstacle{
    public SuperMeteorite(int size, int defaultY) {
        super(size, defaultY);
        image = new Utility().loadImage("super-meteorite", size*2, size*2);
        setUpSuperMeteorite();
        setUpHitBox();
    }

    private void setUpSuperMeteorite(){
        lives = 5;
        id = 2;
    }

    public void setUpHitBox(){
        hitBox.x = 20;
        hitBox.y = 30;
        hitBox.width = 80;
        hitBox.height = 80;
        defaultHitBoxX = hitBox.x;
        defaultHitBoxY = hitBox.y;
    }

    public void draw(Graphics2D g2) {
        if (alive) {
            g2.drawImage(image, positionX, positionY, null);
        }
    }
}
