package ui.components;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import vehicles.LuxuryVehicle;
import ui.NavigationManager;

public class VehicleCard extends VBox {
    public VehicleCard(LuxuryVehicle vehicle) {
        this.getStyleClass().add("card");
        this.setSpacing(10);
        this.setPrefWidth(280);

        // Placeholder for image
        Rectangle imagePlaceholder = new Rectangle(240, 150, Color.web("#F5F5F7"));
        imagePlaceholder.setArcWidth(15);
        imagePlaceholder.setArcHeight(15);

        Label name = new Label(vehicle.getBrand() + " " + vehicle.getModel());
        name.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        Label price = new Label("$" + vehicle.calculateRentalCost(1) + "/hour");
        price.getStyleClass().add("subheading");

        Label status = new Label(vehicle.isAvailable() ? "Available" : "Booked");
        status.setStyle("-fx-text-fill: " + (vehicle.isAvailable() ? "#28A745" : "#DC3545") + "; -fx-font-weight: bold;");

        Button viewBtn = new Button("View Details");
        viewBtn.getStyleClass().add("button-primary");
        viewBtn.setMaxWidth(Double.MAX_VALUE);
        viewBtn.setOnAction(e -> NavigationManager.getInstance().showVehicleDetails(vehicle));

        this.getChildren().addAll(imagePlaceholder, name, price, status, viewBtn);
    }
}
