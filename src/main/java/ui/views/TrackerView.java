package ui.views;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import ui.NavigationManager;
import booking.Booking;

public class TrackerView extends VBox {
    public TrackerView(Booking booking) {
        this.setPadding(new Insets(40));
        this.setSpacing(30);
        this.getStyleClass().add("root");
        this.setAlignment(Pos.TOP_CENTER);

        Label title = new Label("Booking Confirmed");
        title.getStyleClass().add("heading");

        VBox card = new VBox(15);
        card.getStyleClass().add("card");
        card.setMaxWidth(500);
        card.setPadding(new Insets(30));

        // All values come from real Booking, Member, and LuxuryVehicle objects
        Label availStatus = new Label("Vehicle Status: " + (booking.getVehicle().isAvailable() ? "Available" : "Unavailable"));
        availStatus.setStyle("-fx-font-weight: bold; -fx-text-fill: " + (booking.getVehicle().isAvailable() ? "#28A745" : "#DC3545") + ";");

        card.getChildren().addAll(
            createRow("Member",     booking.getCustomer().getName()),
            createRow("Membership", booking.getCustomer().getClass().getSimpleName()),
            createRow("Vehicle",    booking.getVehicle().getVehicleInfo()),
            createRow("Duration",   booking.getRentalHours() + " hour(s)"),
            new Separator(),
            createRow("Total Cost", "$" + String.format("%.2f", booking.getFinalCost())),
            availStatus
        );

        Button doneBtn = new Button("Back to Fleet");
        doneBtn.getStyleClass().add("button-primary");
        doneBtn.setOnAction(e -> NavigationManager.getInstance().showFleetBrowser());

        Button returnBtn = new Button("Return Car");
        returnBtn.setStyle("-fx-background-color: #E8E8ED; -fx-background-radius: 12; -fx-padding: 10 20; -fx-cursor: hand;");
        returnBtn.setOnAction(e -> {
            booking.returnVehicle();
            availStatus.setText("Vehicle Status: Available");
            availStatus.setStyle("-fx-font-weight: bold; -fx-text-fill: #28A745;");
            returnBtn.setDisable(true);
        });

        HBox actions = new HBox(15, returnBtn, doneBtn);
        actions.setAlignment(javafx.geometry.Pos.CENTER);

        this.getChildren().addAll(title, card, actions);
    }

    private HBox createRow(String label, String value) {
        HBox row = new HBox();
        Label l = new Label(label);
        l.setStyle("-fx-font-weight: bold; -fx-min-width: 120px;");
        Label v = new Label(value);
        row.getChildren().addAll(l, v);
        return row;
    }
}
