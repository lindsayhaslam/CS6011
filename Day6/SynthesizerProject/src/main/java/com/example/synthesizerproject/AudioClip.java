package com.example.synthesizerproject;

import java.util.Arrays;

public class AudioClip {
    //static constants for the duration, and sample rate (2.0 seconds, and 44100 respectively)
    public static final double duration = 2.0;
    public static final int sampleRate = 44100;
    public static int TOTAL_SAMPLES = (int) (duration * sampleRate);

    //a member variable that contains the actual byte array
    public byte[] byteArray;

    //Methods int getSample( index ) and setSample( index, value ) that return/set
    //the sample passed as an int. You will need to use bitwise operators to perform these conversions!
    //The ints that are passed/returned should be in the range of shorts.
    //These are the closest thing we can do in Java to overloading operator[].

    //Create a constructor?
    public AudioClip() {
        //Calculate the number of samples

        //Initialize data into the array
        //Multiply it by two to turn the ints into bytes?
        byteArray = new byte[TOTAL_SAMPLES * 2];
    }

    // Method to get a sample at a given index
    public int getSample(int index) {
        // Calculate the start index of the 16-bit sample (2 bytes)
        index = index * 2;
        int result = 0;
        // Combine the lower and upper bytes to form a 16-bit sample
        //Originally, these are ints (byte) makes it possible to be used as a byte in lower code
        byte byte1 = (byte) byteArray[index + 1];
        byte byte2 = (byte) byteArray[index];

        //Or the two bytes to get one and return them
        result = byte1 << 8 | (byte2 & 0xFF);
        return result;

//        byte byte1 = byteArray[index*2];
//        byte byte2 = byteArray[index*2 + 1];
//        int result = 0;
//
//        result = byte2 << 8 | (byte1 & 0xFF);
//        return result;
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
