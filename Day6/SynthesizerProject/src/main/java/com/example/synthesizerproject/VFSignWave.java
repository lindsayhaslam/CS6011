package com.example.synthesizerproject;

public class VFSignWave implements AudioComponent {

    private double phase_=0;
    private AudioComponent input_;

    @Override
    public AudioClip getClip() {
        int maxValue = Short.MAX_VALUE;
        AudioClip audioClip = new AudioClip();
        AudioClip inputClip = input_.getClip();
        int numSamples = audioClip.TOTAL_SAMPLES;

        for (int i = 0; i < numSamples; i++) {
            phase_ += Math.sin(2 * Math.PI * inputClip.getSample(i)/audioClip.sampleRate);
            int sampleValue = (int) (maxValue * Math.sin(phase_));
            audioClip.setSample(i,sampleValue);
        }
        return audioClip;
    }

    @Override
    public boolean hasInput() {
        return true;
    }

    @Override
    public void connectInput(AudioComponent input) {
        input_ = input;

    }
}