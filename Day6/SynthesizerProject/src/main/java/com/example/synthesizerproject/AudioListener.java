package com.example.synthesizerproject;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.Clip;

public class AudioListener implements LineListener {
        public AudioListener(Clip c) {
            clip_ = c;
        }
        @Override
        public void update(LineEvent event) {
            if (event.getType() == LineEvent.Type.STOP) {
                clip_.close();
            }
        }
        private Clip clip_;
    }
