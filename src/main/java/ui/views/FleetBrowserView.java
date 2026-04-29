package ui.views;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import ui.components.VehicleCard;
import vehicles.*;
import booking.Booking;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FleetBrowserView extends ScrollPane {

    public static final Map<String, Booking> activeBookings = new HashMap<>();

    private static final List<LuxuryVehicle> allVehicles = List.of(
        new Supercar("V1", "Lamborghini", "Huracan",   "GOLD",     202),
        new LuxurySUV("V2", "Rolls Royce", "Cullinan", "STANDARD", 5),
        new Exotic("V3", "Bugatti",       "Chiron",    "PLATINUM", "France"),
        new Supercar("V4", "Ferrari",     "488 GTB",   "GOLD",     205)
    );

    private final FlowPane grid = new FlowPane(25, 25);
    private String activeFilter = "All";

    public FleetBrowserView() {
        this.setFitToWidth(true);

        VBox root = new VBox(30);
        root.setPadding(new Insets(40));
        root.getStyleClass().add("root");

        // Header
        VBox header = new VBox(8);
        Label title = new Label("Find Your Perfect Drive");
        title.getStyleClass().add("heading");
        Label subtitle = new Label("Browse our exclusive collection of luxury vehicles.");
        subtitle.getStyleClass().add("subheading");
        header.getChildren().addAll(title, subtitle);

        // Search bar
        TextField search = new TextField();
        search.setPromptText("Search by brand or model...");
        search.setPrefWidth(420);
        search.getStyleClass().add("search-bar");
        search.textProperty().addListener((obs, old, val) -> renderGrid(val, activeFilter));

        // Filter chips
        HBox filters = new HBox(10);
        String[] categories = {"All", "Supercar", "SUV", "Exotic"};
        for (String cat : categories) {
            Button chip = new Button(cat);
            chip.getStyleClass().add("chip");
            styleChip(chip, cat.equals("All"));
            chip.setOnAction(e -> {
                activeFilter = cat;
                filters.getChildren().forEach(node -> {
                    if (node instanceof Button b) styleChip(b, b.getText().equals(cat));
                });
                renderGrid(search.getText(), activeFilter);
            });
            filters.getChildren().add(chip);
        }

        HBox controls = new HBox(15);
        controls.setAlignment(Pos.CENTER_LEFT);
        controls.getChildren().addAll(search, filters);

        // Grid
        grid.setAlignment(Pos.CENTER_LEFT);
        renderGrid("", "All");

        root.getChildren().addAll(header, controls, grid);
        this.setContent(root);
    }

    private void renderGrid(String searchText, String filter) {
        grid.getChildren().clear();
        for (LuxuryVehicle v : allVehicles) {
            boolean matchesSearch = searchText == null || searchText.isBlank()
                || v.getBrand().toLowerCase().contains(searchText.toLowerCase())
                || v.getModel().toLowerCase().contains(searchText.toLowerCase());

            boolean matchesFilter = filter.equals("All")
                || (filter.equals("Supercar") && v instanceof Supercar)
                || (filter.equals("SUV")      && v instanceof LuxurySUV)
                || (filter.equals("Exotic")   && v instanceof Exotic);

            if (matchesSearch && matchesFilter) {
                grid.getChildren().add(new VehicleCard(v, activeBookings.get(v.getVehicleId())));
            }
        }
        if (grid.getChildren().isEmpty()) {
            Label empty = new Label("No vehicles match your search.");
            empty.getStyleClass().add("subheading");
            grid.getChildren().add(empty);
        }
    }

    private void styleChip(Button chip, boolean active) {
        if (active) {
            chip.setStyle("-fx-background-color: #1D1D1F; -fx-text-fill: white; -fx-background-radius: 20; -fx-padding: 8 18; -fx-cursor: hand;");
        } else {
            chip.setStyle("-fx-background-color: #E8E8ED; -fx-text-fill: #1D1D1F; -fx-background-radius: 20; -fx-padding: 8 18; -fx-cursor: hand;");
        }
    }
}
