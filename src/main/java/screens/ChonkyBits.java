package screens;

import javafx.scene.text.Font;

public final class ChonkyBits {

    private static Font BASE;

    public static void load() {
        var url = ChonkyBits.class.getResource(
                "/fonts/ChonkyBitsFontBold-lxeZ5.otf"
        );

        if (url == null) {
            System.err.println("ChonkyBits font not found");
            return;
        }

        BASE = Font.loadFont(url.toExternalForm(), 10);

        if (BASE == null) {
            System.err.println("Failed to load ChonkyBits font");
        }
    }

    public static Font size(double px) {
    if (BASE == null) {
        System.err.println("ChonkyBits BASE == null (font not loaded)");
        return Font.font(px);
    }
    return Font.font(BASE.getFamily(), px);
}

}
