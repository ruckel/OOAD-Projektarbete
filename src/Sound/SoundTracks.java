package Sound;

public enum SoundTracks {
        MENU("src/resources/Skyrogue_menu.wav"),
        GAMEPLAY("src/resources/Skyrogue_bgm.wav"),
        PAUSE("src/resources/Skyrogue_mood.wav"),
        COLLISION("src/resources/Collision3.wav"),
        LASER("src/resources/Laser2.wav");

        private String soundTrack;

        SoundTracks(String soundTrack){
                this.soundTrack = soundTrack;
        }
        public String getSoundTrack(){
                return soundTrack;
        }
}
