package main;

import ui.EndState;
import ui.PlayState;
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
                    gp.lasers[i].reset();
                    gp.player.incrementScore();
                    return true;
                }
            }
        }
        if (gp.player.hitBox.intersects(unit.hitBox)){
            unit.alive = false;

            if(!gp.player.invincible) {
                gp.player.lives--;
                gp.player.invincible = true;
            }
            if(gp.player.lives == 0){
                gp.setState(new EndState());
            }
            return true;
        }
        return false;
    }
}
