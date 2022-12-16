package unit;

import main.GamePanel;
import main.Utility;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Unit {

    protected GamePanel gp;
    protected BufferedImage image;

    public Rectangle hitBox = new Rectangle(0, 0, 0, 0);
    protected int positionX, positionY;
    protected int spriteCount = 0;
    protected int speed;
    protected boolean alive = false;

    protected Unit(GamePanel gp){
        this.gp = gp;
    }
    void update(){}
    void draw(Graphics2D g2){}

    protected BufferedImage loadImage(String name){
        BufferedImage img = null;
        Utility utility = new Utility();
        try{
            img = utility.scaleImage(ImageIO.read(getClass().getResourceAsStream("/" + name + ".png")), gp.size, gp.size);
        }catch (IOException e){
            e.printStackTrace();
        }
        return img;
    }
}
