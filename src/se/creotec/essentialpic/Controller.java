package se.creotec.essentialpic;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private ImageView imageMain;
    @FXML
    private Pane imageContainer;

    @FXML
    private void onAboutMenuClicked() {
        Util.getLogger().finest("About dialog");
        Alert aboutDialog = new Alert(Alert.AlertType.INFORMATION);
        aboutDialog.setTitle("About");
        aboutDialog.setHeaderText("About Essential Pic");
        aboutDialog.setContentText("Developer: Alexander Håkansson");
        aboutDialog.setResizable(false);
        aboutDialog.initModality(Modality.APPLICATION_MODAL);
        aboutDialog.show();
    }

    @FXML
    private void onCloseMenuClicked() {
        System.out.println("Close");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        imageMain.fitWidthProperty().bind(imageContainer.widthProperty());
        imageMain.fitHeightProperty().bind(imageContainer.heightProperty());
    }
}
