package se.creotec.essentialpic;

import javafx.scene.text.Text;

import java.util.logging.Logger;

/**
 * @author Alexander Håkansson
 * @version 1.0.0
 * @since 2015-03-18
 */
public class Util {

    public static final double EM = Math.rint(new Text("").getLayoutBounds().getHeight());
    private static final Logger log = Logger.getGlobal();

    // Don't allow instantiation
    private Util() {}

    public static Logger getLogger() {
        return log;
    }
}
