package screens;
import service.CompilerService;

import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;


import java.io.IOException;

import app.SoundManager;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
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
        title.setTextFill(Color.web("#C23BAA"));

        TextField expressionInput = new TextField();
        expressionInput.setFont(ChonkyBits.size(24));
        expressionInput.setPromptText("Type your expression here...");
        expressionInput.setStyle("-fx-text-fill:rgb(255, 166, 225);");
        

        Button submitButton = new Button("Submit");
        submitButton.setFont(GomePixel.size(18));
        submitButton.setStyle(
                "-fx-background-color: #cfa6e2;" +
                "-fx-text-fill: white;" +
                "-fx-background-radius: 8;"
        );

        TextArea outputArea = new TextArea();
        outputArea.setEditable(false);
        outputArea.setFont(ChonkyBits.size(18));
        outputArea.setPromptText("Output will be displayed here...");
        outputArea.setWrapText(true);
        outputArea.setPrefHeight(180);
        outputArea.setBackground(new Background(
                new BackgroundFill(
                        Color.web("#f5efe8"),
                        new CornerRadii(6),
                        Insets.EMPTY
                )
        ));

        submitButton.setOnAction(e -> {
            String expression = expressionInput.getText().trim();
            if (expression.isEmpty()) {
                outputArea.setText("Please enter a valid expression.");
                return;
            }
            outputArea.setText("Parsing...");
            try {
                String compilerResult = CompilerService.analyze(expression);
                if (compilerResult.toLowerCase().contains("erreur")) {
                    outputArea.setText("Erreur: " + compilerResult);
                } else {
                    outputArea.setText("Success: " + compilerResult);
                }
            } catch (IOException ex) {
                outputArea.setText("An error occurred during compilation.");
            }
        });

        VBox textBox = new VBox(15, title, expressionInput, submitButton, outputArea);
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
        scene.setOnMousePressed(e -> {
        SoundManager.playClick();
        });

        Image cursorImage = new Image(getClass().getResourceAsStream("/images/cursor.png"));
        scene.setCursor(new ImageCursor(cursorImage));
        stage.setTitle("Compilation Playground");
        stage.show();
    }
}