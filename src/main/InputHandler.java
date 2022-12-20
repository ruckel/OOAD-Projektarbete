package main;

import unit.Player;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputHandler implements KeyListener {

    GamePanel gp;
    public boolean shoot = false;
    public boolean left = false;
    public boolean right = false;
    public boolean up = false;
    public boolean down = false;
    Property property = Property.getInstance();

    public InputHandler(GamePanel gp){
        this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_SPACE) {
            shoot = true;
        }

        if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) {
            left = true;
        } else if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) {
            right = true;
        }

        if (key == KeyEvent.VK_UP || key == KeyEvent.VK_A){
            up = true;
        } else if (key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) {
            down = true;
        }

        if(key == KeyEvent.VK_ENTER){
            gp.setNextState();
            gp.sound.stopSounds();
        }
        if(key == KeyEvent.VK_ESCAPE || key == KeyEvent.VK_P){
            gp.setLastState();
            gp.sound.stopSounds();
        }
        if (key == KeyEvent.VK_M) {
            gp.sound.mute();
        }
        if (key == KeyEvent.VK_1){
            property.setProperty("difficulty", "1");
            gp.player.setUpStats();
        }
        if (key == KeyEvent.VK_2){
            property.setProperty("difficulty", "2");
            gp.player.setUpStats();
        }
        if (key == KeyEvent.VK_3){
            property.setProperty("difficulty", "3");
            gp.player.setUpStats();
        }
        if (key == KeyEvent.VK_4){
            property.setProperty("difficulty", "4");
            gp.player.setUpStats();
        }
        if (key == KeyEvent.VK_5){
            property.setProperty("difficulty", "5");
            gp.player.setUpStats();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_SPACE) {
            shoot = false;
            gp.player.setLaserCoolDown(gp.player.getLaserCoolDownTimer()-1);
        }
        if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) {
            left = false;
        } else if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) {
            right = false;
        }
        if (key == KeyEvent.VK_UP || key == KeyEvent.VK_A){
            up = false;
        } else if (key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) {
            down = false;
        }
    }
}
