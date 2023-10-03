package com.example.synthesizerproject;
import javax.sound.sampled.*;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
//import static javax.sound.sampled.AudioSystem.getClip;

public class Main {
    public static void main(String[] args) {
        try {
            // Get the properties from the system about sample rates, etc.
            Clip c = AudioSystem.getClip();

            // Define the audio format (44.1 KHz mono audio, 16 bits per sample).
            AudioFormat format16 = new AudioFormat(44100, 16, 1, true, false);

            // Create a SineWave generator (you may set your desired frequency).

//            AudioComponent gen = new SineWave(440);

            Mixer mixer = new Mixer();
            SineWave sineWave1 = new SineWave(220);
            SineWave sineWave2 = new SineWave(138);
            SineWave sineWave3 = new SineWave(164);

            mixer.connectInput(sineWave1);
            mixer.connectInput(sineWave2);
            mixer.connectInput(sineWave3);
//            mixer.ArraySize();

            //Create new object
            VolumeAdjuster lowerVolume = new VolumeAdjuster(3);
            lowerVolume.connectInput(mixer);
            //AudioClip clip = gen.getClip();

            //get the audio from your volume object and play it
            AudioClip clip = lowerVolume.getClip();


            // Open the audio clip for playback using the specified format and data.
            c.open(format16, clip.getData(), 0, clip.getData().length);

            System.out.println("About to play...");
            c.start(); // Start playing the audio clip.
            c.loop(2); // Play it 2 more times if desired, for a total of 6 seconds.

            // Wait for the sound to finish playing before exiting.
            //long startTime = System.currentTimeMillis();

            System.out.println("About to play...");
            c.start(); // Start playing the audio clip.
            c.loop(2); // Play it 2 more times for a total of 6 seconds.

            while (c.getFramePosition() < AudioClip.TOTAL_SAMPLES || c.isActive() || c.isRunning()) {
                // Do nothing while waiting for the note to play.
            }

            System.out.println("Done.");
            c.close();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }
}

