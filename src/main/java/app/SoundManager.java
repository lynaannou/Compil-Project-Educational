package app;

import javafx.scene.media.AudioClip;

public class SoundManager {

    private static final AudioClip clickSound =
        new AudioClip(SoundManager.class
            .getResource("/sounds/cursor-click-sound.wav")
            .toExternalForm());

    public static void playClick() {
        clickSound.play();
    }
}
