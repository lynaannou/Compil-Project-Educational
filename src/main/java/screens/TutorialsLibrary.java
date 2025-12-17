package screens;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;    
import javafx.scene.paint.Color;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;

import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.util.Duration;
import javafx.animation.PauseTransition;
import javafx.scene.control.Button;

public class TutorialsLibrary {
    public void start(Stage stage) {
        // log for tutorials library screen start
        System.out.println("Tutorials Library screen started.");
        // pop-up image, where the text of the tutorial will be displayed
        Image popUpImage = new Image(
                getClass().getResourceAsStream("/images/tutorial-popup.png")
        );
        ImageView popup = new ImageView(popUpImage);
        popup.setFitWidth(800);
        popup.setFitHeight(750);
        popup.setPreserveRatio(true);

        // tutorial title, inside popup
        Label tutorial_title = new Label("✨ Welcome to the Compilation Playground ✨ \n");
        tutorial_title.setFont(GomePixel.size(28));
        tutorial_title.setTextFill(Color.web("#219fa5ff"));
        Label tutorial_content = new Label("This space is where your arithmetic expressions come to life.\r\n" + //
                        "Type an expression, and we’ll analyze it just like a real compiler would." );
        tutorial_content.setFont(GomePixel.size(18));
        tutorial_content.setTextFill(Color.web("#219fa5ff"));
        tutorial_content.setWrapText(true);
        tutorial_content.setMaxWidth(popup.getFitWidth() - 200); // padding from popup edges
        Label tutorial_content2 = new Label("Don’t worry — mistakes are part of the process.\r\n" +
                "We’ll point them out and help you fix them\r\n");

        tutorial_content2.setFont(GomePixel.size(18));
        tutorial_content2.setTextFill(Color.web("#219fa5ff"));
        tutorial_content2.setWrapText(true);
        tutorial_content2.setMaxWidth(popup.getFitWidth() - 200); // padding from popup edges
        // VBox to hold text elements (add more later)
        VBox textBox = new VBox(10); // spacing 10 between elements
        textBox.setAlignment(Pos.TOP_CENTER); // align children to top center
        textBox.setPadding(new Insets(300, 20, 20, 20));
        textBox.setMaxWidth(popup.getFitWidth());
        textBox.getChildren().add(tutorial_title); // add title
        textBox.getChildren().add(tutorial_content); // add content
        textBox.getChildren().add(tutorial_content2); // add content2
        //stack pane to hold popup and text inside popup
        StackPane popupContainer = new StackPane(popup, textBox);
        popupContainer.setMaxSize(popup.getFitWidth(), popup.getFitHeight());

        StackPane root = new StackPane(popupContainer);
        // background color
        root.setBackground(new Background(
                new BackgroundFill(
                Color.web("#f672f488"),
                CornerRadii.EMPTY,
                Insets.EMPTY
        )
        ));

        //scene setup
        Scene scene = new Scene(root, 850, 600);
        stage.setScene(scene);
        stage.setTitle("Compilation Playground");
        stage.show();
        // auto-transition to Expression screen after 7 seconds
  PauseTransition pause = new PauseTransition(Duration.seconds(7));
pause.setOnFinished(e -> {
    tutorial_content.setText("You can find all acceptable expressions in the Library\r\n");
    tutorial_content2.setText("You can head to the Expression Parser to try it out!");

    // Library button under first content
    Button btnLibrary = new Button("Library");
    btnLibrary.setFont(GomePixel.size(18));
    btnLibrary.setOnAction(event -> {
        // action for Library screen
    });

    // Expression button under second content
    Button btnExpression = new Button("Expression Parser");
    btnExpression.setFont(GomePixel.size(18));
    btnExpression.setOnAction(event -> {
        Expression expressionScreen = new Expression();
        expressionScreen.start(stage);
    });

    // Wrap content + buttons individually
    VBox librarySection = new VBox(10); // spacing 10px
    librarySection.setAlignment(Pos.TOP_CENTER);
    librarySection.getChildren().addAll(tutorial_content, btnLibrary);

    VBox expressionSection = new VBox(10);
    expressionSection.setAlignment(Pos.TOP_CENTER);
    expressionSection.getChildren().addAll(tutorial_content2, btnExpression);

    // Clear old children and add sections + title
    textBox.getChildren().clear();
    textBox.getChildren().addAll(tutorial_title, librarySection, expressionSection);
});
pause.play();

    }
}
