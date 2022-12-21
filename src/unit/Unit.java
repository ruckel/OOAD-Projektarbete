package unit;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Unit {
    protected BufferedImage image;
    protected BufferedImage hurt;

    public Rectangle hitBox = new Rectangle(0, 0, 0, 0);
    public int positionX, positionY;
    protected int defaultHitBoxX, defaultHitBoxY;
    protected int speed;
    public int lives;
    public boolean alive = false;

    void update(){}
    void draw(Graphics2D g2){}

    protected void updateHitBox(){
        hitBox.x = positionX + defaultHitBoxX;
        hitBox.y = positionY + defaultHitBoxY;
    }
}
