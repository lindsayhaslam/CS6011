module com.example.synthesizerproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.synthesizerproject to javafx.fxml;
    exports com.example.synthesizerproject;
}