package main;

import unit.Laser;
import unit.Obstacle;
import unit.Unit;

public class CollisionHandler {

    public boolean checkObstacle (Unit unit){

        for (Laser laser: gp.player.lasers) {
            if (laser.alive){
                if (laser.hitBox.intersects(unit.hitBox)){
                    return true;
                }
            }
        }
        return gp.player.hitBox.intersects(unit.hitBox);
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
