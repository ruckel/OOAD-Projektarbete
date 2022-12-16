package ui;

import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;


public class HomeScreen implements Draw {


    @Override
    public void draw(Graphics2D g2, GamePanel gp) {

        g2.setBackground(Color.DARK_GRAY);
        g2.fillRect(0, 0, gp.width, gp.height);

        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 110F));
        String title = "Space War";
        g2.drawString(title, gp.size/2, gp.size*2);
        getCenterForX(g2, title, gp);

        g2.setColor(Color.black);
        g2.drawString(title, gp.size / 2 - 5, gp.size * 2 + 5);

        g2.setColor(Color.white);
        g2.drawString(title, gp.size / 2, gp.size * 2);


        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 20F));
        g2.drawString("press enter to start new game", gp.size/2+155, gp.size*3);
        getCenterForX(g2, "press enter to start new game", gp);

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 20F));
        g2.drawString("or esc to exit screen", gp.size/2+175, gp.size*3+40);
        getCenterForX(g2, "or esc to exit screen", gp);


    }
    private int getCenterForX(Graphics2D g2, String text, GamePanel gp){
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        return gp.width/2 - length/2;
    }
}
