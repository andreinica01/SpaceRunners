package view.SoundManager;

import Utilities.Parameters;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class SoundManager {

    private List<Clip> sounds;
    private int SOUND_MEMORY_BUFFER = 10;

    public SoundManager() {
        this.sounds = new ArrayList<>();
    }

    /**
     * Play a specific sound following an event.
     * @param sound to be played.
     */
    private void playSound(final String sound) {
        try {
            Clip soundClip = AudioSystem.getClip();
            soundClip.open(AudioSystem.getAudioInputStream(new File(Parameters.SoundFolder + sound)));
            

            soundClip.start();
            this.sounds.add(soundClip);

        } catch (Exception e) {
        } finally {

            if (this.sounds.size() > SOUND_MEMORY_BUFFER) {
                cleanSoundMemory();
            }
        }
    }

    /**
     * Play bullet sound.
     */
    public void playBulletSound() {
        this.playSound("bulletSound.wav");
    }

    /**
     * Play ship movement sound.
     */
    public void playShipPassing() {
        this.playSound("enemy.wav");
    }
    
    /**
     * Play player movement sound.
     */
    public void playPlayerMovementSound() {
        this.playSound("playerMovement.wav");
    }
    
    /**
     * Play death sound.
     */
    public void playDeathSound() {
        this.playSound("explosion.wav");
    }

    /**
     * Play player crashing into a wall sound.
     */
    public void playClashWall() {
        this.playSound("wall_clash.wav");
    }

    /**
     * Play an explosion sound.
     */
    public void playSpaceshipExplosion() {
        this.playSound("spaceship_explosion2.wav");
    }

    /**
     * Play a sound when something hits the player.
     */
    public void playPlayerImpact() {
        this.playSound("player_impact.wav");
    }

    /**
     * Clear the sound memory, this is due to ArrayList implementation.
     */
    private void cleanSoundMemory() {
        Iterator<Clip> soundsToRemove = this.sounds.subList(0, SOUND_MEMORY_BUFFER).iterator();

        while (soundsToRemove.hasNext()) {
            Clip v = soundsToRemove.next();
            if (!v.isRunning()) {
                v.flush();
                v.close();
                soundsToRemove.remove();
            }
        }
    }
}