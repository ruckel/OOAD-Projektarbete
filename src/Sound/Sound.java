package Sound;

import main.GamePanel;
import ui.GameState;
import ui.State;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import static javax.sound.sampled.AudioSystem.getAudioInputStream;

public class Sound {

    GamePanel gp;
    Clip clip;
    Clip soundEffect;
    private int musicVolume = 2; // 0-10
    private int soundEffectVolume = 8; // 0-10
    private boolean muted = false;
    SoundTracks sound;
    public Sound(GamePanel gp){
        this.gp = gp;

    }


    public void playSoundEffect(SoundTracks sound) {
        File file = new File(sound.getSoundTrack());


        try (AudioInputStream in = getAudioInputStream(file)) {
            soundEffect = AudioSystem.getClip();
            soundEffect.open(in);

            float volume = (float) (soundEffectVolume*2)/10;
            FloatControl gainControl = (FloatControl) soundEffect.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(20f * (float) Math.log10(volume));

            soundEffect.start();

        } catch (UnsupportedAudioFileException
                 | LineUnavailableException
                 | IOException e) {
            throw new IllegalStateException(e);
        }
    }


    public void playMusic() {
        if (gp.getMusicPlaying()){
            return;

        /*
        Picks Enum based on gp State. Could probably be done better.
         */
        }
        if (gp.getState().toString().contains("Home")){
            sound = SoundTracks.MENU;
        } else if (gp.getState().toString().contains("Play")) {
            sound = SoundTracks.GAMEPLAY;
        } else if (gp.getState().toString().contains("Pause")) {
            sound = SoundTracks.MOOD;
        }

        /*
        Loads audiofile based on state, sets volume and then plays on continuous loop.
         */
        File file = new File(sound.getSoundTrack());
        try (AudioInputStream in = getAudioInputStream(file)) {
            clip = AudioSystem.getClip();
            clip.open(in);

            float volume = (float) (musicVolume*2)/10;
            FloatControl gainControl2 = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl2.setValue(20f * (float) Math.log10(volume));

            clip.start();
            clip.loop(clip.LOOP_CONTINUOUSLY);

        } catch (UnsupportedAudioFileException
                 | LineUnavailableException
                 | IOException e) {
            throw new IllegalStateException(e);
        }
        gp.setMusicPlaying(true);
    }


    public void stopSounds(){
        String gameState = gp.getState().toString();
        if (!gameState.contains("Play")){
            clip.stop();
            clip.drain();
            clip.flush();
            gp.setMusicPlaying(false);
        }
    }
    public void mute(){

        //TODO broken method
       /*
        if (!muted){
            gp.sound.musicVolume = 0;
            gp.sound.soundEffectVolume = 0;
            gp.sound.muted = true;
            gp.sound.stopSounds();
        } if (muted){

            gp.sound.musicVolume = musicVolume2;
            gp.sound.soundEffectVolume = soundEffectVolume2;
            gp.sound.muted = false;
        }

        */
    }
}