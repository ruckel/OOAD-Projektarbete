package main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Utility {
    public BufferedImage loadImage(String name, int width, int height){
        BufferedImage img = null;
        try{
            img = scaleImage(ImageIO.read(getClass().getResourceAsStream("/" + name + ".png")), width, height);
        }catch (IOException e){
            e.printStackTrace();
        }
        return img;
    }
    private BufferedImage scaleImage(BufferedImage image, int width, int height){
        BufferedImage scaledImage = new BufferedImage(width, height, image.getType());
        Graphics2D g2 = scaledImage.createGraphics();
        g2.drawImage(image, 0, 0, width, height, null);
        g2.dispose();

        return scaledImage;
    }
    public BufferedImage getSubImage(BufferedImage sprite, int col, int row, int width, int height){
        return sprite.getSubimage((col * width) - width, (row * height) - height, width, height);
    }
}
