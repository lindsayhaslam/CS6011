package com.example.synthesizerproject;

public class VolumeAdjuster implements AudioComponent{
    //This variable is used to store the input audio component that the VolumeAdjuster will adjust.
    private AudioComponent input_;

    //This variable represents the scaling factor by which the volume of the input audio will be adjusted.
    public double volumeScale;

    //This is a constructor for the VolumeAdjuster class.
    // It takes a volumeScale parameter, which is used to initialize the volumeScale_ instance variable.
    public VolumeAdjuster(double volumeScale){

       this.volumeScale = volumeScale;
    }
    public void adjustVolume(double volumeScale) {
        this.volumeScale = volumeScale;
    }


    @Override
    //This method adjusts the volume of the input audio
    //and returns a new AudioClip object containing the adjusted audio.
    public AudioClip getClip() {
       AudioClip original = input_.getClip();
       AudioClip result = new AudioClip();
        //Loop iterates through each sample in the original audio clip and applies the volume scaling factor:
       for (int i = 0; i < AudioClip.TOTAL_SAMPLES; i++){
           int adjustedSample = (int)(volumeScale * original.getSample(i));
           int max = Short.MAX_VALUE;
           int min = Short.MIN_VALUE;

           //Clamping
           //Perform clamping to ensure that the adjusted sample value remains within the valid rang
           if (adjustedSample < min){
            adjustedSample = min;
           }
           else if (adjustedSample > max) {
               adjustedSample = max;
           }
           result.setSample(i, adjustedSample);
       }

       //Return adjusted result
        return result;
    }

    @Override
    //Indicates that "VolumeAdjuster" does not need an input
    public boolean hasInput() {

        return input_ != null;
    }

    @Override
    public void connectInput(AudioComponent input) {
        input_ = input;

    }

    public void updateVolume(int volume){
        volumeScale = volume;
    }
}
