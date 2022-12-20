package unit;

import main.Utility;

import java.awt.*;

public class Meteorite extends Obstacle{
    public Meteorite(int size, int defaultY) {
        super(size, defaultY);
        image = new Utility().loadImage("obstacle", size, size);
    }

    public void draw(Graphics2D g2) {
        if (alive) {
            g2.drawImage(image, positionX, positionY, null);
        }
    }

}
