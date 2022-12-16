package ui;

import main.GamePanel;

import java.awt.*;

public class DrawUI {

    private Draw currentScreen;

    public void draw(Graphics2D g2, GamePanel gp){
        currentScreen.draw(g2, gp);
    }
}
