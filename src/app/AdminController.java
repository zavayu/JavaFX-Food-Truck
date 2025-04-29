package app;

import java.util.HashMap;
import java.util.Map;

import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

/**
 * AdminController is responsible for managing the admin interface of the food truck application.
 * It handles actions such as restocking, optimizing inventory, and updating the UI elements
 * to reflect the current state of the food truck's inventory and sales.
 */
public class AdminController {
    @FXML
    private Button restockButton;

    @FXML
    private Text profit;

    @FXML
    private Label slotCount1, slotCount2, slotCount3, slotCount4, slotCount5, slotCount6, slotCount7, slotCount8;

    @FXML
    private Label slotName1, slotName2, slotName3, slotName4, slotName5, slotName6, slotName7, slotName8;

    @FXML
    private Button restock1, restock2, restock3, restock64, restock5, restock6, restock7, restock8;

    @FXML
    private BarChart<String, Number> itemsSoldChart;

    @FXML
    private CategoryAxis x;

    @FXML
    private NumberAxis y;

    private Controller controller;
    private Foodtruck foodtruck;

    public void setController(Controller controller) {
        this.controller = controller;
        foodtruck = controller.getFoodtruck();
        updateProfit();
        updateSales();
        updateSlotLabels();
    }

    @FXML
    private void initialize() {
    }

    @FXML
    private void restockAll() {
        foodtruck.restockAll();
        controller.updateCountLabels();
        updateSlotLabels();
    }

    @FXML
    private void restockSlot(javafx.event.ActionEvent event) {
        Button sourceButton = (Button) event.getSource();
        int slotIndex = Integer.parseInt(sourceButton.getUserData().toString());

        int count = 16;
        if (foodtruck.getSlots()[slotIndex].getFoodName().equals("Grilled Cheese")) count = 32;
        foodtruck.getSlots()[slotIndex].restock(count);
        controller.updateCountLabels();
        updateSlotLabels();
    }

    @FXML
    private void optimize() {
        foodtruck.optimize();
        updateSlotLabels();
    }

    public void updateSlotLabels() {
        Slot[] slots = foodtruck.getSlots();
        slotName1.setText(slots[0].getFoodName());
        slotName2.setText(slots[1].getFoodName());
        slotName3.setText(slots[2].getFoodName());
        slotName4.setText(slots[3].getFoodName());
        slotName5.setText(slots[4].getFoodName());
        slotName6.setText(slots[5].getFoodName());
        slotName7.setText(slots[6].getFoodName());
        slotName8.setText(slots[7].getFoodName());

        slotCount1.setText("(" + slots[0].size() + ")");
        slotCount2.setText("(" + slots[1].size() + ")");
        slotCount3.setText("(" + slots[2].size() + ")");
        slotCount4.setText("(" + slots[3].size() + ")");
        slotCount5.setText("(" + slots[4].size() + ")");
        slotCount6.setText("(" + slots[5].size() + ")");
        slotCount7.setText("(" + slots[6].size() + ")");
        slotCount8.setText("(" + slots[7].size() + ")");
        updateProfit();
        updateSales();
        updateSlotColors();
    }

    private void updateSlotColors() {
        Slot[] slots = foodtruck.getSlots();
        slotCount1.setTextFill((!slots[0].isEmpty() ? javafx.scene.paint.Color.WHITE : javafx.scene.paint.Color.RED));
        slotCount2.setTextFill((!slots[1].isEmpty() ? javafx.scene.paint.Color.WHITE : javafx.scene.paint.Color.RED));
        slotCount3.setTextFill((!slots[2].isEmpty() ? javafx.scene.paint.Color.WHITE : javafx.scene.paint.Color.RED));
        slotCount4.setTextFill((!slots[3].isEmpty() ? javafx.scene.paint.Color.WHITE : javafx.scene.paint.Color.RED));
        slotCount5.setTextFill((!slots[4].isEmpty() ? javafx.scene.paint.Color.WHITE : javafx.scene.paint.Color.RED));
        slotCount6.setTextFill((!slots[5].isEmpty() ? javafx.scene.paint.Color.WHITE : javafx.scene.paint.Color.RED));
        slotCount7.setTextFill((!slots[6].isEmpty() ? javafx.scene.paint.Color.WHITE : javafx.scene.paint.Color.RED));
        slotCount8.setTextFill((!slots[7].isEmpty() ? javafx.scene.paint.Color.WHITE : javafx.scene.paint.Color.RED));
    }

    private void updateProfit() {
        profit.setText(String.format("Total Profit: $%.2f", foodtruck.getProfit()));
    }

    private void updateSales() {
        itemsSoldChart.getData().clear();

        XYChart.Series<String, Number> set1 = new XYChart.Series<>();

        HashMap<String, Integer> sales = foodtruck.getSales();
        for (Map.Entry<String, Integer> sale : sales.entrySet()) {
            String item = sale.getKey();
            int numSold = sale.getValue();
            XYChart.Data<String, Number> data = new XYChart.Data<>(item, numSold);
            set1.getData().add(data);

            data.nodeProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue != null) {
                    newValue.setStyle("-fx-bar-fill: orange;");
                }
            });
        }

        itemsSoldChart.getData().add(set1);
    }
}
