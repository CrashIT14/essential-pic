package se.creotec.essentialpic;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private ImageView imageMain;
    @FXML
    private Pane imageContainer;

    private static final double ZOOM_FACTOR = 1.1;
    private static final double MIN_IMG_SIZE = Util.EM;

    @FXML
    private void onAboutMenuClicked() {
        Util.getLogger().finest("About dialog");
        Util.showAboutDialog();
    }

    @FXML
    private void onCloseMenuClicked() {
        System.out.println("Close");
    }

    @FXML
    private void onImageContainerScroll(ScrollEvent ev) {
        // This handles zooming on the image.
        // TODO: Improve zooming with viewport somehow
        double currentWidth = imageMain.getFitWidth();
        double currentHeight = imageMain.getFitHeight();

        if (ev.getDeltaY() < 0) {
            imageMain.setFitWidth(currentWidth* ZOOM_FACTOR);
            imageMain.setFitHeight(currentHeight * ZOOM_FACTOR);
        } else if (currentWidth > MIN_IMG_SIZE || currentHeight > MIN_IMG_SIZE) {
            imageMain.setFitWidth(currentWidth / ZOOM_FACTOR);
            imageMain.setFitHeight(currentHeight / ZOOM_FACTOR);
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        imageMain.setFitWidth(imageMain.getImage().getWidth());
        imageMain.setFitHeight(imageMain.getImage().getHeight());
    }
}
