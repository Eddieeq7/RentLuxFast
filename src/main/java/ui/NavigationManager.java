package ui;

import javafx.scene.layout.StackPane;
import ui.views.*;
import vehicles.LuxuryVehicle;

public class NavigationManager {
    private final StackPane container;
    private static NavigationManager instance;

    public NavigationManager(StackPane container) {
        this.container = container;
        instance = this;
    }

    public static NavigationManager getInstance() { return instance; }

    public void showFleetBrowser() {
        container.getChildren().setAll(new FleetBrowserView());
    }

    public void showVehicleDetails(LuxuryVehicle vehicle) {
        container.getChildren().setAll(new VehicleDetailView(vehicle));
    }

    public void showLogin(Runnable onLoginSuccess) {
        container.getChildren().setAll(new LoginView(onLoginSuccess));
    }

    public void showTracker(Object booking) {
        container.getChildren().setAll(new TrackerView(booking));
    }

    public void showReceipt(Object booking) {
        container.getChildren().setAll(new ReceiptView(booking));
    }
}
