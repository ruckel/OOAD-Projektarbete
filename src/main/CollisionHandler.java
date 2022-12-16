package main;

import unit.Laser;
import unit.Obstacle;
import unit.Unit;

public class CollisionHandler {

    GamePanel gp;

    public CollisionHandler(GamePanel gp){
        this.gp = gp;
    }

    public boolean checkObstacle (Unit unit){

        for (int i = 0; i < gp.player.lasers.length; i++) {
            if(gp.player.lasers[i].alive){
                if (gp.player.lasers[i].hitBox.intersects(unit.hitBox)){
                    gp.player.lasers[i].alive = false;
                    gp.player.lasers[i].positionY = gp.player.lasers[i].defaultY;
                    return true;
                }
            }
        }
        if (gp.player.hitBox.intersects(unit.hitBox)){
            unit.alive = false;
            gp.player.alive = false;
            return true;
        }
        return false;
    }

    public boolean checkCollisionWithObstacle(Unit unit){

        for (Obstacle obs: gp.unitLoader.obstacles) {
            if(obs.alive){
                if (unit.hitBox.intersects(obs.hitBox)){
                    return true;
                }
            }
        }
        return false;
    }
}
