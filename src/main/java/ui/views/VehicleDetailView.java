package ui.views;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import vehicles.LuxuryVehicle;
import members.GoldMember;
import ui.components.VehicleCard;
import ui.views.FleetBrowserView;
import members.PlatinumMember;
import members.Member;
import booking.Booking;
import ui.NavigationManager;

public class VehicleDetailView extends VBox {

    public VehicleDetailView(LuxuryVehicle vehicle) {
        this.setPadding(new Insets(40));
        this.setSpacing(30);
        this.getStyleClass().add("root");

        Button backBtn = new Button("← Back to Fleet");
        backBtn.setStyle("-fx-background-color: transparent; -fx-cursor: hand; -fx-font-size: 14px;");
        backBtn.setOnAction(e -> NavigationManager.getInstance().showFleetBrowser());

        HBox content = new HBox(50);
        content.setAlignment(Pos.TOP_LEFT);

        ImageView heroImage = new ImageView();
        heroImage.setFitWidth(540);
        heroImage.setFitHeight(360);
        heroImage.setPreserveRatio(true);
        String imgPath = VehicleCard.resolveImagePath(vehicle);
        if (imgPath != null) {
            try {
                heroImage.setImage(new Image(getClass().getResourceAsStream(imgPath)));
            } catch (Exception ignored) {}
        }

        VBox rightSide = new VBox(20);
        rightSide.setPrefWidth(400);

        Label vehicleTitle = new Label(vehicle.getBrand() + " " + vehicle.getModel());
        vehicleTitle.setStyle("-fx-font-size: 30px; -fx-font-weight: bold;");

        GridPane specs = new GridPane();
        specs.setHgap(15);
        specs.setVgap(15);
        specs.add(createInfoCard("Base Rate", "$" + (int) vehicle.calculateRentalCost(1) + "/hr"), 0, 0);
        specs.add(createInfoCard("Status", vehicle.isAvailable() ? "✓ Available" : "✗ Booked"), 1, 0);

        VBox bookingForm = new VBox(15);
        bookingForm.getStyleClass().add("card");
        bookingForm.setPadding(new Insets(20));

        // Duration slider
        Label durationLabel = new Label("Rental Duration: 3 hrs");
        durationLabel.setStyle("-fx-font-weight: bold;");

        int[] selectedHours = {3};

        Slider slider = new Slider(1, 24, 3);
        slider.setShowTickLabels(true);
        slider.setMajorTickUnit(6);
        slider.setSnapToTicks(false);

        Label pricePreview = new Label("Estimated Total: $" + (int) vehicle.calculateRentalCost(3));
        pricePreview.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: #1D1D1F;");

        // Membership selector — demonstrates polymorphism (GoldMember vs PlatinumMember)
        Label memberLabel = new Label("Membership Tier:");
        memberLabel.setStyle("-fx-font-weight: bold;");

        ToggleGroup memberGroup = new ToggleGroup();
        RadioButton goldBtn     = new RadioButton("Gold  (5% discount)");
        RadioButton platinumBtn = new RadioButton("Platinum  (10% discount)");
        goldBtn.setToggleGroup(memberGroup);
        platinumBtn.setToggleGroup(memberGroup);
        goldBtn.setSelected(true);

        HBox memberOptions = new HBox(20, goldBtn, platinumBtn);

        // Update price preview when membership or slider changes
        Runnable updatePrice = () -> {
            int hours = selectedHours[0];
            double base = vehicle.calculateRentalCost(hours);
            Member preview = platinumBtn.isSelected()
                ? new PlatinumMember("M002", "Preview")
                : new GoldMember("M001", "Preview");
            double total = base - preview.getDiscount(base);
            pricePreview.setText("Estimated Total: $" + String.format("%.2f", total));
        };

        slider.valueProperty().addListener((obs, old, val) -> {
            int hours = val.intValue();
            selectedHours[0] = hours;
            durationLabel.setText("Rental Duration: " + hours + (hours == 1 ? " hr" : " hrs"));
            updatePrice.run();
        });

        goldBtn.setOnAction(e -> updatePrice.run());
        platinumBtn.setOnAction(e -> updatePrice.run());

        Button bookBtn = new Button("Book Now");
        bookBtn.getStyleClass().add("button-primary");
        bookBtn.setMaxWidth(Double.MAX_VALUE);
        bookBtn.setOnAction(e -> {
            if (!vehicle.isAvailable()) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Unavailable");
                alert.setHeaderText(null);
                alert.setContentText("This vehicle is already booked.");
                alert.showAndWait();
                return;
            }

            Member member = platinumBtn.isSelected()
                ? new PlatinumMember("M002", "Member")
                : new GoldMember("M001", "Member");

            double base = vehicle.calculateRentalCost(selectedHours[0]);
            double discount = member.getDiscount(base);
            double total = base - discount;

            // Swap form for a receipt summary
            bookingForm.getChildren().clear();

            Label receiptTitle = new Label("Booking Summary");
            receiptTitle.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

            Separator sep1 = new Separator();

            bookingForm.getChildren().addAll(
                receiptTitle,
                sep1,
                receiptRow("Vehicle",    vehicle.getBrand() + " " + vehicle.getModel()),
                receiptRow("Type",       vehicle.getClass().getSimpleName()),
                receiptRow("Membership", member.getClass().getSimpleName()),
                receiptRow("Duration",   selectedHours[0] + " hour(s)"),
                receiptRow("Base Price", "$" + String.format("%.2f", base)),
                receiptRow("Discount",   "-$" + String.format("%.2f", discount)),
                new Separator(),
                receiptRow("Total",      "$" + String.format("%.2f", total))
            );

            // Style the total row bold
            if (bookingForm.getChildren().get(bookingForm.getChildren().size() - 1) instanceof HBox totalRow) {
                totalRow.getChildren().forEach(node -> {
                    if (node instanceof Label lbl) lbl.setStyle("-fx-font-weight: bold; -fx-font-size: 15px;");
                });
            }

            Button confirmBtn = new Button("Confirm Booking");
            confirmBtn.getStyleClass().add("button-primary");
            confirmBtn.setMaxWidth(Double.MAX_VALUE);
            confirmBtn.setOnAction(ev -> {
                Booking booking = new Booking(member, vehicle, selectedHours[0], "");
                FleetBrowserView.activeBookings.put(vehicle.getVehicleId(), booking);
                NavigationManager.getInstance().showFleetBrowser();
            });

            bookingForm.getChildren().add(confirmBtn);
        });

        bookingForm.getChildren().addAll(
            durationLabel, slider, memberLabel, memberOptions, pricePreview, bookBtn
        );
        rightSide.getChildren().addAll(vehicleTitle, specs, bookingForm);
        content.getChildren().addAll(heroImage, rightSide);
        this.getChildren().addAll(backBtn, content);
    }

    private HBox receiptRow(String label, String value) {
        HBox row = new HBox();
        Label l = new Label(label);
        l.setStyle("-fx-text-fill: #86868B; -fx-min-width: 110px;");
        Label v = new Label(value);
        v.setStyle("-fx-font-weight: bold;");
        Region spacer = new Region();
        HBox.setHgrow(spacer, javafx.scene.layout.Priority.ALWAYS);
        row.getChildren().addAll(l, spacer, v);
        return row;
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
}
