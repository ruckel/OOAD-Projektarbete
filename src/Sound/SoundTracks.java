package Sound;

public enum SoundTracks {
        MENU("src/resources/Skyrogue_menu.wav"),
        GAMEPLAY("src/resources/Skyrogue_bgm.wav"),
        PAUSE("src/resources/Skyrogue_mood.wav"),
        COLLISION("src/resources/Collision"),
        LASER("src/resources/Laser");

        private String soundTrack;

        SoundTracks(String soundTrack){
                this.soundTrack = soundTrack;
        }
        public String getSoundTrack(){
                return soundTrack;
        }
}
