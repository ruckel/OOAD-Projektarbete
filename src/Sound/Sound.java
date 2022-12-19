package Sound;

import main.GamePanel;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

import static javax.sound.sampled.AudioSystem.getAudioInputStream;

public class Sound {
    private int musicVolume = 2; // 0-10
    private int soundEffectVolume = 8; // 0-10
    private boolean muted = false;

    GamePanel gp;
    Clip musicClip;
    Clip soundEffectClip;
    SoundTracks sound;

    public Sound(GamePanel gp){
        this.gp = gp;
    }


    public void playSoundEffect(SoundTracks sound) {
        File file = new File(sound.getSoundTrack());


        try (AudioInputStream in = getAudioInputStream(file)) {
            soundEffectClip = AudioSystem.getClip();
            soundEffectClip.open(in);
            setVolume(soundEffectClip, soundEffectVolume);
            soundEffectClip.start();
            soundEffectClip.flush();

        } catch (UnsupportedAudioFileException
                 | LineUnavailableException
                 | IOException e) {
            throw new IllegalStateException(e);
        }
    }


    public void playMusic() {
        if (gp.getMusicPlaying()) {
            return;
        }

        /*
        Picks Enum based on gp State. Could probably be done better.
         */

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
            musicClip = AudioSystem.getClip();
            musicClip.open(in);
            setVolume(musicClip, musicVolume);
            musicClip.start();
            musicClip.loop(musicClip.LOOP_CONTINUOUSLY);

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
            musicClip.stop();
            gp.setMusicPlaying(false);
        }
    }

    public void setVolume(Clip clip, int volumeInput){
        float volumeOutput = (float) (volumeInput*2)/10;
        FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        gainControl.setValue(20f * (float) Math.log10(volumeOutput));
    }
    public void mute(){

        //TODO broken method

        if (!muted){
            gp.sound.musicVolume = 0;
            gp.sound.soundEffectVolume = 0;
            gp.sound.muted = true;
            gp.sound.stopSounds();
        } else if (muted){
            gp.sound.musicVolume = 2;
            gp.sound.soundEffectVolume = 8;
            gp.sound.muted = false;
            gp.sound.playMusic();
        }


    }
}