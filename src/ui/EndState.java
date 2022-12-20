package ui;

import main.GamePanel;

import java.awt.*;

public class EndState implements GameState{
    @Override
    public void draw(Graphics2D g2, GamePanel gp) {
        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 32F));
        g2.drawString("GAME OVERUUUU", getCenterForX(g2,"GAME OVERUUUU", gp), gp.height / 2);

        if(gp.sound.isMuted()) {
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 14F));
            g2.drawString("mute", gp.size * 9 + 25, 15);
        }
    }

    @Override
    public void update(GamePanel gp) {
        if (!gp.sound.getMusicPlaying()){
            gp.sound.playMusic();
        }

    }
    @Override
    public GameState setNext() {
        return new HomeState();
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
