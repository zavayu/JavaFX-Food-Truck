package app;

import java.util.Map.Entry;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

/**
 * The Slot class represents a single slot containing a collection of food items.
 * A slot keeps track of how many items are sold and allows restocking or changing food types.
 */
class Slot {
    private Stack<FoodItem> foodItems;
    private String foodName;
    private double foodPrice;
    private int numSold;

    public Slot() {
        foodItems = new Stack<>();
    }

    public Slot(String name, double price, int count) {
        foodItems = new Stack<>();
        foodName = name;
        foodPrice = price;
        numSold = 0;
        for (int i = 0; i < count; i++) {
            foodItems.add(new FoodItem(name, price));
        }
    }

    public void setItem(String name, double price) {
        foodName = name;
        foodPrice = price;
        numSold = 0;
    }

    public String getFoodName() {
        return foodName;
    }

    public double getFoodPrice() {
        return foodPrice;
    }

    public int getNumSold() {
        return numSold;
    }

    /**
     * Removes one food item and increases the amount sold.
     * Returns true if successful and false if the slot is already empty.
     */
    public boolean buyItem() {
        if (foodItems.isEmpty()) return false;
        foodItems.pop();
        numSold++;
        return true;
    }

    /**
     * Restocks the slot to have count many food items
     */
    public void restock(int count) {
        for (int i = foodItems.size(); i < count; i++) {
            foodItems.add(new FoodItem(foodName, foodPrice));
        }
    }

    public int size() {
        return foodItems.size();
    }

    public boolean isEmpty() {
        return foodItems.isEmpty();
    }

    public void clear() {
        foodItems.clear();
    }
}

/**
 * Represents a Foodtruck that sells food items. Handles the main logic
 * of handling Slots and buying food items.
 */
public class Foodtruck {
    private final int NUM_SLOTS = 8;

    private Slot[] slots;
    private HashMap<String, Double> menu;
    private double profit;
    
    public Foodtruck () {
        slots = new Slot[NUM_SLOTS];
        menu = new HashMap<>();
        menu.put("Coke", 1.99);
        menu.put("Pepsi", 1.75);
        menu.put("Sprite", 1.20);
        menu.put("Dr Pepper", 1.99);
        menu.put("Grilled Cheese", 4.99);
        
        
        slots[0] = new Slot("Coke",   menu.get("Coke"), 16);
        slots[1] = new Slot("Coke",   menu.get("Coke"), 16);
        slots[2] = new Slot("Sprite",   menu.get("Sprite"), 16);
        slots[3] = new Slot("Sprite",   menu.get("Sprite"), 16);
        slots[4] = new Slot("Dr Pepper", menu.get("Dr Pepper"), 16);
        slots[5] = new Slot("Pepsi",     menu.get("Pepsi"), 16);
        slots[6] = new Slot("Grilled Cheese", menu.get("Grilled Cheese"), 32);
        slots[7] = new Slot("Grilled Cheese", menu.get("Grilled Cheese"), 32);
    }
    
    public Slot[] getSlots() {
        return slots;
    }

    public HashMap<String, Double> getMenu() {
        return menu;
    }

    public double getProfit() {
        return profit;
    }

    /**
     * Returns the number of FoodItems available
     * with the given name
     */
    public int getCount(String name) {
        int count = 0;
        for (int i = 0; i < NUM_SLOTS; i++) {
            if (slots[i].getFoodName().equals(name)) {
                count += slots[i].size();
            }
        }
        return count;
    }

    /**
     * Buys one item from the slot with the highest amount
     * returns true if sucessful and false if none are available
     */
    public boolean buyItem(String name) {
        Slot slot = null;
        int count = 0;
        for (Slot s : slots) {
            if (s.getFoodName().equals(name) && s.size() > count) {
                count = s.size();
                slot = s;
            }
        }
        if (slot != null) {
            profit += slot.getFoodPrice();
            slot.buyItem();
            return true;
        }
        return false;
    }

    /**
     * Restocks all the slots to maximum capacity
     */
    public void restockAll() {
        for (int i = 0; i < NUM_SLOTS; i++) {
            int count = 16;
            if (slots[i].getFoodName().equals("Grilled Cheese")) count = 32;
            slots[i].restock(count);
        }
    }

    /**
     * Returns a HashMap containing the name of a food item
     * and its associated number sold.
     */
    public HashMap<String, Integer> getSales() {
        HashMap<String, Integer> sales = new HashMap<>();

        for (Slot s : slots) {
            if (sales.containsKey(s.getFoodName())) {
                sales.put(s.getFoodName(), sales.get(s.getFoodName())+s.getNumSold());
            } else {
                sales.put(s.getFoodName(), s.getNumSold());
            }
        }

        return sales;
    }

    /**
     * Replaces a slot for the least popular drink with the most popular
     * drink to increase supply of the more popular item.
     */
    public boolean optimize() {
        String popular_drink = "";
        String unpopular_drink = "";
        int max_sales = 0;
        int min_sales = Integer.MAX_VALUE;

        Set<Entry<String, Integer>> slotSales = getSales().entrySet();
        for (Map.Entry<String, Integer> set : slotSales) {
            if (set.getKey().equals("Grilled Cheese")) continue;
            if (set.getValue() > max_sales) {
                popular_drink = set.getKey();
                max_sales = set.getValue();
            } else if (set.getValue() < min_sales) {
                unpopular_drink = set.getKey();
                min_sales = set.getValue();
            }
        }

        for (int i = 0; i < NUM_SLOTS; i++) {
            if (slots[i].getFoodName().equals(unpopular_drink)) {
                slots[i].clear();
                slots[i].setItem(popular_drink, menu.get(popular_drink));
                slots[i].restock(16);
                return true;
            }
        }

        return false;
    }

}
