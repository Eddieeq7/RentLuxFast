package ui.components;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import vehicles.LuxuryVehicle;
import ui.NavigationManager;

public class VehicleCard extends VBox {
    public VehicleCard(LuxuryVehicle vehicle) {
        this.getStyleClass().add("card");
        this.setSpacing(10);
        this.setPrefWidth(280);

        ImageView imageView = new ImageView();
        imageView.setFitWidth(240);
        imageView.setFitHeight(150);
        imageView.setPreserveRatio(true);
        String path = resolveImagePath(vehicle);
        if (path != null) {
            try {
                imageView.setImage(new Image(getClass().getResourceAsStream(path)));
            } catch (Exception ignored) {}
        }

        Label name = new Label(vehicle.getBrand() + " " + vehicle.getModel());
        name.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        Label price = new Label("$" + (int) vehicle.calculateRentalCost(1) + "/hour");
        price.getStyleClass().add("subheading");

        Label status = new Label(vehicle.isAvailable() ? "Available" : "Booked");
        status.setStyle("-fx-text-fill: " + (vehicle.isAvailable() ? "#28A745" : "#DC3545") + "; -fx-font-weight: bold;");

        Button viewBtn = new Button("View Details");
        viewBtn.getStyleClass().add("button-primary");
        viewBtn.setMaxWidth(Double.MAX_VALUE);
        viewBtn.setOnAction(e -> NavigationManager.getInstance().showVehicleDetails(vehicle));

        this.getChildren().addAll(imageView, name, price, status, viewBtn);
    }

    public static String resolveImagePath(LuxuryVehicle vehicle) {
        return switch (vehicle.getBrand().toLowerCase()) {
            case "lamborghini" -> "/images/huracan.jpg";
            case "rolls royce" -> "/images/cullican.jpg";
            case "bugatti"     -> "/images/chiron.jpg";
            case "ferrari"     -> "/images/ferrari.jpg";
            default            -> null;
        };
    }
}
