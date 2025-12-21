package app;
import javafx.scene.Scene ;
import javafx.scene.image.Image;
import javafx.application.Application;
import javafx.stage.Stage;
import screens.SplashScreen;
import screens.ChonkyBits;
import screens.GomePixel;
import javafx.scene.ImageCursor;

public class MainApp extends Application {

    @Override
    public void start(Stage stage) {
        GomePixel.load();
        ChonkyBits.load();
        

        SplashScreen splash = new SplashScreen();
        splash.start(stage);
        
        Image icon = new Image(getClass().getResourceAsStream("/images/icone-b.jpg"));
        stage.getIcons().add(icon);
        
    }

    public static void main(String[] args) {
        launch(args);
    }
}