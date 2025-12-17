package app;

import javafx.application.Application;
import javafx.stage.Stage;
import screens.SplashScreen;
import screens.GomePixel;

public class MainApp extends Application {

    @Override
    public void start(Stage stage) {
        GomePixel.load();
        SplashScreen splash = new SplashScreen();
        splash.start(stage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}