package ui.views;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import vehicles.LuxuryVehicle;
import ui.NavigationManager;

public class VehicleDetailView extends VBox {

    private static boolean isLoggedIn = false;

    public VehicleDetailView(LuxuryVehicle vehicle) {
        this.setPadding(new Insets(40));
        this.setSpacing(30);
        this.getStyleClass().add("root");

        // Back button
        Button backBtn = new Button("← Back to Fleet");
        backBtn.setStyle("-fx-background-color: transparent; -fx-cursor: hand; -fx-font-size: 14px;");
        backBtn.setOnAction(e -> NavigationManager.getInstance().showFleetBrowser());

        HBox content = new HBox(50);
        content.setAlignment(Pos.TOP_LEFT);

        // Hero image placeholder
        Rectangle heroImage = new Rectangle(580, 380, Color.web("#F0F0F2"));
        heroImage.setArcWidth(24);
        heroImage.setArcHeight(24);
        heroImage.setStroke(Color.web("#E0E0E5"));
        heroImage.setStrokeWidth(1.5);

        // Right panel
        VBox rightSide = new VBox(20);
        rightSide.setPrefWidth(400);

        Label vehicleTitle = new Label(vehicle.getBrand() + " " + vehicle.getModel());
        vehicleTitle.setStyle("-fx-font-size: 30px; -fx-font-weight: bold;");

        // Spec cards
        GridPane specs = new GridPane();
        specs.setHgap(15);
        specs.setVgap(15);
        specs.add(createInfoCard("Base Rate",  "$" + (int) vehicle.calculateRentalCost(1) + "/hr"), 0, 0);
        specs.add(createInfoCard("Status", vehicle.isAvailable() ? "✓ Available" : "✗ Booked"), 1, 0);

        // Booking form card
        VBox bookingForm = new VBox(15);
        bookingForm.getStyleClass().add("card");
        bookingForm.setPadding(new Insets(20));

        // Duration slider + live price label (OOP polymorphism in action)
        Label durationLabel = new Label("Rental Duration: 3 hrs");
        durationLabel.setStyle("-fx-font-weight: bold;");

        Slider slider = new Slider(1, 24, 3);
        slider.setShowTickLabels(true);
        slider.setMajorTickUnit(6);
        slider.setSnapToTicks(false);

        Label pricePreview = new Label("Estimated Total: $" + (int) vehicle.calculateRentalCost(3));
        pricePreview.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: #1D1D1F;");

        // Update label live as slider moves — calls the OOP method directly
        slider.valueProperty().addListener((obs, old, val) -> {
            int hours = val.intValue();
            durationLabel.setText("Rental Duration: " + hours + (hours == 1 ? " hr" : " hrs"));
            double cost = vehicle.calculateRentalCost(hours);
            pricePreview.setText("Estimated Total: $" + (int) cost);
        });

        // Delivery toggle
        CheckBox deliveryToggle = new CheckBox("Enable Home Delivery  (+$25)");
        TextField addressField = new TextField();
        addressField.setPromptText("Enter delivery address...");
        addressField.setVisible(false);
        addressField.setManaged(false);
        deliveryToggle.setOnAction(e -> {
            boolean on = deliveryToggle.isSelected();
            addressField.setVisible(on);
            addressField.setManaged(on);
        });

        Button bookBtn = new Button("Book Now");
        bookBtn.getStyleClass().add("button-primary");
        bookBtn.setMaxWidth(Double.MAX_VALUE);
        bookBtn.setOnAction(e -> handleBooking(vehicle));

        bookingForm.getChildren().addAll(durationLabel, slider, pricePreview, new Separator(), deliveryToggle, addressField, bookBtn);
        rightSide.getChildren().addAll(vehicleTitle, specs, bookingForm);

        content.getChildren().addAll(heroImage, rightSide);
        this.getChildren().addAll(backBtn, content);
    }

    private VBox createInfoCard(String label, String value) {
        VBox box = new VBox(5);
        box.getStyleClass().add("card");
        box.setPadding(new Insets(15));
        box.setPrefWidth(185);
        Label l = new Label(label);
        l.getStyleClass().add("subheading");
        Label v = new Label(value);
        v.setStyle("-fx-font-weight: bold; -fx-font-size: 16px;");
        box.getChildren().addAll(l, v);
        return box;
    }

    private void handleBooking(LuxuryVehicle v) {
        if (!isLoggedIn) {
            NavigationManager.getInstance().showLogin(() -> {
                isLoggedIn = true;
                NavigationManager.getInstance().showTracker(null);
            });
        } else {
            NavigationManager.getInstance().showTracker(null);
        }
    }
}
