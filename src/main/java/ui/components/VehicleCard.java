package ui.components;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import vehicles.LuxuryVehicle;
import booking.Booking;
import ui.NavigationManager;
import ui.views.FleetBrowserView;

public class VehicleCard extends VBox {
    public VehicleCard(LuxuryVehicle vehicle, Booking booking) {
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

        boolean booked = booking != null;

        Label status = new Label(booked ? "Unavailable" : "Available");
        status.setStyle("-fx-text-fill: " + (booked ? "#DC3545" : "#28A745") + "; -fx-font-weight: bold;");

        if (booked) {
            Button returnBtn = new Button("Return Car");
            returnBtn.setStyle(
                "-fx-background-color: transparent; " +
                "-fx-border-color: #DC3545; " +
                "-fx-border-radius: 8; " +
                "-fx-background-radius: 8; " +
                "-fx-text-fill: #DC3545; " +
                "-fx-font-weight: bold; " +
                "-fx-cursor: hand; " +
                "-fx-padding: 8 16;"
            );
            returnBtn.setMaxWidth(Double.MAX_VALUE);
            returnBtn.setOnAction(e -> {
                booking.returnVehicle();
                FleetBrowserView.activeBookings.remove(vehicle.getVehicleId());
                NavigationManager.getInstance().showFleetBrowser();
            });
            this.getChildren().addAll(imageView, name, price, status, returnBtn);
        } else {
            Button viewBtn = new Button("View Details");
            viewBtn.getStyleClass().add("button-primary");
            viewBtn.setMaxWidth(Double.MAX_VALUE);
            viewBtn.setOnAction(e -> NavigationManager.getInstance().showVehicleDetails(vehicle));
            this.getChildren().addAll(imageView, name, price, status, viewBtn);
        }
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
