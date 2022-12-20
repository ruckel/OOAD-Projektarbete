package ui;

import Sound.SoundTracks;
import main.GamePanel;

import java.awt.*;

public class PauseState implements GameState{
    @Override
    public void draw(Graphics2D g2, GamePanel gp) {

        int y = gp.size * 3;

        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 32F));
        g2.drawString("GAME IS PAUSED", getCenterForX(g2,"GAME IS PAUSED", gp), y);

        y += gp.size;

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 20F));
        g2.drawString("press enter to resume game", getCenterForX(g2, "press enter to resume game", gp), y);

        y += gp.size /2;

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 20F));
        g2.drawString("or esc to go home", getCenterForX(g2, "or esc to go home", gp), y);

        // TODO slightly broken
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
