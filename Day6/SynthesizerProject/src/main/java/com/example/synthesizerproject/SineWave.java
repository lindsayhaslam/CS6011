package com.example.synthesizerproject;

public class SineWave implements AudioComponent {
    //Declare variables
    private double frequency_;
    private int volume_;
    private AudioClip audioClip;
    int numSamples = (int) (AudioClip.duration * AudioClip.sampleRate);

    //Constructor
    public SineWave(double frequency) {
        frequency_ = frequency;
        this.audioClip = new AudioClip();
    }


    @Override
    //Method generates a sine wave audio clip based on the specified frequency and returns it.
    public AudioClip getClip() {
        double maxValue = Short.MAX_VALUE;
        //Iterates from 0 to numSamples
        for (int i = 0; i < numSamples; i++) {
            //calculates the sine wave value for the current sample using frequency and sample rate (AudioClip class)
            double sineValue = Math.sin(2 * Math.PI * frequency_ * i / AudioClip.sampleRate);
            int sampleValue = (int) (maxValue * sineValue);
            audioClip.setSample(i, sampleValue);
        }

        return audioClip;
    }

    @Override
    //SineWave class does not require an output
    public boolean hasInput() {

        return false;
    }

    @Override
    public void connectInput(AudioComponent input) {

    }

    public void updateFrequency(int frequency){
        frequency_=frequency;
    }

    //Volume
    public void updateVolume(int volume){
        volume_=volume;
    }
}
