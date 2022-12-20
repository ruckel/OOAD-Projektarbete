package Sound;

import main.GamePanel;
import ui.PlayState;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

import static javax.sound.sampled.AudioSystem.getAudioInputStream;

public class Sound {
    private int musicVolume = 2; // 0-10
    private int soundEffectVolume = 5; // 0-10

    GamePanel gp;
    Clip musicClip;
    Clip soundEffectClip;
    SoundTracks sound;

    private boolean musicPlaying = false;
    private boolean muted = false;


    public Sound(GamePanel gp){
        this.gp = gp;
    }


    public void playSoundEffect(SoundTracks sound) {
        File file = new File(sound.getSoundTrack());


        try (AudioInputStream in = getAudioInputStream(file)) {
            soundEffectClip = AudioSystem.getClip();
            soundEffectClip.open(in);

            float volumeOutput = (float) (soundEffectVolume*2)/10;
            FloatControl gainControl = (FloatControl) soundEffectClip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(20f * (float) Math.log10(volumeOutput));

            soundEffectClip.start();
            soundEffectClip.flush();

        } catch (UnsupportedAudioFileException
                 | LineUnavailableException
                 | IOException e) {
            throw new IllegalStateException(e);
        }
    }


    public void playMusic() {
        if (gp.sound.getMusicPlaying()) {
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
            sound = SoundTracks.PAUSE;
        }

        /*
        Loads audiofile based on state, sets volume and then plays on continuous loop.
         */
        File file = new File(sound.getSoundTrack());
        try (AudioInputStream in = getAudioInputStream(file)) {
            musicClip = AudioSystem.getClip();
            musicClip.open(in);

            float volumeOutput = (float) (musicVolume*2)/10;
            FloatControl gainControl = (FloatControl) musicClip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(20f * (float) Math.log10(volumeOutput));

            musicClip.start();
            musicClip.loop(musicClip.LOOP_CONTINUOUSLY);

        } catch (UnsupportedAudioFileException
                 | LineUnavailableException
                 | IOException e) {
            throw new IllegalStateException(e);
        }
        gp.sound.setMusicPlaying(true);
    }


    public void stopSounds(){
        musicClip.stop();
        musicClip.flush();
        gp.sound.setMusicPlaying(false);


    }

    public void setVolume(Clip clip, int volumeInput){

    }
    public void mute(){

        if (!muted){
            gp.sound.musicVolume = 0;
            gp.sound.soundEffectVolume = 0;
            gp.sound.muted = true;
            gp.sound.musicClip.stop();
        } else {
            gp.sound.musicVolume = 2;
            gp.sound.soundEffectVolume = 8;
            gp.sound.muted = false;
            gp.sound.musicClip.start();
            gp.sound.setMusicPlaying(true);
        }
    }

    public boolean isMuted() {
        return muted;
    }

    public boolean getMusicPlaying(){return musicPlaying;}
    public void setMusicPlaying(boolean bool){musicPlaying = bool;}
}