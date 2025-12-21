package screens;

import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import app.SoundManager;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class TutorialsExpression {

    public void start(Stage stage) {
        System.out.println("Tutorials screen started.");
        Label title = new Label("COMPILATION PLAYGROUND -- TUTORIALS");
        title.setFont(GomePixel.size(36));
        title.setTextFill(Color.web("#2855b8ff")); 
        Label subtitle = new Label("THIS IS WHERE TUTORIALS WILL BE DISPLAYED");
        subtitle.setFont(GomePixel.size(24));
        subtitle.setTextFill(Color.web("#219fa5ff"));
        VBox textBox = new VBox(10, title, subtitle);
        textBox.setAlignment(Pos.CENTER);
        textBox.setPadding(new Insets(20));
        StackPane root = new StackPane(textBox);
        Scene scene = new Scene(root, 850, 600);
        stage.setScene(scene);
        scene.setOnMousePressed(e -> {
        SoundManager.playClick();
        });
        Image cursorImage = new Image(getClass().getResourceAsStream("/images/cursor.png"));
        scene.setCursor(new ImageCursor(cursorImage));
        stage.setTitle("Compilation Playground");
        stage.show();
    }
}