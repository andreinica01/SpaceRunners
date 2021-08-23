package view.SoundManager;

import java.io.File;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import Utilities.Parameters;

public class SoundManager {

    static private void playSound(String sound) {
        try {

            Clip bulletSound = AudioSystem.getClip();
            bulletSound.open(AudioSystem.getAudioInputStream(new File(Parameters.SoundFolder + sound)));

            bulletSound.start();

        } catch (Exception e) {

        }

    }

    static public void playBulletSound() {
        playSound("laser.wav");
    }

    static public void playShipPassing() {
        playSound("passingby.wav");
    }

    static public void playClashWall()
    {
        playSound("wall_clash.wav");
    }

}
