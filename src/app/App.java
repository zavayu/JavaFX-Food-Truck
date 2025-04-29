package app;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * Main application class for the Grilled Cheese Food Truck application.
 * This class extends the JavaFX Application class and serves as the entry point for the application.
 */
public class App extends Application {

    @Override
    public void start(Stage stage) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("MainView.fxml"));
        } catch (IOException e) {
            System.out.println("MainView.fxml not found");
            e.printStackTrace();
        }
        Scene scene = new Scene(root, 960, 683);
        stage.setTitle("The GC Food Truck!!");
        stage.getIcons().add(new Image(getClass().getResource("/resources/food-truck.png").toExternalForm()));
        stage.setScene(scene);
        stage.setResizable(false); 
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}



