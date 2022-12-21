package main;

import Sound.SoundTracks;
import ui.EndState;
import unit.Unit;

public class CollisionHandler {

    private final GamePanel gp;

    public CollisionHandler(GamePanel gp){
        this.gp = gp;
    }

    public boolean checkObstacle (Unit unit){

        for (int i = 0; i < gp.lasers.length; i++) {
            if(gp.lasers[i].alive){
                if (gp.lasers[i].hitBox.intersects(unit.hitBox)){
                    gp.sound.playSoundEffect(SoundTracks.COLLISION);
                    gp.lasers[i].reset();
                    gp.player.incrementScore();
                    unit.lives--;
                    if (unit.lives == 0){
                        resetObstacleLives();
                        return true;
                    }
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
            gp.sound.playSoundEffect(SoundTracks.COLLISION);
            return true;
        }
        return false;
    }

    public void resetObstacleLives(){
        for(int i = 0; i < gp.obstacles.length; i++){
            if (gp.obstacles[i].id == 1){
                gp.obstacles[i].lives = 1;
            } else {
                gp.obstacles[i].lives = 5;
            }
        }
    }
}
