package ui;

import main.GamePanel;
import main.Property;
import main.Utility;
import unit.Laser;
import unit.Obstacle;
import unit.Star;

import java.awt.*;
import java.awt.image.BufferedImage;

public class PlayState implements GameState {

    private boolean initiation = true;
    private int backgroundSpeed = 0;
    Property property = Property.getInstance();
    @Override
    public void draw(Graphics2D g2, GamePanel gp) {
        final BufferedImage background = new Utility().loadImage("background2", gp.width, gp.height);
        final BufferedImage background2 = new Utility().loadImage("background", gp.width, gp.height);
        final BufferedImage heart = new Utility().loadImage("heart", gp.size/3,gp.size/3);
        g2.drawImage(background, 0, 0,null);

        if (initiation){
            g2.drawImage(background2, 0, 0,null);
        } else if (!initiation && backgroundSpeed < gp.height) {
            g2.drawImage(background2, 0, backgroundSpeed, null);
            backgroundSpeed = backgroundSpeed + 5;
        }

        gp.player.draw(g2);
        for (Laser laser :
                gp.lasers) {
            laser.draw(g2);
        }
        for (Star star : gp.stars){
            star.draw(g2);
        }

        for (Obstacle ob :
                gp.obstacles) {
            ob.draw(g2);
        }

        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 12F));
        g2.setColor(Color.white);
        g2.drawString("Score: " + gp.player.getScore(), gp.size / 2, gp.size / 2);
        g2.drawString("Lives: ", gp.size / 2, gp.size-15);
        g2.drawString("Difficulty: " + property.getProperty("difficulty"), gp.size / 2, gp.size / 2 -15);


        if (gp.player.lives != 0) {
            int integer = 0;
            for (int i = 0; i < gp.player.lives + 1; i++) {
                g2.drawImage(heart, gp.size + integer + 5, gp.size -29, null);
                integer = heart.getWidth() * i;
            }
        }

        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 14F));

        if(gp.sound.isMuted()) {
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 14F));
            g2.drawString("mute", gp.size * 9 + 25, 15);
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
        for (Star star : gp.stars){
            star.update(gp);
        }
        for (Obstacle ob :
                gp.obstacles) {
            ob.update(gp);
        }

        if (initiation){
            initiation = false;
        }
        if (!gp.sound.getMusicPlaying()){
            gp.sound.playMusic();
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

    public void setInitiation(boolean initiation) {
        this.initiation = initiation;
    }
}
