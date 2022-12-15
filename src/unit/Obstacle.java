package unit;

import main.GamePanel;

import java.awt.*;

public class Obstacle extends Unit{

    private final int defaultY;

    public Obstacle(GamePanel gp){
        super(gp);
        //image = loadSprite("obstacle");
        super.screenY = spawnY;
        defaultY = spawnY;
        this.endOfScreen = endOfScreen;

        hitBox.x = 13;
        hitBox.y = 20;
        hitBox.width = 66;
        hitBox.height = 54;

    }
    public void setObstacle(int screenX, int speed){
        super.screenX = screenX;
        super.speed = speed;
        alive = true;
    }

    public void update(){
        if (alive){
            if(screenY >= endOfScreen){
                alive = false;
                screenY = defaultY;
            } else {
                screenY += speed;
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
