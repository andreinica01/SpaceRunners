package view.SoundManager;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import Utilities.Parameters;

public class SoundManager {

    private List<Clip> sounds;
    private int SOUND_MEMORY_BUFFER = 30;

    public SoundManager()
    {
        this.sounds = new ArrayList<>();

    }

    private void playSound(String sound) {
        try {

            Clip soundClip = AudioSystem.getClip();
            soundClip.open(AudioSystem.getAudioInputStream(new File(Parameters.SoundFolder + sound)));

           
            soundClip.start();
            this.sounds.add(soundClip);

            if(this.sounds.size()>SOUND_MEMORY_BUFFER)
            {
                cleanSoundMemory();
            }

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

    public void cleanSoundMemory()
    {
        Iterator<Clip> soundsToRemove = this.sounds.subList(0, 10).iterator();
            
        while(soundsToRemove.hasNext())
        {
            Clip v = soundsToRemove.next();
            if(!v.isRunning())
            {
                v.flush();
                v.close();
                soundsToRemove.remove();
            }
           
        }
    }

  

}
