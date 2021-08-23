package view.SoundManager;

import java.io.File;
import java.util.List;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import Utilities.Parameters;

public class SoundManager {

    private List<Clip> sounds;

    private void playSound(String sound) {
        try {

            Clip bulletSound = AudioSystem.getClip();
            bulletSound.open(AudioSystem.getAudioInputStream(new File(Parameters.SoundFolder + sound)));

            bulletSound.start();

        } catch (Exception e) {

        }

    }

    public void playBulletSound() {
        playSound("laser.wav");
    }

    public void playShipPassing() {
        playSound("passingby.wav");
    }

    public void playClashWall() {
        playSound("wall_clash.wav");
    }

    //to do

}
