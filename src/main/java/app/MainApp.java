package app;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import ui.NavigationManager;

public class MainApp extends Application {
    @Override
    public void start(Stage primaryStage) {
        StackPane root = new StackPane();
        NavigationManager nav = new NavigationManager(root);
        
        Scene scene = new Scene(root, 1100, 800);
        scene.getStylesheets().add(getClass().getResource("/styles/luxury.css").toExternalForm());
        
        primaryStage.setTitle("RentLuxFast | Luxury Vehicles");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        // Initial view
        nav.showFleetBrowser();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
