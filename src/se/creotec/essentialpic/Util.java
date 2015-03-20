package se.creotec.essentialpic;

import javafx.scene.control.Alert;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.logging.Logger;

/**
 * @author Alexander Håkansson
 * @version 1.0.0
 * @since 2015-03-18
 */
public class Util {

    public static final double EM = Math.rint(new Text("").getLayoutBounds().getHeight());
    private static final Logger log = Logger.getLogger("EssentialPic");
    public static Stage MAIN_CONTEXT;

    // Don't allow instantiation
    private Util() {}

    public static Logger getLogger() {
        return log;
    }

    public static void showAboutDialog() {
        Alert aboutDialog = new Alert(Alert.AlertType.INFORMATION);
        aboutDialog.setTitle("About");
        aboutDialog.setHeaderText("About Essential Pic");
        aboutDialog.setContentText("Developer:\nAlexander Håkansson");
        aboutDialog.initModality(Modality.APPLICATION_MODAL);
        aboutDialog.show();
    }
}
