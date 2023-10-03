package com.example.synthesizerproject;
import java.util.ArrayList;


public class Mixer implements AudioComponent {
    public ArrayList<AudioComponent> audioArray = new ArrayList<AudioComponent>();
//    Mixer(){
//        audioArray = new ArrayList<>();
//    }
//
//    public void ArraySize(){
//        System.out.println("Size: " + audioArray.size());
////    }
//    public void addInput (AudioComponent input){
//
//    }
    @Override
    public AudioClip getClip() {
        AudioClip result = new AudioClip();
        for (int i = 0; i < AudioClip.TOTAL_SAMPLES; i++) {
            int Sample=0;
            for(AudioComponent audioComponents : audioArray) {
                Sample +=(audioComponents.getClip().getSample(i));
            }
            int max = Short.MAX_VALUE;
            int min = Short.MIN_VALUE;
            if (Sample < min) {
                Sample = min;
            } else if (Sample > max) {
                Sample = max;
            }
            result.setSample(i, Sample);
        }
        return result;
    }

    @Override
    public boolean hasInput() {

        return false;
    }

    @Override
    public void connectInput(AudioComponent input) {
        audioArray.add(input);

    }
}
