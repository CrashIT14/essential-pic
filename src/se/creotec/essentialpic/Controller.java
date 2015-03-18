package se.creotec.essentialpic;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.stage.Modality;

public class Controller {

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
}
