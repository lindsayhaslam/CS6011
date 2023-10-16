package com.example.synthesizerproject;
import javafx.event.ActionEvent;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class AudioComponentWidget extends Pane {
    AudioComponent ac_;
    AnchorPane parent_;
    static Slider freqSlider;
    Slider volumeSlider;


    Line line_;
    double mouseXpos, mouseYpos, widgetXpos, widgetYpos;

    Label frequencyLabel;
    Label volumeLabel;
    //constructor
    AudioComponentWidget(AudioComponent ac, AnchorPane parent){
        ac_=ac;
        parent_=parent;
        HBox baseLayout=new HBox();
        baseLayout.setStyle("-fx-background-color: #FFECF6; -fx-border-color: white; -fx-border-width: 2px; -fx-font-family: 'Comic Sans MS'; -fx-font-weight: bold; -fx-text-fill: #E0218A; -fx-font-size: 14;");

        //VBOX for LEFT
        VBox leftSide = new VBox();
        frequencyLabel = new Label ("Frequency");
        frequencyLabel.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 14));
        leftSide.getChildren().add(frequencyLabel);
        freqSlider = new Slider(50, 2000, 300);
        freqSlider.setOnMouseDragged(this::handleSlider);
        freqSlider.setStyle("-fx-color: #F18DBC");
        freqSlider.setShowTickLabels(true);
        freqSlider.setShowTickMarks(true);
        leftSide.getChildren().add(freqSlider); //to check it later on

        //VolumeSlider
        //VBox leftSideVolume = new VBox();
        volumeLabel = new Label ("Volume");
        volumeLabel.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 14));
        leftSide.getChildren().add(volumeLabel);
        volumeSlider = new Slider(0, 6, 3);
        volumeSlider.setOnMouseDragged(this::handleVolumeSlider);
        volumeSlider.setStyle("-fx-color: #F18DBC");
        volumeSlider.setShowTickLabels(true);
        volumeSlider.setShowTickMarks(true);
        leftSide.getChildren().add(volumeSlider);

        leftSide.setOnMousePressed(this::getPosinf);
        leftSide.setOnMouseDragged(this::moveWidget);

        //VBOX for RIGHT
        VBox rightSide = new VBox();
        Button closeBtn=new Button("x");
        closeBtn.setStyle("-fx-background-color: #F18DBC; -fx-text-fill: white; -fx-border-color: white; -fx-border-width: 2px; -fx-padding: 8px; -fx-font-size: 12px;");
        closeBtn.setOnAction(this::closeWidget);
        Circle output = new Circle(10);
        output.setStyle("-fx-fill: #E0218A");
        //rightSide.getChildren().add(closeBtn);

        //Handle drawing the line - handle 3 events
        output.setOnMousePressed(e->startConnection(e,output));
        output.setOnMouseDragged(e->moveConnection(e, output));
        output.setOnMouseReleased(e->endConnection(e, output));


        rightSide.getChildren().add(closeBtn);
        rightSide.getChildren().add(output);
        //Alignment and padding
        rightSide.setAlignment(Pos.CENTER);
        rightSide.setPadding(new Insets(5));
        rightSide.setSpacing(5);

        baseLayout.getChildren().add(leftSide);
        baseLayout.getChildren().add(rightSide);

        this.getChildren().add(baseLayout);
        this.setLayoutX(50);
        this.setLayoutY(50);
    }

    //FOR DRAWING THE LINE
    private void startConnection(MouseEvent e, Circle output) {

        if (line_!=null){
            parent_.getChildren().remove(line_);
        }

        Bounds parentBounds = parent_.getBoundsInParent();
        Bounds bounds = output.localToScene(output.getBoundsInLocal());

        line_ = new Line();
        line_.setStrokeWidth(4);
        line_.setStroke(Color.WHITE);
        line_.setStartX(bounds.getCenterX() - parentBounds.getMinX());
        line_.setStartY(bounds.getCenterY() - parentBounds.getMinY());
        line_.setEndX(e.getSceneX());
        line_.setEndY(e.getSceneY());
        parent_.getChildren().add(line_);
    }
    private void moveConnection(MouseEvent e, Circle output) {
        Bounds parentBounds = parent_.getBoundsInParent();
        line_.setEndX(e.getSceneX()-parentBounds.getMinX());
        line_.setEndY(e.getSceneY()-parentBounds.getMinY());
    }
    private void endConnection(MouseEvent e, Circle output) {
        Circle speaker = SynthesizeApplication.speaker; //get the speaker from the main application
        Bounds speakerBounds = speaker.localToScene(speaker.getBoundsInLocal());
        double distance = Math.sqrt(Math.pow(speakerBounds.getCenterX() - e.getSceneX(), 2.0) +  Math.pow(speakerBounds.getCenterY() - e.getSceneY(), 2.0));
        if (distance < 10){
            SynthesizeApplication.connectedWidgets.add(this);
        }
        else {
            parent_.getChildren().remove(line_);
            line_ = null;
        }

    }
    private void handleSlider(MouseEvent mouseEvent) {
        AudioComponent ac = getAudioComponent();
        int result = (int) freqSlider.getValue();
        frequencyLabel.setText("Frequency: " + result + " Hz");
        ((SineWave) ac_).updateFrequency(result);
    }

    private void handleVolumeSlider(MouseEvent mouseEvent) {
       AudioComponent volume = getAudioComponent();
        int result = (int) volumeSlider.getValue();
        volumeLabel.setText("Volume: " + result);
        ((SineWave) ac_).updateVolume(result);
    }
    private void closeWidget(ActionEvent e) {
        parent_.getChildren().remove(this);
        SynthesizeApplication.widgets.remove(this);
        SynthesizeApplication.connectedWidgets.remove(this);
        parent_.getChildren().remove(line_);
    }
    private void moveWidget(MouseEvent e) {
        double delX = e.getSceneX() - mouseXpos;
        double delY = e.getSceneY() - mouseYpos;
        relocate(delX + widgetXpos, delY + widgetYpos);
    }
    private void getPosinf(MouseEvent e){
        mouseXpos = e.getSceneX();
        mouseYpos = e.getSceneY();
        widgetXpos = this.getLayoutX();
        widgetYpos = this.getLayoutY();
    }

    public AudioComponent getAudioComponent(){
        return ac_;
    }
}