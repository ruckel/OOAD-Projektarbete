package main;

import ui.HomeScreen;
import ui.PauseScreen;
import ui.PlayScreen;
import unit.Obstacle;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputHandler implements KeyListener {

    GamePanel gp;
    public boolean shoot = false;
    public boolean left = false;
    public boolean right = false;

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

        if(key == KeyEvent.VK_ENTER){
            gp.state.setCurrentScreen(new PlayScreen());
        }
        if(key == KeyEvent.VK_ESCAPE){
            gp.state.setCurrentScreen(new PauseScreen());
        }
        if(key == KeyEvent.VK_H){
            gp.state.setCurrentScreen(new HomeScreen());
            gp.currentScore = 0;
            for (Obstacle ob: gp.obstacles
                 ) {
                ob.reset();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_SPACE) {
            shoot = false;
        }
        if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) {
            left = false;
        } else if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) {
            right = false;
        }
    }
}
