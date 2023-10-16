package com.example.synthesizerproject;
import java.util.ArrayList;


public class Mixer implements AudioComponent {
    public ArrayList<AudioComponent> audioArray;

    Mixer(){
        audioArray = new ArrayList<>();
    }
    @Override
    public AudioClip getClip() {
        AudioClip result = new AudioClip();
        ArrayList<AudioClip> audioClips = new ArrayList<>();
        //loop through arrays
        for (AudioComponent audioComponents : audioArray) {
            AudioClip clip = audioComponents.getClip();
            VolumeAdjuster lowerVolume = new VolumeAdjuster(.25);
            lowerVolume.connectInput(audioComponents);
            audioClips.add(lowerVolume.getClip());
        }

            for(AudioClip clip: audioClips){
            for (int i = 0; i < AudioClip.TOTAL_SAMPLES; i++) {
                int Sample = (result.getSample(i) + clip.getSample(i));
                int max = Short.MAX_VALUE;
                int min = Short.MIN_VALUE;
                //Clamping
                if (Sample < min) {
                    Sample = min;
                } else if (Sample > max) {
                    Sample = max;
                }
                result.setSample(i, Sample);
            }
        }
        return result;
    }

        @Override
        public boolean hasInput () {

            return !audioArray.isEmpty();
        }

        @Override
        public void connectInput (AudioComponent input){
            audioArray.add(input);

        }
    }
