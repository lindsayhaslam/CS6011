package com.example.synthesizerproject;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.layout.HBox;

public class MixerWidget extends AudioComponentWidget {

    Label mixerLabel;
    HBox baseLayout;
    VBox rightSide;

    MixerWidget(AudioComponent ac, AnchorPane parent) {
        super(ac, parent);

        //MixerWidget
        rightSide = new VBox();
        mixerLabel = new Label("Mixer");
        mixerLabel.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 14));
        rightSide.getChildren().add(mixerLabel);
        super.baseLayout.getChildren().add(rightSide);

        //VBOX for RIGHT
        rightSide.setOnMousePressed(this::getPosinf);
        rightSide.setOnMouseDragged(this::moveWidget);

        // Set the position of this widget
        this.getChildren().add(super.baseLayout);
        this.setLayoutX(50);
        this.setLayoutY(60);
    }
}
