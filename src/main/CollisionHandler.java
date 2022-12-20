package main;

import Sound.SoundTracks;
import ui.EndState;
import ui.PlayState;
import unit.Unit;

public class CollisionHandler {

    GamePanel gp;
    Property property = Property.getInstance();
    private boolean godMode = Boolean.parseBoolean(property.getProperty("godmode"));

    public CollisionHandler(GamePanel gp){
        this.gp = gp;
    }

    public boolean checkObstacle (Unit unit){

        for (int i = 0; i < gp.lasers.length; i++) {
            if(gp.lasers[i].alive){
                if (gp.lasers[i].hitBox.intersects(unit.hitBox)){
                    gp.sound.playSoundEffect(SoundTracks.COLLISION, Integer.parseInt(property.getProperty("meteoriteexplosionsound")));
                    gp.lasers[i].reset();
                    gp.player.incrementScore(true);
                    return true;
                }
            }
        }
        if (gp.player.hitBox.intersects(unit.hitBox)){
            unit.alive = false;
            if (gp.player.invincible || godMode) {
                gp.sound.playSoundEffect(SoundTracks.COLLISION, Integer.parseInt(property.getProperty("shieldsound")));
            } else if(!gp.player.invincible && !godMode) {
                gp.player.lives--;
                gp.player.invincible = true;
                gp.sound.playSoundEffect(SoundTracks.COLLISION, Integer.parseInt(property.getProperty("collisionsound")));
            } else if(gp.player.lives == 0 && !godMode){
                gp.setState(new EndState());
                gp.sound.playSoundEffect(SoundTracks.COLLISION, Integer.parseInt(property.getProperty("deadsound")));
                gp.sound.deathMute();
            }
            return true;
        }
        return false;
    }
}
