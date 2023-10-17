package com.example.synthesizerproject;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.event.ActionEvent;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;


public class VolumeAdjusterWidget extends AudioComponentWidget {
    // Add a separate label for this widget to display the volume
    private final Label volumeLabel_;
    Slider volumeSlider;
    Label volumeLabel;
    HBox baseLayout;
    VBox rightSide;


    VolumeAdjusterWidget(AudioComponent ac, AnchorPane parent) {
        // Call the constructor of the base class (AudioComponentWidgetBase)
        super(ac, parent);
        //VolumeSlider
        rightSide = new VBox();
        volumeLabel = new Label("Volume");
        volumeLabel.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 14));
        leftSide.getChildren().add(volumeLabel);
        volumeSlider = new Slider(0, 6, 3);
        volumeSlider.setStyle("-fx-color: #F18DBC");
        volumeSlider.setShowTickLabels(true);
        volumeSlider.setShowTickMarks(true);
        rightSide.getChildren().add(volumeSlider);
        super.baseLayout.getChildren().add(rightSide);


        //VBOX for RIGHT
        rightSide.setOnMousePressed(this::getPosinf);
        rightSide.setOnMouseDragged(this::moveWidget);
        //rightSide.getChildren().add(closeBtn);

        // Set the position of this widget
        this.getChildren().add(super.baseLayout);
        this.setLayoutX(500);
        this.setLayoutY(600);

        // Create a label specific to this widget to display the volume
        volumeLabel_ = new Label("Volume: " + getFormattedVolume());
         //Apply styles to the label (font family and text color)
        volumeLabel_.setStyle("-fx-font-family: 'Comic Sans MS';-fx-text-fill: #9B55E0");
        // Add the volume label to the left-side VBox of this widget
        baseLayout.getChildren().add(volumeLabel_);
    }

        private void handleVolumeSlider(MouseEvent mouseEvent) {
       AudioComponent volume = getAudioComponent();
        int result = (int) volumeSlider.getValue();
        volumeLabel.setText("Volume: " + result);
        ((SineWave) ac_).updateVolume(result);
    }

        // Override the handleSlider method from the base class
        // This method is called when the volume slider is dragged
//    @Override
//    protected void handleVolumeSlider(Object e) {
//        AudioComponent ac_ = getAudioComponent();
//        // Get the value of the volume slider
//        float sliderValue = (float) freqSlider.getValue();
//        // Update the label text with the selected volume
//        volumeLabel_.setText("Volume: " + getFormattedVolume());
//        // Update the volume scale field of the associated audio component
//        ((VolumeAdjuster) ac_).getVolumeScale((int) sliderValue);
//    }

         //Helper method to format the volume value with two decimal places
        private String getFormattedVolume() {
            float sliderValue = (float) volumeSlider.getValue();
            // Format the volume value with two decimal places
            return String.format("%.2f", sliderValue);
        }
    }
