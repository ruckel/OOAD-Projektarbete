package ui;

import main.GamePanel;
import unit.Laser;
import unit.Obstacle;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class UI {

    GamePanel gp;

    HomeScreen homeScreen = new HomeScreen();

    private final BufferedImage background;


    public UI(GamePanel gp){
        this.gp = gp;

        background = loadImage("background");
    }
    public void update(){
//        gp.player.update();
//        for (Laser laser :
//                gp.lasers) {
//            laser.update();
//        }
//        gp.unitLoader.update();
//        for (Obstacle ob :
//                gp.obstacles) {
//            ob.update();
//        }
    }
    public void draw(Graphics2D g2){

        homeScreen.draw(g2, gp);

//        g2.drawImage(background,0, 0, null);
//        gp.player.draw(g2);
//        for (Laser laser:gp.lasers) {
//            laser.draw(g2);
//        }
//        for (Obstacle ob :
//                gp.obstacles) {
//            ob.draw(g2);
//        }
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
