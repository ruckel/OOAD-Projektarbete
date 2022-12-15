package unit;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

abstract class Unit {

    protected GamePanel gp;
    protected BufferedImage image;

    public Rectangle hitBox = new Rectangle(0, 0, 0, 0);
    protected int screenX, screenY;
    protected boolean invincible = false;
    protected int spriteCount = 0;
    protected int speed;
    protected boolean alive = false;

    protected Unit(GamePanel gp){
        this.gp = gp;
    }
    void update(){}
    void draw(Graphics2D g2){}

    private BufferedImage loadImage(String name){
        BufferedImage img = null;
        try{
            img = ImageIO.read(getClass().getResourceAsStream("/" + name + ".png"));
        }catch (IOException e){
            e.printStackTrace();
        }
        return img;
    }
}
