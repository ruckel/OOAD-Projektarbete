package unit;

import main.GamePanel;
import main.Utility;

import java.awt.*;

public class Star extends Unit{

    private final int defaultY;

    public Star(int size, int defaultY){
        image = new Utility().loadImage("heart", size, size);
        this.defaultY = defaultY;
        positionY = defaultY;
    }

    public void setStar(int screenX, int speed){
        positionX = screenX;
        this.speed = speed;
        alive = true;
    }

    public void update(GamePanel gp){
        if (alive){
            if(positionY >= gp.height){
                alive = false;
                positionY = defaultY;
            } else {
                positionY += speed;
            }
        }
    }
    public void reset(){
        alive = false;
        positionY = defaultY;
        }
    public void draw(Graphics2D g2){
        if(alive){
            //g2.drawImage(image, positionX, positionY, null);
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 16F));
            g2.setColor(Color.white);
            g2.drawString(".", positionX, positionY);

        }
    }
    }

