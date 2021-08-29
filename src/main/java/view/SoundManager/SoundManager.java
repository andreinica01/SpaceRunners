package view.SoundManager;

import Utilities.HUDParameters;
import Utilities.Parameters;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

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
            setVolume(soundClip, 30);
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
     * Set Volume level of a sound.
     * 
     * @param clip
     * @param level. Min: 0, Max: 100
     */
    public void setVolume(Clip clip, float volume) {
    	volume = volume / 100;
        if (volume < 0f || volume > 1f)
            throw new IllegalArgumentException("Volume not valid: " + volume);
        FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);        
        gainControl.setValue(20f * (float) Math.log10(volume));
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
        Iterator<Clip> soundsToRemove = this.sounds.subList(HUDParameters.ZERO, SOUND_MEMORY_BUFFER).iterator();

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