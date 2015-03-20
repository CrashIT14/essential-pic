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

    private final Rectangle imageClippingRect = new Rectangle(0, 0);

    @FXML
    private void onAboutMenuClicked() {
        Util.getLogger().finest("About dialog");
        Util.showAboutDialog();
        System.out.println(imageContainer.getWidth());
        System.out.println(imageMain.getFitWidth());
    }

    @FXML
    private void onCloseMenuClicked() {
        System.out.println("Close");
    }

    @FXML
    private void onImageContainerScroll(ScrollEvent ev) {
        if (ev.getDeltaY() < 0) {
//            imageMain.setScaleX(imageMain.getScaleX() + 0.1);
//            imageMain.setScaleY(imageMain.getScaleY() + 0.1);
            imageMain.setFitWidth(imageMain.getFitWidth() * 1.1);
            imageMain.setFitHeight(imageMain.getFitHeight() * 1.1);
        } else {
//            imageMain.setScaleX(imageMain.getScaleX() - 0.1);
//            imageMain.setScaleY(imageMain.getScaleY() - 0.1);
            imageMain.setFitWidth(imageMain.getFitWidth() / 1.1);
            imageMain.setFitHeight(imageMain.getFitHeight() / 1.1);
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        imageMain.fitWidthProperty().bind(imageContainer.widthProperty());
//        imageMain.fitHeightProperty().bind(imageContainer.heightProperty());

        imageClippingRect.widthProperty().bind(imageContainer.widthProperty());
        imageClippingRect.heightProperty().bind(imageContainer.heightProperty());

        imageMain.setFitWidth(imageMain.getImage().getWidth());
        imageMain.setFitHeight(imageMain.getImage().getHeight());
        imageMain.setClip(imageClippingRect);
    }
}
