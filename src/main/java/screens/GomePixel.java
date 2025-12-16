package screens;

import javafx.scene.text.Font;

public final class GomePixel {

    private static Font BASE;

    public static void load() {
        System.out.println("GomePixel.load() CALLED");

        var url = GomePixel.class.getResource("/fonts/GOMEPIXEL.otf");
        System.out.println("Font URL = " + url);

        if (url == null) {
            System.err.println("Font resource NOT FOUND");
            return;
        }

        BASE = Font.loadFont(url.toExternalForm(), 10);

        if (BASE == null) {
            System.err.println("Font.loadFont returned null");
        } else {
            System.out.println("Font loaded: " + BASE.getFamily());
        }
    }

    public static Font size(double px) {
        if (BASE == null) {
            System.err.println(" Using fallback font");
            return Font.font(px);
        }
        return Font.font(BASE.getFamily(), px);
    }
}