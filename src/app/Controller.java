package app;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * Controller class for managing the user interface and interactions
 * with the Foodtruck application. Handles user actions, updates UI
 * elements, and communicates with the AdminController.
 */
public class Controller {

    @FXML
    private Button button, button1, button2, button3, button4;

    @FXML
    private Label countLabel, countLabel1, countLabel2, countLabel3, countLabel4;

    @FXML
    private Label priceLabel1, priceLabel2, priceLabel3, priceLabel4, priceLabel5;

    @FXML
    private Label totalLabel;

    private double total;
    private Foodtruck foodtruck;
    private AdminController adminController;

    public Foodtruck getFoodtruck() {
        return foodtruck;
    }

    @FXML
    private void initialize() {
        foodtruck = new Foodtruck();
        adminController = null;
        updateCountLabels();
        updatePriceLabels();
    }

    @FXML
    private void buyCoke() {
        if(foodtruck.buyItem("Coke"))
            total += foodtruck.getMenu().get("Coke");
        updateCountLabels();
    }

    @FXML
    private void buySprite() {
        if(foodtruck.buyItem("Sprite"))
            total += foodtruck.getMenu().get("Sprite");
        updateCountLabels();
    }

    @FXML
    private void buyDrPepper() {
        if(foodtruck.buyItem("Dr Pepper"))
            total += foodtruck.getMenu().get("Dr Pepper");
        updateCountLabels();
    }

    @FXML
    private void buyPepsi() {
        if(foodtruck.buyItem("Pepsi"))
            total += foodtruck.getMenu().get("Pepsi");
        updateCountLabels();
    }

    @FXML
    private void buyGrilledCheese() {
        if(foodtruck.buyItem("Grilled Cheese"))
            total += foodtruck.getMenu().get("Grilled Cheese");
        updateCountLabels();
    }

    public void updateCountLabels() {
        countLabel.setText("(" + foodtruck.getCount("Coke") + ")");
        countLabel1.setText("(" + foodtruck.getCount("Sprite") + ")");
        countLabel2.setText("(" + foodtruck.getCount("Dr Pepper") + ")");
        countLabel3.setText("(" + foodtruck.getCount("Pepsi") + ")");
        countLabel4.setText("(" + foodtruck.getCount("Grilled Cheese") + ")");
        totalLabel.setText("Your Total: $" + String.format("%.2f", total));
        updateCountColors();
        if (adminController != null) {
            adminController.updateSlotLabels();
        }
    }

    public void updateCountColors() {
        countLabel.setTextFill((foodtruck.getCount("Coke") != 0) ? javafx.scene.paint.Color.WHITE : javafx.scene.paint.Color.RED);
        countLabel1.setTextFill((foodtruck.getCount("Sprite") != 0) ? javafx.scene.paint.Color.WHITE : javafx.scene.paint.Color.RED);
        countLabel2.setTextFill((foodtruck.getCount("Dr Pepper") != 0) ? javafx.scene.paint.Color.WHITE : javafx.scene.paint.Color.RED);
        countLabel3.setTextFill((foodtruck.getCount("Pepsi") != 0) ? javafx.scene.paint.Color.WHITE : javafx.scene.paint.Color.RED);
        countLabel4.setTextFill((foodtruck.getCount("Grilled Cheese") != 0) ? javafx.scene.paint.Color.WHITE : javafx.scene.paint.Color.RED);
    }

    public void updatePriceLabels() {
        priceLabel1.setText("$" + String.format("%.2f", foodtruck.getMenu().get("Coke")));
        priceLabel2.setText("$" + String.format("%.2f", foodtruck.getMenu().get("Sprite")));
        priceLabel3.setText("$" + String.format("%.2f", foodtruck.getMenu().get("Dr Pepper")));
        priceLabel4.setText("$" + String.format("%.2f", foodtruck.getMenu().get("Pepsi")));
        priceLabel5.setText("$" + String.format("%.2f", foodtruck.getMenu().get("Grilled Cheese")));
    }

    @FXML
    private void onOpenAdminView() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminView.fxml"));
            Parent root = loader.load();

            adminController = loader.getController();
            adminController.setController(this); 

            Stage adminStage = new Stage();
            adminStage.setTitle("Admin View");
            adminStage.getIcons().add(new Image(getClass().getResource("/resources/cog.png").toExternalForm()));
            adminStage.setScene(new Scene(root, 960, 683));
            adminStage.setResizable(false); 
            adminStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
}
