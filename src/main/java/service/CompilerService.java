package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import service.CompilerService;

public class MainController {

    /* =======================
       FXML-linked components
       ======================= */

    @FXML
    private TextField expressionField;

    @FXML
    private TextArea outputArea;


    @FXML
    public void onAnalyzeClicked() {

        outputArea.clear();

        String expression = expressionField.getText();

        // Input validation (frontend responsibility)
        if (expression == null || expression.trim().isEmpty()) {
            showAlert(
                    "Input Error",
                    "No expression provided",
                    "Please enter an arithmetic expression to analyze."
            );
            return;
        }

        try {
            // Call compiler core (backend)
            String response = CompilerService.analyze(expression);

            // Interpret compiler response
            if (response.contains("STATUS:OK")) {

                outputArea.setText(
                        "✅ Expression accepted\n\n" + response
                );

            } else {

                outputArea.setText(
                        "❌ Expression rejected\n\n" + response
                );
            }

        } catch (Exception e) {

            // System-level error (compiler not found, IO error, etc.)
            showAlert(
                    "Execution Error",
                    "Compiler execution failed",
                    "An internal error occurred while executing the compiler."
            );

            outputArea.setText("❌ Internal system error");
        }
    }


    private void showAlert(String title, String header, String content) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
