package ui;

import Sound.Sound;
import main.GamePanel;

import java.awt.*;

public class State {
    private GameState currentScreen;

    public void draw(Graphics2D g2, GamePanel gp){
        currentScreen.draw(g2, gp);
    }
    public void update(GamePanel gp){
        currentScreen.update(gp);
    }
    public void setCurrentGameState(GameState currentScreen){
        this.currentScreen = currentScreen;
    }
    public GameState getCurrentGameState(){
        return currentScreen;
    }
    public void setNextState(){
        setCurrentGameState(currentScreen.setNext());
    }
    public void setLastState() {
        setCurrentGameState(currentScreen.setLast());
    }
}
