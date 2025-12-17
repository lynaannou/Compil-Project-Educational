package screens;

import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Expression {

    public void start(Stage stage) {

        Image backgroundImage = new Image(
                getClass().getResourceAsStream("/images/background-expression-screen.jpg")
        );

        ImageView background = new ImageView(backgroundImage);
        background.setPreserveRatio(false);
        background.setOpacity(0.3);
        background.fitWidthProperty().bind(stage.widthProperty());
        background.fitHeightProperty().bind(stage.heightProperty());

        Label title = new Label("Expression Parser");
        title.setFont(GomePixel.size(36));
        title.setTextFill(Color.web("#2855b8ff"));

        TextField expressionInput = new TextField();
        expressionInput.setFont(GomePixel.size(24));
        expressionInput.setPromptText("Type your expression here...");

        //button
        Button submitButton = new Button("Submit");
        submitButton.setFont(GomePixel.size(18));

        VBox textBox = new VBox(15, title, expressionInput, submitButton);
        textBox.setAlignment(Pos.CENTER);
        textBox.setPadding(new Insets(20));

        StackPane root = new StackPane(background, textBox);
        StackPane.setAlignment(textBox, Pos.TOP_CENTER);
        StackPane.setMargin(textBox, new Insets(60, 0, 0, 0));

        root.setBackground(new Background(
                new BackgroundFill(
                        Color.TRANSPARENT,
                        CornerRadii.EMPTY,
                        Insets.EMPTY
                )
        ));

        Scene scene = new Scene(root, 850, 600);

        stage.setScene(scene);
        stage.setTitle("Compilation Playground");
        stage.show();
    }
}
