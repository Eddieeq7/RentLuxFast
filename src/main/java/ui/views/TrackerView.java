package ui.views;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import ui.NavigationManager;

public class TrackerView extends VBox {
    public TrackerView(Object booking) {
        this.setPadding(new Insets(40));
        this.setSpacing(30);
        this.getStyleClass().add("root");
        this.setAlignment(Pos.TOP_CENTER);

        Label title = new Label("Delivery Tracking");
        title.getStyleClass().add("heading");

        VBox statusCard = new VBox(20);
        statusCard.getStyleClass().add("card");
        statusCard.setMaxWidth(600);

        HBox steps = new HBox(40);
        steps.setAlignment(Pos.CENTER);
        steps.getChildren().addAll(
            createStatusStep("Pending", true),
            createStatusStep("Driver Assigned", true),
            createStatusStep("En Route", false),
            createStatusStep("Delivered", false)
        );

        Separator s = new Separator();
        
        GridPane info = new GridPane();
        info.setHgap(30);
        info.setVgap(15);
        info.add(new Label("Driver"), 0, 0);
        info.add(new Label("Marco Silva"), 1, 0);
        info.add(new Label("Vehicle"), 0, 1);
        info.add(new Label("Lamborghini Huracan"), 1, 1);
        info.add(new Label("ETA"), 0, 2);
        info.add(new Label("15 mins"), 1, 2);

        statusCard.getChildren().addAll(steps, s, info);

        Button receiptBtn = new Button("View Receipt");
        receiptBtn.getStyleClass().add("button-primary");
        receiptBtn.setOnAction(e -> NavigationManager.getInstance().showReceipt(null));

        this.getChildren().addAll(title, statusCard, receiptBtn);
    }

    private VBox createStatusStep(String label, boolean completed) {
        VBox box = new VBox(5);
        box.setAlignment(Pos.CENTER);
        Label dot = new Label(completed ? "●" : "○");
        dot.setStyle("-fx-font-size: 24px; -fx-text-fill: " + (completed ? "#28A745" : "#E8E8ED") + ";");
        Label l = new Label(label);
        l.setStyle("-fx-font-size: 10px; -fx-text-fill: " + (completed ? "#1D1D1F" : "#86868B") + ";");
        box.getChildren().addAll(dot, l);
        return box;
    }
}
