package com.example.synthesizerproject;

public class LinearRamp implements AudioComponent{

    private final double start_;
    private final double stop_;

    public LinearRamp(double start, double stop){
        start_ = start;
        stop_ = stop;
    }
    @Override
    public AudioClip getClip() {
        AudioClip audioClip = new AudioClip();
        int numSamples = AudioClip.TOTAL_SAMPLES;
        //Iterates from 0 to numSamples
        for (int i = 0; i < numSamples; i++) {
            //calculates the sine wave value for the current sample using frequency and sample rate (AudioClip class)
            double sampleValue = ( start_ * ( numSamples - i ) + stop_ * i ) / numSamples;
            audioClip.setSample(i, (int) sampleValue);
        }

        return audioClip;
    }

    @Override
    public boolean hasInput() {
        return false;
    }

    @Override
    public void connectInput(AudioComponent input) {

    }
}
