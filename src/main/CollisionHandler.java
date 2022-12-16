package main;

import ui.EndScreen;
import unit.Laser;
import unit.Obstacle;
import unit.Unit;

public class CollisionHandler {

    GamePanel gp;

    public CollisionHandler(GamePanel gp){
        this.gp = gp;
    }

    public boolean checkObstacle (Unit unit){

        for (int i = 0; i < gp.lasers.length; i++) {
            if(gp.lasers[i].alive){
                if (gp.lasers[i].hitBox.intersects(unit.hitBox)){
                    gp.lasers[i].alive = false;
                    gp.lasers[i].positionY = gp.lasers[i].defaultY;
                    gp.currentScore++;
                    return true;
                }
            }
        }
        if (gp.player.hitBox.intersects(unit.hitBox)){
            unit.alive = false;
            gp.player.alive = false;
            gp.state.setCurrentScreen(new EndScreen());
            return true;
        }
        return false;
    }
}
