package com.example.synthesizerproject;
//From the Main
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.AudioSystem;
import javafx.application.Application;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.Clip;
import java.io.FileInputStream;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;
import java.util.ArrayList;

public class SynthesizeApplication extends Application {

    AnchorPane mainCenter;
    public static Circle speaker;
    //Slider frequencySlider = new Slider(200, 400, 310);
    public static ArrayList<AudioComponentWidget> widgets = new ArrayList<>();
    public static ArrayList<AudioComponentWidget> connectedWidgets = new ArrayList<>();
    public static ArrayList<SineWave> sineWaves = new ArrayList();

    public VolumeAdjuster changeVolume;

    @Override
    public void start(Stage stage) throws IOException {
        BorderPane mainLayout = new BorderPane();

        //Right Panel
        VBox rightpanel = new VBox();
        rightpanel.setStyle("-fx-background-color: #F18DBC");
        rightpanel.setPadding(new Insets(4));
        rightpanel.setSpacing(10);
        Image image = new Image(new FileInputStream("/Users/lindsayhaslam/CS6011/Day6/Barbie.png"));

        //Setting the image view
        ImageView imageView = new ImageView(image);
        double bottomY = rightpanel.getHeight() - imageView.getFitHeight();

        //setting the fit height and width of the image view
        imageView.setFitHeight(40);
        imageView.setFitWidth(80);
        rightpanel.getChildren().add(imageView);
        imageView.setY(bottomY);

        //SineWave Button
        Button sinewaveBtn = new Button("SineWave");
        sinewaveBtn.setStyle("-fx-background-color: #FFECF6; -fx-text-fill: #E0218A; -fx-border-color: white; -fx-border-width: 2px; -fx-font-family: 'Comic Sans MS'; -fx-font-weight: bold; -fx-font-size: 14;");
        sinewaveBtn.setOnAction(this::createComponent);
        rightpanel.getChildren().add(sinewaveBtn);

        //Volume Button
        Button volumeBtn = new Button("Volume");
        volumeBtn.setStyle("-fx-background-color: #FFECF6; -fx-text-fill: #E0218A; -fx-border-color: white; -fx-border-width: 2px; -fx-font-family: 'Comic Sans MS'; -fx-font-weight: bold; -fx-font-size: 14;");
        volumeBtn.setOnAction(this::createVolume);
        rightpanel.getChildren().add(volumeBtn);

        //Linear Ramp Button
        Button linearRamp = new Button("Linear Ramp");
        linearRamp.setStyle("-fx-background-color: #FFECF6; -fx-text-fill: #E0218A; -fx-border-color: white; -fx-border-width: 2px; -fx-font-family: 'Comic Sans MS'; -fx-font-weight: bold; -fx-font-size: 14;");
        linearRamp.setOnAction(this::createComponent);
        rightpanel.getChildren().add(linearRamp);

        //Center Panel
        mainCenter = new AnchorPane();
        mainCenter.setStyle("-fx-background-color: #F7B9D7");

        //Define speaker so other classes can access it
        speaker = new Circle(400, 200, 15);
        speaker.setStyle("-fx-fill: #E0218A");
        mainCenter.getChildren().add(speaker);

        //Adding the AnchorPane's to the layout
        mainLayout.setCenter(mainCenter);
        mainLayout.setRight(rightpanel);

        //Bottom Panel
        HBox bottomPanel = new HBox();
        bottomPanel.setStyle("-fx-background-color: #FFECF6");
        bottomPanel.setAlignment(Pos.CENTER);
        Button playBtn=new Button("⫸");
        playBtn.setStyle("-fx-background-color: #F18DBC; -fx-text-fill: white; -fx-border-color: white; -fx-border-width: 2px;");
        playBtn.setOnAction(this::playAudio);
        bottomPanel.getChildren().add(playBtn);
        mainLayout.setBottom(bottomPanel);

        //The entire scene
        Scene scene = new Scene(mainLayout, 600, 400);
        stage.setTitle("✿ Synthesizer ✿");
        stage.setScene(scene);
        stage.show();
    }

    private void createComponent(ActionEvent e) {
        int frequency = 200;
        AudioComponent sineWave = new SineWave(frequency);
        sineWaves.add((SineWave) sineWave);
        SineWaveWidget sw = new SineWaveWidget(sineWave, mainCenter);
        mainCenter.getChildren().add(sw);
        widgets.add(sw);
    }

    // Creates a volume adjustment widget
    private void createVolume(ActionEvent e) {
        int initialVolume = 3;
        AudioComponent volume = new VolumeAdjuster(initialVolume);
        VolumeAdjusterWidget volumeWidget = new VolumeAdjusterWidget(volume, mainCenter);
        // Add the VolumeAdjusterWidget to the mainCenter AnchorPane
        mainCenter.getChildren().add(volumeWidget);
        // Add the widget to your list of widgets (if needed)
        widgets.add(volumeWidget);
    }

    private void playAudio(ActionEvent e) {
        //ATTEMPT OF NABIL'S CODE
        try{
            Clip c = AudioSystem.getClip();
            AudioFormat format16 = new AudioFormat(44100, 16, 1, true, false);
            Mixer mixer = new Mixer();
            for (AudioComponentWidget w:connectedWidgets){
                AudioComponent audioComponent = w.getAudioComponent();
                mixer.connectInput(audioComponent);
            }
            AudioClip clip = mixer.getClip();
            c.open(format16, clip.getData(), 0, clip.getData().length);
            c.start();
            AudioListener listener = new AudioListener(c);
            c.addLineListener(listener);
        } catch (LineUnavailableException k) {
            System.out.println(k.getMessage());
        }
    }

    public static void main (String[]args){
        launch();
    }
}