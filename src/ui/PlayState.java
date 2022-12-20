package ui;

import Sound.SoundTracks;
import main.GamePanel;
import main.Utility;
import unit.Laser;
import unit.Obstacle;
import java.awt.*;
import java.awt.image.BufferedImage;

public class PlayState implements GameState {

    private final BufferedImage background = new Utility().loadImage("background", 640, 640);
    private boolean initiation = true;

    @Override
    public void draw(Graphics2D g2, GamePanel gp) {
        final BufferedImage background = new Utility().loadImage("background", gp.width, gp.height);
        final BufferedImage heart = new Utility().loadImage("heart", gp.size/3,gp.size/3);
        g2.drawImage(background, 0, 0,null);

        gp.player.draw(g2);
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
        g2.drawString("Score: " + gp.player.getScore(), gp.size / 2, gp.size / 2);
        g2.drawString("Lives: ", gp.width-gp.size*2-heart.getWidth(), gp.size / 2);

        if(gp.player.lives == 3){
            g2.drawImage(heart, gp.width-gp.size, gp.size/4, null);
            g2.drawImage(heart,gp.width-gp.size-heart.getWidth(), gp.size/4, null);
            g2.drawImage(heart, gp.width-gp.size-heart.getWidth()*2,gp.size/4, null);
        } else if (gp.player.lives == 2) {
            g2.drawImage(heart,gp.width-gp.size-heart.getWidth(), gp.size/4, null);
            g2.drawImage(heart, gp.width-gp.size-heart.getWidth()*2,gp.size/4, null);
        } else {
            g2.drawImage(heart, gp.width-gp.size-heart.getWidth()*2,gp.size/4, null);
        }


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
