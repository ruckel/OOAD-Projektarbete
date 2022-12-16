package ui;

import main.GamePanel;
import unit.Laser;
import unit.Obstacle;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class PlayScreen implements GameState {

    private final BufferedImage background = loadImage("background");
    @Override
    public void draw(Graphics2D g2, GamePanel gp) {
        g2.drawImage(background, 0, 0, gp.width, gp.height, null);

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
        g2.drawString("Score: " + gp.currentScore, gp.size /2, gp.size /2);
    }

    @Override
    public void update(GamePanel gp) {
        gp.player.update();
        for (Laser laser :
                gp.lasers) {
            laser.update();
        }

        gp.unitLoader.update();
        for (Obstacle ob :
                gp.obstacles) {
            ob.update();
        }
    }

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
