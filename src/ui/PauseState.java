package ui;

import main.GamePanel;

import java.awt.*;

public class PauseState implements GameState{
    @Override
    public void draw(Graphics2D g2, GamePanel gp) {
        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 32F));
        g2.drawString("GAME IS PAUSED", getCenterForX(g2,"GAME IS PAUSED", gp), gp.height / 2);
    }

    @Override
    public void update(GamePanel gp) {

    }

    @Override
    public GameState setNext() {
        return new PlayState();
    }

    @Override
    public GameState setLast() {
        return new HomeState();
    }

    private int getCenterForX(Graphics2D g2, String text, GamePanel gp){
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        return gp.width/2 - length/2;
    }
}
