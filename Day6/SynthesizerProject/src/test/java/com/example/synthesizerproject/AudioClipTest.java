package com.example.synthesizerproject;
import org.junit.Test;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class AudioClipTest {
    @Test
    public void testGetSetSample() {
        AudioClip audioClip = new AudioClip();
        Random random = new Random();

        // Test with random sample values
        for (int i = 0; i < audioClip.getData().length / 2; i++) {
            int sample = random.nextInt(65536) - 32768; // Generate random short value
            audioClip.setSample(i, sample);
            assertEquals(sample, audioClip.getSample(i));
        }

        // Test with maximum and minimum short values
        audioClip.setSample(0, Short.MAX_VALUE);
        assertEquals(Short.MAX_VALUE, audioClip.getSample(0));

        audioClip.setSample(1, Short.MIN_VALUE);
        assertEquals(Short.MIN_VALUE, audioClip.getSample(1));

    }

    @Test
    public void testGetData(){

    }

}