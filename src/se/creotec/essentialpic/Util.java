package se.creotec.essentialpic;

import javafx.scene.text.Text;

/**
 * @author Alexander Håkansson
 * @version 1.0.0
 * @since 2015-03-18
 */
public class Util {

    public static final double EM = Math.rint(new Text("").getLayoutBounds().getHeight());

    // Don't allow instantiation
    private Util() {}
}
