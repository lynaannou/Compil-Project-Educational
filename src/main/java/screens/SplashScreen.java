package screens;

import javafx.animation.PauseTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.io.File;

import app.SoundManager;
import javafx.util.Duration;

public class SplashScreen {

    public void start(Stage stage) {

        // --- Background image ---
        Image backgroundImage = new Image(
                getClass().getResource("/images/SplashScreen.jpeg").toExternalForm()
        );

        ImageView background = new ImageView(backgroundImage);
        background.setFitWidth(850);
        background.setFitHeight(600);
        background.setPreserveRatio(false);

        Label title = new Label("COMPILATION PLAYGROUND");
        title.setFont(GomePixel.size(18));
        title.setTextFill(Color.web("#F3D6C8")); // pink-beige-white

        DropShadow titleGlow = new DropShadow();
        titleGlow.setColor(Color.web("#F7E4DA")); // soft fluorescent glow
        titleGlow.setRadius(12);
        titleGlow.setSpread(0.25);

        title.setEffect(titleGlow);

        Label subtitle = new Label("LEARN PARSING  |  PLAY WITH GRAMMAR  |  HAVE FUN");
        subtitle.setFont(GomePixel.size(8));
        subtitle.setTextFill(Color.web("#EED4C2"));

        DropShadow subtitleGlow = new DropShadow();
        subtitleGlow.setColor(Color.web("#F7E4DA"));
        subtitleGlow.setRadius(8);
        subtitleGlow.setSpread(0.15);

        subtitle.setEffect(subtitleGlow);

        VBox textBox = new VBox(10, title, subtitle);
        textBox.setAlignment(Pos.CENTER);
        textBox.setPadding(new Insets(20));

        // subtle "screen glass" effect
        textBox.setOpacity(0.95);
        System.out.println("Log : Verifying changes in text position");
        textBox.setTranslateY(-1); // pushes text into monitor area

        StackPane root = new StackPane(background, textBox);

        Scene scene = new Scene(root, 850, 600);
        File icon = new File("/images/brain-icon.png");
        Image iconImage = new Image(icon.toURI().toString());
        stage.getIcons().add(iconImage);
        


        stage.setScene(scene);
        scene.setOnMousePressed(e -> {
        SoundManager.playClick();
        });
        Image cursorImage = new Image(getClass().getResourceAsStream("/images/cursor.png"));
        scene.setCursor(new ImageCursor(cursorImage));

        stage.setTitle("Compilation Playground");
        stage.show();

        PauseTransition pause = new PauseTransition(Duration.seconds(3.5));
       /*  pause.setOnFinished(e -> {
            Tutorials tutorialsScreen = new Tutorials();
            tutorialsScreen.start(stage);
        }); */
       /*  pause.setOnFinished(e -> {
            Expression expressionScreen = new Expression();
            expressionScreen.start(stage);
        }); */ 
        pause.setOnFinished(e -> {
            TutorialsLibrary tutorialsLibraryScreen = new TutorialsLibrary();
            tutorialsLibraryScreen.start(stage);
        });
        pause.play();
    }
}