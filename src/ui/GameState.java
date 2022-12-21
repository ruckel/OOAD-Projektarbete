package ui;

import main.GamePanel;

import java.awt.*;

public interface GameState {
    void draw(Graphics2D g2, GamePanel gp);
    void update(GamePanel gp);
    GameState setNext();
    GameState setLast();
}
