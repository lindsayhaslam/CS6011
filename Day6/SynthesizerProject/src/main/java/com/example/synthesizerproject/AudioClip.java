package com.example.synthesizerproject;

import java.util.Arrays;

public class AudioClip {
    //static constants for the duration, and sample rate (2.0 seconds, and 44100 respectively)
    public static final double duration = 2.0;
    public static final int sampleRate = 44100;
    public static int TOTAL_SAMPLES = (int) (duration * sampleRate);

    //This array will hold the audio data.
    public byte[] byteArray;

    //Create a constructor
    public AudioClip() {
        //Calculate the number of samples
        //Initialize data into the array
        //Multiply it by two to turn the ints into bytes.
        byteArray = new byte[TOTAL_SAMPLES * 2];
    }

    // Method to get a sample at a given index
    public int getSample(int index) {
        // Calculate the start index of the 16-bit sample (2 bytes)
        index = index * 2;
        int result = 0;
        // Combine byte1 and byte2 to form a 16-bit sample
        //Originally, these are ints (byte) makes it possible to be used as a byte in lower code
        byte byte1 = byteArray[index + 1];
        byte byte2 = byteArray[index];

        //Or the two bytes to get one and return it.
        result = byte1 << 8 | (byte2 & 0xFF);
        return result;

    }

    public void setSample(int index, int value) {
        // Extract the bytes from the 16-bit value
        byte byte1 = (byte) (value >> 8);
        byte byte2 = (byte) (value & 0xFF);

        byteArray[(index * 2) + 1] = byte1;
        byteArray[index * 2] = byte2;

    }

    // Method to get a copy of the audio data
    public byte[] getData() {
        return Arrays.copyOf(byteArray, byteArray.length);
    }

}
