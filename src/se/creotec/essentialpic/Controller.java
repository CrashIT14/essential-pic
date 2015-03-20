package se.creotec.essentialpic;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private ImageView imageMain;
    @FXML
    private Pane imageContainer;
    @FXML
    private Label statusText;

    @FXML
    private MenuItem menuItemZoomIn;
    @FXML
    private MenuItem menuItemZoomOut;
    @FXML
    private MenuItem menuItemClose;

    private static final double ZOOM_FACTOR = 1.1;
    private static final double MIN_IMG_SIZE = Util.EM;

    private BooleanProperty zoomingDisabled = new SimpleBooleanProperty(true);

    private File lastImagePath = new File(System.getProperty("user.home"));

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        menuItemZoomOut.disableProperty().bind(zoomingDisabled);
        menuItemZoomIn.disableProperty().bind(zoomingDisabled);

        if (imageMain.getImage() != null) {
            imageMain.setFitWidth(imageMain.getImage().getWidth());
            imageMain.setFitHeight(imageMain.getImage().getHeight());
        }

        // Show status message if there is no image
        statusText.setVisible(imageMain.getImage() == null);
    }

    @FXML
    private void onOpenMenuClicked() {
        if (!lastImagePath.exists()) {
            lastImagePath = new File(System.getProperty("user.home"));
            Util.getLogger().warning("Image path reset");
        }

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open a picture");
        fileChooser.setInitialDirectory(lastImagePath);
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Pictures", "*.png", "*.jpg", "*.jpeg", "*.gif"));
        File image = fileChooser.showOpenDialog(Util.MAIN_CONTEXT);
        if (image != null && image.isFile()) {
            lastImagePath = new File(image.getParent());
            String imagePath = image.toURI().toString();
            imageMain.setImage(new Image(imagePath));
            newImageOpened();
        }
    }

    @FXML
    private void onCloseMenuClicked() {
        imageMain.setImage(null);
        imageMain.setFitWidth(0);
        imageMain.setFitHeight(0);

        statusText.setVisible(true);
        menuItemClose.setDisable(true);
        zoomingDisabled.setValue(true);
    }

    @FXML
    private void onAboutMenuClicked() {
        Util.showAboutDialog();
    }

    @FXML
    private void onExitMenuClicked() {
        Util.getLogger().info("Program closed");
        System.exit(0);
    }

    @FXML
    private void onImageContainerScroll(ScrollEvent ev) {
        // This handles zooming on the image.
        // TODO: Improve zooming with viewport somehow
        if (ev.getDeltaY() < 0) {
            zoomInImage();
        } else if (imageMain.getFitWidth() > MIN_IMG_SIZE ||
                imageMain.getFitHeight() > MIN_IMG_SIZE) {
            zoomOutImage();
        }

    }

    @FXML
    private void onZoomInMenuClicked() {
        zoomInImage();
    }

    @FXML
    private void onZoomOutMenuClicked() {
        zoomOutImage();
    }

    private void zoomInImage() {
        if (!zoomingDisabled.get()) {
            imageMain.setFitWidth(imageMain.getFitWidth() * ZOOM_FACTOR);
            imageMain.setFitHeight(imageMain.getFitHeight() * ZOOM_FACTOR);
        }
    }

    private void zoomOutImage() {
        if (!zoomingDisabled.get()) {
            imageMain.setFitWidth(imageMain.getFitWidth() / ZOOM_FACTOR);
            imageMain.setFitHeight(imageMain.getFitHeight() / ZOOM_FACTOR);
        }
    }



    private void newImageOpened() {
        //View updates
        statusText.setVisible(false);
        menuItemClose.setDisable(false);
        zoomingDisabled.setValue(false);

        // Set image zoom
        double imageWidth = imageMain.getImage().getWidth();
        double imageHeight = imageMain.getImage().getHeight();
        double maxWidth = imageContainer.getWidth();
        double maxHeight = imageContainer.getHeight();
        if (imageWidth > maxWidth || imageHeight > maxHeight) {
            if (imageHeight > imageWidth) {
                imageMain.setFitHeight(maxHeight - Util.EM  * 2);
            } else {
                imageMain.setFitWidth(maxWidth - Util.EM * 2);
            }
        } else {
            imageMain.setFitWidth(imageWidth);
            imageMain.setFitHeight(imageHeight);
        }
    }
}
