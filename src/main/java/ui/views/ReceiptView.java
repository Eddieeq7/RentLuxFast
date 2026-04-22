package ui.views;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import ui.NavigationManager;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class ReceiptView extends VBox {
    public ReceiptView(Object booking) {
        this.setPadding(new Insets(40));
        this.setSpacing(30);
        this.getStyleClass().add("root");
        this.setAlignment(Pos.TOP_CENTER);

        VBox card = new VBox(25);
        card.getStyleClass().add("card");
        card.setMaxWidth(500);

        Label title = new Label("Reservation Receipt");
        title.getStyleClass().add("heading");

        VBox breakdown = new VBox(15);
        breakdown.getChildren().addAll(
            createLineItem("Base Rental (3 hours)", "$450.00"),
            createLineItem("Membership Discount (10%)", "-$45.00"),
            createLineItem("Delivery Fee", "$25.00"),
            new Separator(),
            createLineItem("Total", "$430.00", true)
        );

        HBox actions = new HBox(15);
        actions.setAlignment(Pos.CENTER);
        Button extendBtn = new Button("Extend Rental");
        extendBtn.setStyle("-fx-background-color: #E8E8ED; -fx-background-radius: 12; -fx-padding: 10 20;");
        extendBtn.setOnAction(e -> {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Rental Extended");
            alert.setHeaderText(null);
            alert.setContentText("Your rental has been extended by 2 hours. Updated total: $580.00");
            alert.showAndWait();
        });
        
        Button confirmBtn = new Button("Confirm & Close");
        confirmBtn.getStyleClass().add("button-primary");
        confirmBtn.setOnAction(e -> NavigationManager.getInstance().showFleetBrowser());

        actions.getChildren().addAll(extendBtn, confirmBtn);
        card.getChildren().addAll(title, breakdown, actions);
        this.getChildren().add(card);
    }

    private HBox createLineItem(String label, String value) {
        return createLineItem(label, value, false);
    }

    private HBox createLineItem(String label, String value, boolean bold) {
        HBox row = new HBox();
        Label l = new Label(label);
        Label v = new Label(value);
        if (bold) {
            l.setStyle("-fx-font-weight: bold; -fx-font-size: 18px;");
            v.setStyle("-fx-font-weight: bold; -fx-font-size: 18px;");
        } else {
            l.getStyleClass().add("subheading");
        }
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        row.getChildren().addAll(l, spacer, v);
        return row;
    }
}
