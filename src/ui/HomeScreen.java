package ui;

import main.GamePanel;

import java.awt.*;

public class HomeScreen implements Draw {

    @Override
    public void draw(Graphics2D g2, GamePanel gp) {

        int x = 0;

        int y = 0;

        g2.setColor(Color.DARK_GRAY);
        g2.fillRect(0, 0, gp.width, gp.height);

        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 32F));
        String title = "SPACE WAR YAY!!!!";

        g2.setColor(Color.black);
        g2.drawString(title, gp.size / 2 + 5, gp.size * 2 - 5);

        g2.setColor(Color.white);
        g2.drawString(title, gp.size / 2, gp.size * 2);


        x = getCenterForX(g2, "MENY VAL 1", gp);
        y = gp.size * 4;

        g2.drawString("MENY VAL 1", x, y);
        g2. drawString("->", x - gp.size / 2, y);

        y = gp.size * 5;
        g2.drawString("MENY VAL 2", x, y);
        g2. drawString("->", x - gp.size / 2, y);

        y = gp.size * 6;
        g2.drawString("MENY VAL 3", x, y);
        g2. drawString("->", x - gp.size / 2, y);




    }
    private int getCenterForX(Graphics2D g2, String text, GamePanel gp){
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        return gp.width/2 - length/2;
    }
}
