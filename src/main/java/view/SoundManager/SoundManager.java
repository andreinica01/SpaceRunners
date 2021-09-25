package view.SoundManager;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import utilities.Parameters;
import utilities.VariousMagicNumbers;

public class SoundManager {

    private List<Clip> sounds;
    private static final int SOUND_MEMORY_BUFFER = 10;

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
            this.setVolume(soundClip, 10);
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
    public void setVolume(final Clip clip, final float volume) {
        final float newVolume = volume / VariousMagicNumbers.ONE_HUNDRED;

        if (newVolume < 0f || newVolume > 1f) {
            throw new IllegalArgumentException("Volume not valid: " + newVolume);
        }

        FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        gainControl.setValue(VariousMagicNumbers.TWENTY * (float) Math.log10(newVolume));
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
     * Play a sound when boss is damaged.
     */
    public void playBossDamaged() {
        this.playSound("bossDamage.wav");
    }

    /**
     * Play a sound when the boss dies.
     */
    public void playBossDeath() {
        this.playSound("bossDeath.wav");
    }

    /**
     * Play a sound when status is taken.
     */
    public void playStatusPick() {
        this.playSound("pickup.wav");
    }

    /**
     * Play a sound when status run off.
     */
    public void playStatusFinish() {
        this.playSound("off.wav");
    }
    
    /**
     * Play intro music for the game.
     */
    public void playMusicMenu() {
    	this.playSound("menu.wav");
    }
    
    /**
     * Play a sound when a button is clicked.
     */
    public void playButtonClicked() {
    	this.playSound("selection.wav");
    }
    
    /**
     * Play a sound when mouse stand on a button.
     */
    public void playMouseOnButton() {
    	this.playSound("cursor.wav");
    }

    /**
     * Clear the sound memory, this is due to ArrayList implementation.
     */
    public void cleanSoundMemory() {
        Iterator<Clip> soundsToRemove = this.sounds.subList(VariousMagicNumbers.ZERO, SOUND_MEMORY_BUFFER).iterator();

        while (soundsToRemove.hasNext()) {
            Clip v = soundsToRemove.next();
            if (!v.isRunning()) {
                v.flush();
                v.stop();
                v.close();
                soundsToRemove.remove();
            }
        }
    }
}
