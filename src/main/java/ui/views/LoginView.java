package ui.views;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class LoginView extends StackPane {
    public LoginView(Runnable onLoginSuccess) {
        this.getStyleClass().add("root");

        VBox card = new VBox(20);
        card.getStyleClass().add("card");
        card.setMaxSize(420, 480);
        card.setAlignment(Pos.CENTER_LEFT);
        card.setPadding(new Insets(35));

        Label title = new Label("Welcome Back");
        title.getStyleClass().add("heading");

        Label subtitle = new Label("Sign in to complete your booking.");
        subtitle.getStyleClass().add("subheading");

        TextField nameField = new TextField();
        nameField.setPromptText("Full Name");
        nameField.getStyleClass().add("search-bar");
        nameField.setMaxWidth(Double.MAX_VALUE);

        TextField memberIdField = new TextField();
        memberIdField.setPromptText("Member ID  (e.g. M001 or M002)");
        memberIdField.getStyleClass().add("search-bar");
        memberIdField.setMaxWidth(Double.MAX_VALUE);

        Label tierLabel = new Label("");
        tierLabel.setStyle("-fx-font-weight: bold; -fx-text-fill: #1D1D1F;");

        memberIdField.textProperty().addListener((obs, old, val) -> {
            if (val.equalsIgnoreCase("M001"))      tierLabel.setText("Gold Membership Detected ✓");
            else if (val.equalsIgnoreCase("M002")) tierLabel.setText("Platinum Membership Detected ✓");
            else                                    tierLabel.setText("");
        });

        Label errorLabel = new Label("");
        errorLabel.setStyle("-fx-text-fill: #DC3545; -fx-font-size: 12px;");

        Button continueBtn = new Button("Continue to Booking");
        continueBtn.getStyleClass().add("button-primary");
        continueBtn.setMaxWidth(Double.MAX_VALUE);
        continueBtn.setOnAction(e -> {
            String name = nameField.getText().trim();
            String id   = memberIdField.getText().trim();
            if (name.isEmpty() || id.isEmpty()) {
                errorLabel.setText("Please fill in both fields before continuing.");
                return;
            }
            onLoginSuccess.run();
        });

        card.getChildren().addAll(title, subtitle, nameField, memberIdField, tierLabel, errorLabel, continueBtn);
        this.getChildren().add(card);
    }
}
