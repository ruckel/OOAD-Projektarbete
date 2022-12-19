package ui;

import main.GamePanel;
import main.Utility;
import unit.Laser;
import unit.Obstacle;
import java.awt.*;
import java.awt.image.BufferedImage;

public class PlayState implements GameState {



    @Override
    public void draw(Graphics2D g2, GamePanel gp) {
        final BufferedImage background = new Utility().loadImage("background", gp.width, gp.height);
        final BufferedImage heart = new Utility().loadImage("heart2", gp.size/2,gp.size/2);
        g2.drawImage(background, 0, 0,null);

        gp.player.draw(g2);

        if(gp.player.invincible){
            
        }

        for (Laser laser :
                gp.lasers) {
            laser.draw(g2);
        }

        for (Obstacle ob :
                gp.obstacles) {
            ob.draw(g2);
        }

        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 12F));
        g2.setColor(Color.white);
        g2.drawString("Score: " + gp.player.getScore() + "    Lives: ", gp.size / 2, gp.size / 2);

        if(gp.player.lives == 3){
            g2.drawImage(heart, gp.size*2, gp.size/6, null);
            g2.drawImage(heart,gp.size*2+heart.getWidth(), gp.size/6, null);
            g2.drawImage(heart, gp.size*2+ heart.getWidth()*2,gp.size/6, null);
        } else if (gp.player.lives == 2) {
            g2.drawImage(heart, gp.size*2, gp.size/6, null);
            g2.drawImage(heart,gp.size*2+heart.getWidth(), gp.size/6, null);
        } else {
            g2.drawImage(heart, gp.size*2, gp.size/6, null);
        }

    }

    @Override
    public void update(GamePanel gp) {
        gp.player.update(gp);
        for (Laser laser :
                gp.lasers) {
            laser.update();
        }

        gp.unitLoader.update();
        for (Obstacle ob :
                gp.obstacles) {
            ob.update(gp);
        }
    }

    @Override
    public GameState setNext() {
        return this;
    }

    @Override
    public GameState setLast() {
        return new PauseState();
    }
}
