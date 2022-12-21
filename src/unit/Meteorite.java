package unit;

import main.Utility;

import java.awt.*;

public class Meteorite extends Obstacle{
    public Meteorite(int size, int defaultY) {
        super(size, defaultY);
        image = new Utility().loadImage("obstacle", size, size);
        setUpMeteorite();
        setUpHitBox();
    }

    private void setUpMeteorite(){
        lives = 1;
        id = 1;
    }

    public void setUpHitBox(){
        hitBox.x = 10;
        hitBox.y = 15;
        hitBox.width = 40;
        hitBox.height = 40;
        defaultHitBoxX = hitBox.x;
        defaultHitBoxY = hitBox.y;
    }

    public void draw(Graphics2D g2) {
        if (alive) {
            g2.drawImage(image, positionX, positionY, null);
        }
    }

}
