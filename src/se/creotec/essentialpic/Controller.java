package se.creotec.essentialpic;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

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
        Util.showAboutDialog();
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
