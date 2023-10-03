package com.example.synthesizerproject;

public class SineWave implements AudioComponent{
    //Declare variables
    private double frequency;
    private AudioClip audioClip;
    int numSamples = (int) (AudioClip.duration * AudioClip.sampleRate);


    public SineWave(double frequency) {
        this.frequency = frequency;
        this.audioClip = new AudioClip();
    }


    @Override
    public AudioClip getClip() {
    double maxValue = Short.MAX_VALUE;

        for (int i = 0; i < numSamples; i++) {
            double sineValue = Math.sin(2 * Math.PI * frequency * i / AudioClip.sampleRate);
            int sampleValue = (int) (maxValue * sineValue);
            audioClip.setSample(i, sampleValue);
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
