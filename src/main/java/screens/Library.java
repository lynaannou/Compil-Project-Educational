package screens;

import app.SoundManager;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

public class Library {

    public void start(Stage stage) {

        // ===== TITLE =====
        Text title = new Text("📚 EXPRESSION LIBRARY\n");
        title.setFont(GomePixel.size(34));
        title.setFill(Color.web("#C23BAA"));

        Text intro = new Text(
                "This library describes all arithmetic expressions supported by the compiler.\n"
              + "It explains operators, functions, syntax rules, and gives concrete examples.\n\n"
        );
        intro.setFont(GomePixel.size(18));
        intro.setFill(Color.web("#F672F4"));

        // ===== CONTENT =====
        TextFlow content = new TextFlow(
            section("🔢 Numbers",
                "Integers and decimal values are supported.\n",
                "Examples: 5, 3.14, 0.25\n\n"),

            section("➕ Arithmetic Operators",
                "Addition (+), subtraction (-), multiplication (*), and division (/).\n"
              + "Operator precedence is respected (* and / before + and -).\n",
                "Example: 2 + 3 * 4\n\n"),

            section("📐 Trigonometric Functions",
                "sin(x) : computes the sine of x (in radians)\n"
              + "cos(x) : computes the cosine of x\n"
              + "tan(x) : computes the tangent of x\n",
                "Examples: sin(3.14), cos(0), tan(1)\n\n"),

            section("📈 Exponential & Logarithmic",
                "exp(x) : computes e raised to the power x\n"
              + "ln(x)  : computes the natural logarithm of x\n",
                "Examples: exp(1), ln(2.71)\n\n"),

            section("⬜ Square Root",
                "sqrt(x) : computes the square root of x (x ≥ 0)\n",
                "Example: sqrt(16)\n\n"),

            section("📊 Functions with Multiple Arguments",
                "somme(a, b, c, ...)   : returns the sum of all arguments\n"
              + "produit(a, b, c, ...) : returns the product of all arguments\n"
              + "moyenne(a, b, c, ...) : returns the average value\n",
                "Examples:\n"
              + "somme(1, 2, 3)\n"
              + "produit(2, 3, 4)\n"
              + "moyenne(4, 6, 8)\n\n"),

            section("⚡ Power Function",
                "puissance(a, b) : computes a raised to the power of b\n",
                "Example:\n"
              + "puissance(2, 3) → 8\n\n"),

            section("❌ Invalid Expressions",
                "The following expressions are rejected by the compiler:\n",
                "5 / \n"
              + "somme()\n"
              + "3 + * 4\n"
              + "ln()\n"
              + "exp()\n"
              + "sqrt()\n"
              + "produit()\n"
              + "moyenne()\n"
              + "5 / 0\n"
              + "sqrt(-4)\n"
              + "ln(-1)\n"
        )
        );

        ScrollPane scroll = new ScrollPane(content);
        scroll.setFitToWidth(true);
        scroll.setStyle("-fx-background-color: transparent;");

        Button backButton = new Button("← Back");
        backButton.setFont(GomePixel.size(16));
        backButton.setStyle(
                "-fx-background-color: #F672F4;" +
                "-fx-text-fill: white;" +
                "-fx-background-radius: 12;"
        );

        backButton.setOnAction(e -> {
            TutorialsLibrary screen = new TutorialsLibrary();
            screen.start(stage);
        });

        VBox box = new VBox(25, title, intro, scroll, backButton);
        box.setAlignment(Pos.TOP_CENTER);
        box.setPadding(new Insets(30));
        box.setMaxWidth(720);

        StackPane root = new StackPane(box);
        root.setStyle("-fx-background-color: #FFF0FA;");

        Scene scene = new Scene(root, 850, 600);
        stage.setScene(scene);
        scene.setOnMousePressed(e -> {
        SoundManager.playClick();
        });
        Image cursorImage = new Image(getClass().getResourceAsStream("/images/cursor.png"));
        scene.setCursor(new ImageCursor(cursorImage));
        stage.setTitle("Expression Library");
        stage.show();
    }

    // ===== HELPER METHOD =====
    private TextFlow section(String title, String description, String example) {

        Text tTitle = new Text(title + "\n");
        tTitle.setFont(GomePixel.size(22));
        tTitle.setFill(Color.web("#C23BAA"));

        Text tDesc = new Text(description);
        tDesc.setFont(GomePixel.size(18));
        tDesc.setFill(Color.web("#F672F4"));

        Text tExample = new Text(example);
        tExample.setFont(ChonkyBits.size(18));
        tExample.setFill(Color.web("#FF9AD5"));

        return new TextFlow(tTitle, tDesc, tExample);
    }
}
