package ui;

import main.GamePanel;

import java.awt.*;

public class State {

    GamePanel gp;
    private GameState currentScreen;


    public State(GamePanel gp){
        this.gp = gp;
    }
    public void draw(Graphics2D g2){
        currentScreen.draw(g2, gp);
    }
    public void update(){
        currentScreen.update(gp);
    }
    public void setCurrentScreen(GameState currentScreen){
        this.currentScreen = currentScreen;
    }
}
